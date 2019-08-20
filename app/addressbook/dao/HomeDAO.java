package addressbook.dao;

import java.util.List;

import addressbook.domain.Person;
import addressbook.exception.AddressBookException;

/**
 * HomeDAO interface
 */
public interface HomeDAO
{
	/**
	 * insert Person record
	 *
	 * @param Person - Person obj
	 * @return
	 */
	public int insertData(Person person)throws AddressBookException;

	/**
	 * update Person record
	 *
	 * @param Person - Person obj
	 * @return
	 */
	public boolean updateData(Person person)throws AddressBookException;

	/**
	 * delete specified record
	 *
	 * @param id - id of contact
	 * @return
	 */
	public boolean deleteData(int id)throws AddressBookException;
	/**
	 * return all records
	 *
	 * @return
	 */
	public List<Person> getPersonsInfo()throws AddressBookException;

	/**
	 * import data from given list
	 * 
	 * @param people - Persons list
	 * @return
	 */
	public boolean importData(List<Person> people)throws AddressBookException;

	/**
	 * return all data in byte[]
	 * 
	 * @return
	 */
	public byte[] exportData()throws AddressBookException;
}