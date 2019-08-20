package addressbook.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import play.db.Database;
import addressbook.exception.AddressBookException;
import addressbook.utility.Definitions;

/**
 * DBAccessorImpl class
 */
public class DBAccessorImpl implements DBAccessor
{
	/**
	 * connection object
	 */
	protected Connection _connection = null;
	/**
	 * statement object
	 */
	protected Statement _statement = null;
	/**
	 * Database obj
	 */
	private static Database _db;

	/**
	 * constructor
	 *
	 * @param db - Database obj
	 */
	public DBAccessorImpl(Database db)
	{
		_db = db;
	}

	/**
	 * initialize connection and statement
	 *
	 * @throws DBAccessorException 
	 */
	public void makeConnection() throws DBAccessorException
	{
		try
		{
			if (_connection != null && _connection.isClosed() == false)
			{
				_connection.close();
			}
			_connection = _db.getConnection();
			_statement = _connection.createStatement();
		}
		catch(SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
	}

	/**
	 * execute insert query
	 *
	 * @throws AddressBookException
	 */
	@Override
	public int executeInsert(String query) throws DBAccessorException
	{
		int id = 0;
		try
		{
			if(_connection == null)
			{
				makeConnection();
			}
			int rowsAffected = _statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			if(rowsAffected == 1)
			{
				ResultSet rs = _statement.getGeneratedKeys();
				if(rs.next())
				{
					id = rs.getInt(1);
				}
			}
		}
		catch(SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
		return id;
	}

	/**
	 * execute update,delete query
	 *
	 * @throws AddressBookException 
	 */
	@Override
	public int executeUpdate(String query) throws DBAccessorException
	{
		int rowsAffected = 0; 
		try
		{
			if(_connection == null)
			{
				makeConnection();
			}
			rowsAffected = _statement.executeUpdate(query);
		}
		catch(SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
		
		return rowsAffected;
	}

	/**
	 * execute select query
	 *
	 * @throws AddressBookException 
	 */
	@Override
	public ResultSet executeSelect(String query) throws DBAccessorException
	{
		ResultSet rs = null;
		try
		{
			if(_connection == null)
			{
				makeConnection();
			}
			rs = _statement.executeQuery(query);
		}
		catch(SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
		return rs;
	}

	/**
	 * clean up connection and statement obj
	 *
	 * @throws AddressBookException 
	 */
	@Override
	public void free() throws DBAccessorException
	{
		try
		{
			if (_connection != null && _connection.isClosed() == false)
			{
				_statement.close();
				_connection.close();
			}
			_connection = null;
			_statement = null;
		}
		catch(SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
		
	}

	/**
	 * begin transaction
	 *
	 * @throws AddressBookException 
	 */
	public void beginTransaction() throws DBAccessorException
	{
		if (_connection == null)
		{
			makeConnection();
		}
		setAutoCommit(false);
	}

	/**
	 * set auto commit
	 *
	 * @param isAutoCommit - set auto commit
	 * @throws AddressBookException 
	 */
	public void setAutoCommit(boolean isAutoCommit) throws DBAccessorException
	{
		try
		{
			_connection.setAutoCommit(isAutoCommit);
		}
		catch(SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
	}

	/**
	 * roll back transaction
	 *
	 * @throws AddressBookException 
	 */
	public void rollback() throws DBAccessorException
	{
		try
		{
			_connection.rollback();
		}
		catch (SQLException e)
		{
			String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
			throw new DBAccessorException(errmsg);
		}
		finally
		{
			setAutoCommit(true);
		}
	}

	/**
	 * commit transaction
	 *
	 * @throws AddressBookException 
	 */
	public void commit() throws DBAccessorException
	{
		if (_connection != null)
		{
			try
			{
				_connection.commit();
			}
			catch(SQLException e)
			{
				String errmsg = String.format(Definitions.SQL_EXCEPTION, e.getMessage());
				throw new DBAccessorException(errmsg);
			}
			finally
			{
				setAutoCommit(true);
			}
		}
		else
		{
			throw new DBAccessorException(Definitions.NULL_CONNECTION);
		}
	}
}