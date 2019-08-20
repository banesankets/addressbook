package addressbook.exception;

/**
 * AddressBookException class
 */
public class AddressBookException extends Exception
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
	public AddressBookException(String msg)
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