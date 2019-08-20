package addressbook.daoimpl;

import java.io.FileInputStream;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.mssql.InsertIdentityOperation;

import addressbook.exception.AddressBookException;
import addressbook.utility.Definitions;

/**
 * TableRegister class
 */
public class TableRegister
{
	private static final String	DB_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static final String	DB_URL = "jdbc:jtds:sqlserver";
	private static final String	USERNAME = "sa";
	private static final String	PASSWORD = "boss";
	private static final String	SCHEMA = "dbo";
	private static final String URL_FMT = "%s://%s:%d/%s";

	/**
	 * IDatabaseTester obj
	 */
	private IDatabaseTester _databaseTester;

	/**
	 * constructor
	 *
	 * @param ipAddr - ip address of db
	 * @param portNo - port number of db
	 * @param dbName - database name
	 * @throws ClassNotFoundException
	 */
	public TableRegister(String ipAddr, int portNo, String dbName) throws ClassNotFoundException
	{
		String url = String.format(URL_FMT, DB_URL, ipAddr, portNo, dbName);
		_databaseTester = new JdbcDatabaseTester(
				DB_DRIVER,
				url,
				USERNAME, PASSWORD, SCHEMA);
	}

	/**
	 * loads specified xls file into database
	 *
	 * @param xlsPath - path of xls file
	 * @throws AddressBookException
	 */
	public void load(String xlsPath) throws AddressBookException
	{
		try
		{
			IDataSet dataSet = new XlsDataSet(new FileInputStream(xlsPath));
			_databaseTester.setDataSet(dataSet);
			_databaseTester.setSetUpOperation(InsertIdentityOperation.CLEAN_INSERT);
			_databaseTester.onSetup();
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.IDATABASE_TESTER_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
	}

	/**
	 * returns specified table
	 *
	 * @param tableName - name of table
	 * @return
	 * @throws AddressBookException
	 */
	public ITable getTableDataFromTable(String tableName) throws AddressBookException 
	{
		try
		{
			IDatabaseConnection connection = _databaseTester.getConnection();
			IDataSet dataSet = connection.createDataSet();
			ITable table = dataSet.getTable(tableName);
			return table;
		}
		catch(Exception e)
		{
			String errmsg = String.format(Definitions.IDATABASE_CONNECTION_EXCEPTION + e.getMessage());
			throw new AddressBookException(errmsg);
		}
	}

	/**
	 * returns output table of specified query
	 *
	 * @param name - name of table
	 * @param query - query
	 * @return
	 * @throws Exception
	 */
	public ITable getTableDataFromQuery(String name, String query) throws Exception
	{
		IDatabaseConnection connection = _databaseTester.getConnection();
		ITable table = connection.createQueryTable(name, query);
		return table;
	}
}