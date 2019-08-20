package addressbook.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import addressbook.daoimpl.HomeDAOImpl;
import addressbook.domain.Person;
import addressbook.exception.AddressBookException;
import addressbook.utility.Definitions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

/**
 * HomeController class
 */
public class HomeController extends Controller 
{
	/**
	 * HomeDAOImpl object
	 */
	HomeDAOImpl _homeDAO;

	/**
	 * constructor
	 *
	 * @param homeDAO - HomeDAOImpl object 
	 */
	@Inject
	public HomeController(HomeDAOImpl homeDAO)
	{
		_homeDAO = homeDAO;
	}

	/**
	 * displays home page 
	 *
	 * @return
	 */
	public Result getContactList() throws AddressBookException
	{
		List<Person> peoples = _homeDAO.getPersonsInfo();
		return ok(views.html.home.render(peoples));
	}

	/**
	 * insert new contact 
	 *
	 * @return
	 * @throws AddressBookException 
	 */
	public Result insertContact() throws AddressBookException
	{
		int id = 0;
		Result result = null;
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			Person person = mapper.readValue(json.toString(), Person.class);
			id = _homeDAO.insertData(person);
			if(id != 0)
			{
				List<Person> people = _homeDAO.getPersonsInfo();
				Map<String,Object> responce = new HashMap<String,Object>();
				responce.put(Definitions.ID_KEY_NAME, id);
				responce.put(Definitions.PEOPLE_KEY_NAME, people);
				result = ok(Json.toJson(responce)).as("application/json");
			}
			else
			{
				result = internalServerError();
			}
		}
		catch(IOException e)
		{
			String errmsg = String.format(Definitions.IO_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
			
		}
		return result;
	}

	/**
	 * update contact  
	 *
	 * @return
	 * @throws AddressBookException 
	 */
	public Result updateContact() throws AddressBookException
	{
		boolean status = false;
		Result result = null;
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			Person person = mapper.readValue(json.toString(), Person.class);
			status = _homeDAO.updateData(person);
			if(status)
			{
				List<Person> people = _homeDAO.getPersonsInfo();
				result = ok(Json.toJson(people)).as("application/json");
			}
			else
			{
				result = internalServerError();
			}
		}
		catch(IOException e)
		{
			String errmsg = String.format(Definitions.IO_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
		return result;
	}

	/**
	 * delete contact
	 *
	 * @return
	 * @throws AddressBookException 
	 */
	public Result deleteContact() throws AddressBookException
	{
		boolean status = false;
		Result result = null;
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			Person person = mapper.readValue(json.toString(), Person.class);
			status = _homeDAO.deleteData(person.getId());
			if(status == true)
			{
				List<Person> people = _homeDAO.getPersonsInfo();
				result = ok(Json.toJson(people)).as("application/json");
			}
			else
			{
				result = internalServerError();
			}
		}
		catch(IOException e)
		{
			String errmsg = String.format(Definitions.IO_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
		return result;
	}

	/**
	 * imports records 
	 *
	 * @return
	 * @throws AddressBookException 
	 */
	public Result importCSV() throws AddressBookException
	{
		Result result = null;
		boolean success = false;
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			List<Person> people = mapper.readValue(json.toString(), new TypeReference<List<Person>>(){});
			success = _homeDAO.importData(people);
			if(success)
			{
				result = ok();
			}
			else
			{
				result = internalServerError();
			}
		}
		catch(IOException e)
		{
			String errmsg = String.format(Definitions.IO_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
		return result;
		
	}

	/**
	 * exports records 
	 *
	 * @return
	 */
	public Result exportCSV()throws AddressBookException
	{
		Result result = null;
		byte[] csvByte = _homeDAO.exportData(); 
		if(csvByte != null)
		{
			result = ok(csvByte);
		}
		else
		{
			result = noContent();
		}
		return result;
	}
}