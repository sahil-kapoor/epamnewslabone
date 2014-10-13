package by.epam.news.database;

import java.util.ResourceBundle;

/**
 * Help class.
 * 
 * Help dao to get sql code from properties fales. 
 * @author Alexander_Demeshko
 *
 */
public class SqlScriptMaker {
	
	
	private static final String LOAD_ALL = "loadAll";
	private static final String LOAD_ONE = "loadOne";
	private static final String ADD = "add";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("by.epam.news.properties.database.SqlScripts");
	
	
	
	public static String getLoadAll(){
		return bundle.getString(LOAD_ALL);
	}


	public static String getLoadOne(int id) {
		return bundle.getString(LOAD_ONE)+id;
	}


	public static String getAdd() {
		return bundle.getString(ADD);
	}


	public static String getUpdate() {
		return bundle.getString(UPDATE);
	}


	/**
	 * Get Delete.
	 * 
	 * Return delete script. Script generating from array.
	 * @param ids
	 * @return SQL string
	 */
	public static String getDelete(Integer[] ids) {
		StringBuilder sql = new StringBuilder(bundle.getString(DELETE));
		sql.append("(");
		for(int id : ids){
			sql.append(id);
			sql.append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		return sql.toString();
	}
	
	
}
