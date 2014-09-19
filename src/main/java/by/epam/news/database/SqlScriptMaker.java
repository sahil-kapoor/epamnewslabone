package by.epam.news.database;

import java.util.List;
import java.util.ResourceBundle;


public class SqlScriptMaker {
	
	
	private static final String LOAD_ALL = "loadAll";
	private static final String LOAD_ONE = "loadOne";
	private static final String ADD = "add";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("by.epam.news.properties.SqlScripts");
	
	
	
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


	public static String getDelete(List<Integer> idList) {
		StringBuilder sql = new StringBuilder(bundle.getString(DELETE));
		sql.append("(");
		for(Integer id : idList){
			sql.append(id);
			sql.append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		return sql.toString();
	}
	
	
}
