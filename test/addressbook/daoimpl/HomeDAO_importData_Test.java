package addressbook.daoimpl;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import addressbook.domain.Person;
import addressbook.exception.AddressBookException;

/**
 * Delete database data before executing this test case
 *
 * Tests deleteData() of HomeDAO
 *
 */
public class HomeDAO_importData_Test extends TestCase
{
	/**
	 * HomeDAOImpl obj
	 */
	HomeDAOImpl _homeDAO;

	/**
	 * tests getPersonsInfo() for valid input
	 */
	public void test_getPersonsInfo_assertTrue()
	{
		running(fakeApplication(), ()->
		{
			_homeDAO = new HomeDAOImpl();
			try
			{
				List<Person> people = getValidPeople();
				boolean imported = _homeDAO.importData(people);
				assertTrue(imported);
			}
			catch(AddressBookException e)
			{
				System.err.println(e.getMessage());
			}
		});
	}

	/**
	 * tests getPersonsInfo() for invalid input
	 */
	public void test_getPersonsInfo_assertFalse()
	{
		running(fakeApplication(), ()->
		{
			_homeDAO = new HomeDAOImpl();
			try
			{
				List<Person> people = getInvalidPeople();
				boolean imported = _homeDAO.importData(people);
				assertFalse(imported);
			}
			catch(AddressBookException e)
			{
				System.err.println(e.getMessage());
			} 
		});
	}

	/**
	 * returns valid persons list
	 *
	 * @return
	 */
	private List<Person> getValidPeople()
	{
		List<Person> people = new ArrayList<Person>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		person1.setFname("Sanket");
		person1.setLname("Bane");
		person1.setMobile("+919819615396");
		person1.setAddress("Virar");
		person2.setFname("Vinayak");
		person2.setLname("Magdum");
		person2.setMobile("+919638520741");
		person2.setAddress("Kolhapur");
		person3.setFname("Parag");
		person3.setLname("Jimbhal");
		person3.setMobile("+917418520963");
		person3.setAddress("Saphale");
		people.add(person1);
		people.add(person2);
		people.add(person3);
		return people;
	}

	/**
	 * returns invalid persons list
	 *
	 * @return
	 */
	private List<Person> getInvalidPeople()
	{
		List<Person> people = new ArrayList<Person>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		person1.setFname("Sanket");
		person1.setLname("Bane");
		person1.setMobile("+919819615396");
		person1.setAddress("Virar");
		person2.setFname("Vinayak");
		person2.setLname("Magdum");
		person2.setMobile("+919638520741");
		person2.setAddress("Kolhapur");
		person3.setFname("Parag");
		person3.setAddress("Saphale");
		people.add(person1);
		people.add(person2);
		people.add(person3);
		return people;
	}
}