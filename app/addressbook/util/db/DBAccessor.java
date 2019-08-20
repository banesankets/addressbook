package addressbook.util.db;

import java.sql.ResultSet;

import addressbook.exception.AddressBookException;

/**
 * DBAccessor Interface
 */
public interface DBAccessor
{
	/**
	 * Execute insert query
	 *
	 * @param query - insert query
	 * @return
	 * @throws AddressBookException 
	 */
	public int executeInsert(String query) throws DBAccessorException;

	/**
	 * Execute update, delete query
	 *
	 * @param query - update/delete query
	 * @return
	 */
	public int executeUpdate(String query)throws DBAccessorException;

	/**
	 * execute select query
	 *
	 * @param query - select query
	 * @return
	 * @throws AddressBookException 
	 */
	public ResultSet executeSelect(String query) throws DBAccessorException;

	/**
	 * clean up connection and statement obj
	 * @throws AddressBookException 
	 */
	public void free() throws DBAccessorException;

	/**
	 * begin transaction
	 *
	 * @throws AddressBookException 
	 */
	public void beginTransaction() throws DBAccessorException;

	/**
	 * set auto commit
	 *
	 * @param isAutoCommit - set auto commit
	 * @throws AddressBookException 
	 */
	public void setAutoCommit(boolean isAutoCommit) throws DBAccessorException;

	/**
	 * roll back transaction
	 * 
	 * @throws AddressBookException 
	 */
	public void rollback() throws DBAccessorException;

	/**
	 * commit transaction
	 * @throws AddressBookException 
	 */
	public void commit() throws DBAccessorException;
}