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
public class HomeDAO_updateData_Test extends TestCase
{
	/**
	 * HomeDAOImpl obj
	 */
	HomeDAOImpl _homeDAO;

	/**
	 * tests updateData
	 */
	public void test_updateData()
	{
		running(fakeApplication(), ()->
		{
			_homeDAO = new HomeDAOImpl();
			Person person = getPerson();
			Person editedPerson = getEditedPerson();
			try
			{
				int id = _homeDAO.insertData(person);
				editedPerson.setId(id);
				_homeDAO.updateData(editedPerson);
				assertResult(person);
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
	 * returns edited persons obj
	 *
	 * @return
	 */
	private Person getEditedPerson()
	{
		Person person = new Person();
		person.setFname("Aditya");
		person.setLname("Gavad");
		person.setMobile("+917896541230");
		person.setTelephone("+917410852963");
		person.setEmail("adigavad@gmail.com");
		person.setAddress("Saphale");
		return person;
	}

	/**
	 * assertResult
	 *
	 * @param person - person obj
	 * @throws AddressBookException 
	 * @throws ClassNotFoundException 
	 */
	private void assertResult(Person person) throws AddressBookException
	{
		try
		{
			TableRegister tableRegister;
			String query = getSelectQuery(person.getId());
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
	 * @param id  - id of inserted record
	 * @return
	 */
	private String getSelectQuery(int id)
	{
		String query = "SELECT FsFirstName, FsLastName, FsMobile, FsTelephone, FsEmail, FsAddress"
					+ " FROM PersonMaster WHERE FvId = " + id;
		
		return query;
	}
}