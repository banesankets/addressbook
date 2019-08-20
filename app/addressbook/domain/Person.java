package addressbook.domain;

/**
 * Bean class representing a Person information
 */
public class Person
{
	/**
	 * primary key in db
	 */
	private int _id;
	/**
	 * first name
	 */
	private String _fname;
	/**
	 * last name
	 */
	private String _lname;
	/**
	 * mobile number
	 */
	private String _mobile;
	/**
	 * telephone number
	 */
	private String _telephone;
	/**
	 * email
	 */
	private String _email;
	/**
	 * address
	 */
	private String _address;

	/**
	 * return _fname
	 *
	 * @return
	 */
	public String getFname()
	{
		return _fname;
	}

	/**
	 * set _fname
	 *
	 * @param fname
	 */
	public void setFname(String fname)
	{
		_fname = fname;
	}

	/**
	 * return _lname
	 *
	 * @return
	 */
	public String getLname()
	{
		return _lname;
	}

	/**
	 * set last name
	 *
	 * @param lname
	 */
	public void setLname(String lname)
	{
		_lname = lname;
	}

	/**
	 * returns _mobile
	 *
	 * @return
	 */
	public String getMobile()
	{
		return _mobile;
	}

	/**
	 * set _mobile
	 *
	 * @param mobile
	 */
	public void setMobile(String mobile)
	{
		_mobile = mobile;
	}

	/**
	 * returns _telephone
	 *
	 * @return
	 */
	public String getTelephone()
	{
		return _telephone;
	}

	/**
	 * set _telephone
	 *
	 * @param telephone
	 */
	public void setTelephone(String telephone)
	{
		_telephone = telephone;
	}

	/**
	 * returns _email
	 *
	 * @return
	 */
	public String getEmail()
	{
		return _email;
	}

	/**
	 * set _email
	 *
	 * @param email
	 */
	public void setEmail(String email)
	{
		_email = email;
	}

	/**
	 * returns _address
	 *
	 * @return
	 */
	public String getAddress()
	{
		return _address;
	}

	/**
	 * set _address
	 *
	 * @param address
	 */
	public void setAddress(String address)
	{
		_address = address;
	}

	/**
	 * get _id
	 *
	 * @return
	 */
	public int getId()
	{
		return _id;
	}

	/**
	 * set value of _id
	 *
	 * @param id
	 */
	public void setId(int id)
	{
		_id = id;
	}
}