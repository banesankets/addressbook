package addressbook.daoimpl;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.util.List;

import junit.framework.TestCase;

import org.dbunit.dataset.ITable;

import addressbook.domain.Person;
import addressbook.exception.AddressBookException;
import addressbook.utility.Definitions;

/**
 * Delete database data before executing this test case
 *
 * Tests getPersonInfo() of HomeDAO
 */
public class HomeDAO_getPersonsInfo_Test extends TestCase
{
	/**
	 * HomeDAOImpl obj
	 */
	HomeDAOImpl _homeDAO;

	/**
	 * tests getPersonsInfo()
	 */
	public void test_getPersonsInfo()
	{
		running(fakeApplication(), ()->
		{
			_homeDAO = new HomeDAOImpl();
			try
			{
				loadDB();
				List<Person> persons = _homeDAO.getPersonsInfo();
				assertEquals(getRowCount(), persons.size());
			}
			catch(AddressBookException e)
			{
				System.err.println(e.getMessage());
			} 
			
		});
	}

	/**
	 * loads .xls file into db
	 *
	 * @throws AddressBookException 
	 */
	private void loadDB() throws AddressBookException
	{
		TableRegister tableRegister;
		try
		{
			tableRegister = new TableRegister("127.0.0.1", 1433, "ADDRESSBOOK");
			tableRegister.load("./testdata/AddressbookTest.xls");
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.IDATABASE_TESTER_EXCEPTION, e.getMessage());
			throw new AddressBookException(errmsg);
		}
	}

	/**
	 * returns row count of database
	 *
	 * @return
	 * @throws AddressBookException 
	 */
	private int getRowCount() throws AddressBookException
	{
		int rowCount = 0;
		TableRegister tableRegister;
		try
		{
			tableRegister = new TableRegister("127.0.0.1", 1433, "ADDRESSBOOK");
			ITable table = tableRegister.getTableDataFromTable("PersonMaster");
			rowCount = table.getRowCount();	
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.IDATABASE_CONNECTION_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
		return rowCount;
	}
}