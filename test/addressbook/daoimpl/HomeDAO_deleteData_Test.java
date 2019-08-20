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
 * Tests deleteData() of HomeDAO
 */
public class HomeDAO_deleteData_Test extends TestCase
{
	/**
	 * HomeDAOImpl obj
	 */
	HomeDAOImpl _homeDAO;

	/**
	 * tests deleteData()
	 */
	public void test_deleteData()
	{
		running(fakeApplication(), ()->
		{
			_homeDAO = new HomeDAOImpl();
			Person person = getPerson();
			try
			{
				int id = _homeDAO.insertData(person);
				person.setId(id);
				_homeDAO.deleteData(id);
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
	 * asserts result
	 *
	 * @param person - person obj
	 * @throws AddressBookException 
	 */
	private void assertResult(Person person) throws AddressBookException
	{
		try
		{
			TableRegister tableRegister;
			String query = getSelectQuery(person.getId());
			tableRegister = new TableRegister("127.0.0.1", 1433, "ADDRESSBOOK");
			ITable table = tableRegister.getTableDataFromQuery("PersonMaster", query);
			assertEquals(table.getRowCount(), 0);
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
	 * @param - id of inserted record
	 * @return
	 */
	private String getSelectQuery(int id)
	{
		String query = "SELECT FsFirstName, FsLastName, FsMobile, FsTelephone, FsEmail, FsAddress"
				+ " FROM PersonMaster WHERE FvId = " + id;
		return query;
	}
}