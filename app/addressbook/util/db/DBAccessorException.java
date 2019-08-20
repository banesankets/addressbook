package addressbook.util.db;

/**
 * AddressBookException class
 */
public class DBAccessorException extends Exception
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * message
	 */
	private String _message;

	/**
	 * constructor
	 *
	 * @param msg
	 */
	public DBAccessorException(String msg)
	{
		_message = msg;
	}

	/**
	 * returns message
	 *
	 * @return
	 */
	public String getMessage()
	{
		return _message;
	}
}