package addressbook.daoimpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import addressbook.dao.HomeDAO;
import addressbook.domain.Person;
import addressbook.exception.AddressBookException;
import addressbook.util.db.DBAccessor;
import addressbook.util.db.DBAccessorException;
import addressbook.util.db.DBAccessorFactory;
import addressbook.utility.Definitions;

import com.google.inject.Inject;

/**
 * HomeDAOImpl class
 */
public class HomeDAOImpl implements HomeDAO
{
	/**
	 * DBAccessorFactory obj
	 */
	static private DBAccessorFactory _factory;

	/**
	 * constructor
	 *
	 * @param dbAccessor - AddressbookDBAccessor obj 
	 */
	@Inject
	public HomeDAOImpl(DBAccessorFactory factory)
	{
		_factory = factory;
	}

	/**
	 * empty constructor
	 */
	public HomeDAOImpl()
	{

	}
	/**
	 * insert new record in DB
	 *
	 * @param person - Person obj
	 * @throws AddressBookException
	 */
	@Override
	public int insertData(Person person) throws AddressBookException
	{
		int id = 0;
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		String query = getInsertQuery(person);
		try
		{
			id = dbAccessor.executeInsert(query);
		}
		catch(DBAccessorException e)
		{
			throw new AddressBookException(e.getMessage());
		}
		finally
		{
			try
			{
				dbAccessor.free();
			}
			catch(DBAccessorException e)
			{
				throw new AddressBookException(e.getMessage());
			}
		}
		return id;
	}

	/**
	 * update existing record
	 *
	 * @param person - Person obj
	 * @throws AddressBookException
	 */
	@Override
	public boolean updateData(Person person) throws AddressBookException
	{
		boolean status = false;
		String query = getUpdateQuery(person);
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		try
		{
			int affectedRows = dbAccessor.executeUpdate(query);
			if(affectedRows == 1)
			{
				status = true;
			}
		}
		catch(DBAccessorException e)
		{
			throw new AddressBookException(e.getMessage());
		}
		finally
		{
			try
			{
				dbAccessor.free();
			}
			catch(DBAccessorException e)
			{
				throw new AddressBookException(e.getMessage());
			}
		}
		return status;
	}

	/**
	 * delete specified record
	 *
	 * @param id - id of contact
	 * @throws AddressBookException 
	 */
	@Override
	public boolean deleteData(int id) throws AddressBookException
	{
		boolean status = false;
		String query = getDeleteQuery(id);
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		try
		{
			int affectedRows = dbAccessor.executeUpdate(query);
			if(affectedRows == 1)
			{
				status = true;
			}
		}
		catch(DBAccessorException e)
		{
			throw new AddressBookException(e.getMessage());
		}
		return status;
	}

	/**
	 * returns all contacts from db
	 *
	 * @return
	 * @throws AddressBookException
	 */
	@Override
	public List<Person> getPersonsInfo() throws AddressBookException
	{
		String query = getPersonsInfoQuery();
		List<Person> people = new ArrayList<Person>();
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		
		try
		{
			ResultSet rs = dbAccessor.executeSelect(query);
			while(rs.next())
			{
				Person p = new Person();
				p.setId(rs.getInt("FvId"));
				p.setFname(rs.getString("FsFirstName"));
				p.setLname(rs.getString("FsLastName"));
				p.setMobile(rs.getString("FsMobile"));
				p.setTelephone(rs.getString("FsTelephone"));
				p.setEmail(rs.getString("FsEmail"));
				p.setAddress(rs.getString("FsAddress"));
				people.add(p);
			}
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new AddressBookException(errmsg);
		}
		finally
		{
			try
			{
				dbAccessor.free();
			}
			catch(DBAccessorException e)
			{
				throw new AddressBookException(e.getMessage());
			}
		}
		return people;
	}

	/**
	 * import data into db
	 *
	 * @param people - list of person
	 * @return
	 * @throws AddressBookException
	 */
	public boolean importData(List<Person> people) throws AddressBookException
	{
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		boolean recordUpdated = false;
		try
		{
			dbAccessor.beginTransaction();
			recordUpdated = importDataTransaction(people, dbAccessor);
			if(recordUpdated)
			{
				dbAccessor.commit();
			}
			else
			{
				dbAccessor.rollback();
			}
		}
		catch(DBAccessorException e)
		{
			throw new AddressBookException(e.getMessage());
		}
		finally
		{
			try
			{
				dbAccessor.free();
			}
			catch(DBAccessorException e)
			{
				throw new AddressBookException(e.getMessage());
			}
		}
		return recordUpdated;
	}

	/**
	 * transaction of importData
	 *
	 * @param people - list of person
	 * @param dbAccessor - DBAccessor obj
	 * @return
	 * @throws AddressBookException
	 */
	private boolean importDataTransaction(List<Person> people, DBAccessor dbAccessor)throws AddressBookException
	{
		boolean recordUpdated = true;
		try
		{
			for(int index = 0; index < people.size() && recordUpdated; index++)
			{
				Person person = people.get(index);
				if(isUpdateStatement(person))
				{
					String query = getUpdateQuery(person);
					int affectedRows = dbAccessor.executeUpdate(query);
					if (affectedRows != 1)
					{
						recordUpdated = false;
					}
				}
				else
				{
					int id = 0;
					String query = getInsertQuery(person);
					id = dbAccessor.executeInsert(query);
					if (id == 0)
					{
						recordUpdated = false;
					}
				}
			}
		}
		catch(DBAccessorException e)
		{
			throw new AddressBookException(e.getMessage());
		}
		return recordUpdated;
	}

	/**
	 * returns all contacts in byte[]
	 *
	 * @return
	 * @throws AddressBookException
	 */
	@Override
	public byte[] exportData() throws AddressBookException
	{
		byte[] csvBytes = null;
		String csvString = "FsFirstName, FsLastName, FsMobile, FsTelephone, FsEmail, FsAddress";
		String query = getPersonsInfoQuery();
		String telephone;
		String email;
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		try
		{
			ResultSet rs = dbAccessor.executeSelect(query);
			while(rs.next())
			{
				telephone = rs.getString("FsTelephone") == null ? "" : rs.getString("FsTelephone");
				email = rs.getString("FsEmail") == null ? "" :rs.getString("FsEmail");
				csvString += " \n" + rs.getString("FsFirstName") + "," + rs.getString("FsLastName") + "," + rs.getString("FsMobile") + ","
							+ telephone + "," + email + "," + rs.getString("FsAddress"); 
			}
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new AddressBookException(errmsg);
		}
		finally
		{
			try
			{
				dbAccessor.free();
			}
			catch(DBAccessorException e)
			{
				throw new AddressBookException(e.getMessage());
			}
		}
		csvBytes = csvString.getBytes();
		return csvBytes;
	}

	/**
	 * checks if specified person exists
	 *
	 * @param person  - Person obj
	 * @return
	 * @throws AddressBookException
	 */
	private boolean isUpdateStatement(Person person) throws AddressBookException
	{
		boolean exists = false;
		DBAccessor dbAccessor = _factory.getInstance(Definitions.ADDRESSBOOK_DATABSE);
		try
		{
			String query = getPersonExistQuery(person);
			ResultSet rs = dbAccessor.executeSelect(query);
			if(rs.next())
			{
				exists = true;
			}
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new AddressBookException(errmsg);
		}
		finally
		{
			try
			{
				dbAccessor.free();
			}
			catch(DBAccessorException e)
			{
				throw new AddressBookException(e.getMessage());
			}
		}
		return exists;
	}

	/**
	 * returns insert query
	 *
	 * @param person
	 * @return
	 */
	private String getInsertQuery(Person person)
	{
		String fname = person.getFname() == null ? "NULL" : "'" + person.getFname() + "'";
		String lname = person.getLname() == null ? "NULL" : "'" + person.getLname() + "'";
		String mobile = person.getMobile() == null ? "NULL" : "'" + person.getMobile() + "'";
		String telephone = person.getTelephone() == null ? "NULL" : "'" + person.getTelephone() + "'";
		String email = person.getEmail() == null ? "NULL" : "'" + person.getEmail() + "'";
		String address = person.getAddress() == null ? "NULL" : "'" + person.getAddress() + "'";

		String query = "INSERT INTO PersonMaster (FsFirstName, FsLastName, "
					+ "FsMobile, FsTelephone, FsEmail, FsAddress)"
					+ " VALUES(" + fname + "," + lname + "," + mobile + "," 
					+ telephone + "," + email + "," + address +")";
		return query;
	}

	/**
	 * returns update query
	 *
	 * @param person
	 * @return
	 */
	private String getUpdateQuery(Person person)
	{
		String query = "";
		String fname = person.getFname() == null ? "NULL" : "'" + person.getFname() + "'";
		String lname = person.getLname() == null ? "NULL" : "'" + person.getLname() + "'";
		String mobile = person.getMobile() == null ? "NULL" : "'" + person.getMobile() + "'";
		String telephone = person.getTelephone() == null ? "NULL" : "'" + person.getTelephone() + "'";
		String email = person.getEmail() == null ? "NULL" : "'" + person.getEmail() + "'";
		String address = person.getAddress() == null ? "NULL" : "'" + person.getAddress() + "'";
		if(person.getId() == 0)
		{
			query = "UPDATE PersonMaster SET FsMobile = " + mobile + ",FsTelephone = " + telephone 
					+ ",FsEmail = " + email + ",FsAddress = " + address + " WHERE FsFirstName = "
					+ fname + " and FsLastName = " + lname;
		}
		else
		{
			 query = "UPDATE PersonMaster SET FsFirstName = " + fname + ",FsLastName = " + lname
					+ ",FsMobile = " + mobile + ",FsTelephone = " + telephone + ",FsEmail = "
					+ email + ",FsAddress = " + address +" WHERE FvId = " + person.getId();
		}
		return query;
	}

	/**
	 * returns delete person by id query
	 *
	 * @param id - persons id
	 * @return
	 */
	private String getDeleteQuery(int id)
	{
		String query = "DELETE FROM PersonMaster WHERE FvId = " + id;
		return query;
	}

	/**
	 * returns select persons info query(with ID)
	 *
	 * @param person - Person obj
	 * @return
	 */
	private String getPersonsInfoQuery()
	{
		String query = "SELECT FvId, FsFirstName, FsLastName, FsMobile, FsTelephone, FsEmail, FsAddress"
					+ " FROM PersonMaster ORDER BY FsFirstName, FsLastName";
		return query;
	}

	/**
	 * returns person exist query
	 *
	 * @param person - Person obj
	 * @return
	 */
	private String getPersonExistQuery(Person person)
	{
		String query = "SELECT 1 FROM PersonMaster WHERE FsFirstname = '" + person.getFname() +
				"' AND FsLastName = '" + person.getLname() + "'";
		return query;
	}
}