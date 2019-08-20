package addressbook.util.db;

import play.db.DBApi;
import play.db.Database;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * DBAccessorFactory class
 */
@Singleton
public class DBAccessorFactory
{
	/**
	 * DBApi obj
	 */
	private DBApi _dbapi;

	/**
	 * Constructor
	 *
	 * @param dbapi - DBApi obj
	 */
	@Inject
	DBAccessorFactory(DBApi dbapi)
	{
		_dbapi = dbapi;
	}

	/**
	 * returns DBAccessor instance with specified DB
	 * @param dbName
	 * @return
	 */
	public DBAccessor getInstance(String dbName)
	{
		Database db= _dbapi.getDatabase(dbName);
		DBAccessorImpl accessor = new DBAccessorImpl(db);
		return accessor;
	}
}