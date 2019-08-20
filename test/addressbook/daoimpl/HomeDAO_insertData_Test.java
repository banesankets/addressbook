package addressbook.daoimpl;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import junit.framework.TestCase;

import org.dbunit.dataset.ITable;

import addressbook.domain.Person;
import addressbook.exception.AddressBookException;
import addressbook.utility.Definitions;

/**
 * Delete database data before executing this test case
 *
 * Tests insertData() of HomeDAO
 */
public class HomeDAO_insertData_Test extends TestCase
{
	/**
	 * HomeDAOImpl obj
	 */
	HomeDAOImpl _homeDAO;

	/**
	 * tests insertData()
	 */
	public void test_insertData()
	{
		running(fakeApplication(), ()->
		{
			_homeDAO = new HomeDAOImpl();
			Person person = getPerson();
			try
			{
				int id = _homeDAO.insertData(person);
				assertResult(id, person);
			}
			catch(AddressBookException e)
			{
				System.err.println(e.getMessage());
			} 
			
		});
	}

	/**
	 * returns persons obj
	 *
	 * @return
	 */
	private Person getPerson()
	{
		Person person = new Person();
		person.setFname("Adi");
		person.setLname("Gavad");
		person.setMobile("+919638527410");
		person.setTelephone("+917410852963");
		person.setEmail("adigavad@gmail.com");
		person.setAddress("Virar");
		return person;
	}

	/**
	 * assert result
	 *
	 * @param id - id of inserted record
	 * @param person - person obj
	 */
	private void assertResult(int id, Person person)throws AddressBookException
	{
		try
		{
			TableRegister tableRegister;
			String query = getSelectQuery(id);
			tableRegister = new TableRegister("127.0.0.1", 1433, "ADDRESSBOOK");
			ITable table = tableRegister.getTableDataFromQuery("PersonMaster", query);
			assertEquals(person.getFname(), table.getValue(0, "FsFirstName"));
			assertEquals(person.getLname(), table.getValue(0, "FsLastName"));
			assertEquals(person.getMobile(), table.getValue(0, "FsMobile"));
			assertEquals(person.getTelephone(), table.getValue(0, "FsTelephone"));
			assertEquals(person.getEmail(), table.getValue(0, "FsEmail"));
			assertEquals(person.getAddress(), table.getValue(0, "FsAddress"));
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.IDATABASE_CONNECTION_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
	}

	/**
	 * returns select query
	 *
	 * @param id - id of inserted record
	 * @return
	 */
	private String getSelectQuery(int id)
	{
		String query = "SELECT FsFirstName, FsLastName, FsMobile, FsTelephone, FsEmail, FsAddress"
					+ " FROM PersonMaster WHERE FvId = " + id;
		return query;
	}
}