package org.housejoy.common.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DataBaseSeleniumUtil {
	private String driverClass = null;
	private String userName = null;
	private String password = null;
	private String dbUrl = null;
	public static Connection conn = null;
	private ResultSet resultSet = null;
	public static Statement stmt = null;
	private int numberOfRowsInResult = 0;
	private boolean addMissingRows = true;
	private transient final static Log LOG = LogFactory.getLog(DataBaseSeleniumUtil.class.getName());
	public static String sqlQuery;
	
	/**
	 * Constructor
	 */
	public DataBaseSeleniumUtil() {
		try {
			connectDB();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}

	/**
	 * Connects to Database specified in build.{$environment}.properties file
	 * 
	 * @throws Exception
	 */
	private void connectDB() throws Exception {
		getDataBaseDetailsFromPropertyFile();
		Class.forName(driverClass).newInstance();
		try {
			conn = DriverManager.getConnection(dbUrl, userName, password);
			stmt = conn.createStatement();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}

	/**
	 * Gets the Database in use and other details related to Database from the
	 * build.{$environment}.properties file
	 * 
	 * @throws Exception
	 */
	private void getDataBaseDetailsFromPropertyFile() throws Exception {
		// reading all Database properties from file
		driverClass = null;
		userName = CommonTools.getProperty("database.username");
		password = CommonTools.getProperty("database.password");
		dbUrl = CommonTools.getProperty("database.connection.url");
		driverClass = CommonTools.getProperty("database.connection.driverclass");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * housejoy.in Database support DataBaseFixtureSupport#addMissingRowsInReport(
	 * java.lang.String)
	 */
	public void addMissingRowsInReport(String val) {
		if (val.equalsIgnoreCase("true")) {
			addMissingRows = true;
		} else if (val.equalsIgnoreCase("false")) {
			addMissingRows = false;
		} else {
			throw new IllegalArgumentException(
					"Please enter either true or false!!! By default it adds surplus records in report!");
		}
	}

	public boolean executeStatement(String query) {
		boolean status = false;
		try {
			stmt = conn.createStatement();

			/*
			 * PreparedStatement psmt = conn.prepareStatement(query);
			 * psmt.execute();
			 */
			// stmt.executeQuery(query);
			stmt.execute(query);
			status = true;
		} catch (Exception ex) {
			status = false;
			LOG.error(ex.getMessage());
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * housejoy.in Database support DataBaseFixtureSupport#updateQuery(java.lang.
	 * String)
	 */
	public boolean updateQuery(String query) {
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if (result > 0) {
				return true;
			}
			return false;
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return false;
	}

	public boolean deleteAllFromTable(String tableName) {
		try {
			StringBuilder queryString = new StringBuilder("delete").append(" from ").append(tableName);
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(queryString.toString());
			if (result >= 0) {
				return true;
			}
			return false;
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return false;
	}

	public int numberOfRowsReturnedforQueryAre(String queryString) {
		ResultSet resultSet = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(queryString);
			while (resultSet.next()) {
				++numberOfRowsInResult;
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// ignore exception
				}
			}
		}
		return numberOfRowsInResult;
	}

	public boolean executeUserDefinedQuery(String queryString) {
		try {
			stmt = conn.createStatement();
			stmt.execute(queryString);
			return true;
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return false;
	}

	public String executeQueryReturnResult(String queryString) throws SQLException {
		String result =null;
		stmt = conn.createStatement();
		resultSet= stmt.executeQuery(queryString);
		if (resultSet.next()) {
			result = resultSet.getString(1);
		}
		return result;
	}
	
	public void closeDBResources() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public String executeQueryTogetOrderID(String sColumnName, String CookieID) throws SQLException {

		String OrderID = null;

		try {
			resultSet = stmt.executeQuery(
					"SELECT " + sColumnName + " FROM  lead_master  WHERE LEAD_COOKIE_ID = " + CookieID + " ");

			while (resultSet.next())
				OrderID = resultSet.getString(1);
			resultSet.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * finally { if (stmt != null) { stmt.close(); } }
		 */

		return OrderID;
	}

	public ArrayList<String> executeQueryTogetPageContents(String jobID) {
		
		String sql2 = "select jm.id as jobID,uam.user_contact_name,uam.user_address1,uam.user_address2,stm.service_type_name, "
				+ "group_concat(CASE WHEN parent2.id IS NOT NULL THEN CONCAT('',parent2.service_type_name,' - ',parent1.service_type_name,' ','::',' ',jsd.display_name) "
				+ "WHEN parent1.id IS NOT NULL THEN CONCAT(parent1.service_type_name,' ','::',' ',jsd.display_name) "
				+ "ELSE IFNULL(jsd.display_name,'') END separator '\n')  AS subService, jm.job_scheduled_date, jd.coupon_code, jm.job_slot_time from job_master jm "
				+ "INNER JOIN user_address_master uam on uam.id=jm.addr_id "
				+ "INNER JOIN service_type_master stm on stm.id=jm.service_type_id "
				+ "LEFT JOIN job_discounts jd on jd.job_id=jm.id and jd.job_discount_status='A' "
				+ "LEFT JOIN job_service_details jsd on jsd.job_id = jm.id and (jsd.ordered_qty+jsd.updated_qty-jsd.cancelled_qty)>0 "
				+ "LEFT JOIN service_type_master child ON child.id = jsd.sub_service_id "
				+ "LEFT JOIN service_type_master parent1 ON parent1.id = child.parent_id AND parent1.id>20 "
				+ "LEFT JOIN service_type_master parent2 ON parent2.id = parent1.parent_id  AND parent2.id>20 "
				+ "where jm.id = " + jobID + "  group by jm.id ;";
		
		ArrayList<String> arrayList = new ArrayList<String>();

		try {
			resultSet = stmt.executeQuery(sql2);
			ResultSetMetaData metadata = resultSet.getMetaData();
			int numberOfColumns = metadata.getColumnCount();

			while (resultSet.next()) {
				int i = 1;
				while (i <= numberOfColumns) {
					arrayList.add(resultSet.getString(i++));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;

	}
	
public ArrayList<String> executeQueryTogetSemTableContents(String cookieID) {
		
		String sqlQueryForQA = "select utm_source,utm_medium,utm_content,utm_campaign,account,campaign ,adgroup,user_city "
				+ "from qa_bc_services_trn.sem_tracking where user_cookie = " + cookieID + ";";
		
		String sqlQueryForProd = "select utm_source,utm_medium,utm_content,utm_campaign,account,campaign ,adgroup,user_city "
				+ "from bc_services_trn.sem_tracking where user_cookie = " + cookieID + ";";
		
		if(userName.contentEquals("qa")){
			 sqlQuery=sqlQueryForQA;
		}
		else {
			sqlQuery=sqlQueryForProd;
		}
		
		ArrayList<String> arrayList = new ArrayList<String>();

		try {
			resultSet = stmt.executeQuery(sqlQuery);
			ResultSetMetaData metadata = resultSet.getMetaData();
			int numberOfColumns = metadata.getColumnCount();

			while (resultSet.next()) {
				int i = 1;
				while (i <= numberOfColumns) {
					arrayList.add(resultSet.getString(i++));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;

	}

public ArrayList<String> executeQueryTogetLeadMasterTableContents(String cookieID) {
	
	String sqlQueryForQA = "select SM.service_type_name, CM.city_name, LM.job_id, LM.lead_status, LM.lead_user_email_id, LM.lead_mobile_number, LM.lead_user_name "
			+ "from qa_bc_services_trn.lead_master as LM , qa_bc_services_trn.service_type_master  as SM , qa_bc_services_trn.city_master as CM "
			+ "where lead_cookie_id = " + cookieID + " AND SM.id = LM.service_type_id AND CM.id = LM.city_id;";
	
	String sqlQueryForProd = "select SM.service_type_name, CM.city_name, LM.job_id, LM.lead_status, LM.lead_user_email_id, LM.lead_mobile_number, LM.lead_user_name "
			+ "from bc_services_trn.lead_master as LM , bc_services_trn.service_type_master  as SM , bc_services_trn.city_master as CM "
			+ "where lead_cookie_id = " + cookieID + " AND SM.id = LM.service_type_id AND CM.id = LM.city_id;";
	
	if(userName.contentEquals("qa")){
		 sqlQuery=sqlQueryForQA;
	}
	else {
		sqlQuery=sqlQueryForProd;
	}
	
	ArrayList<String> arrayList = new ArrayList<String>();

	try {
		resultSet = stmt.executeQuery(sqlQuery);
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numberOfColumns = metadata.getColumnCount();

		while (resultSet.next()) {
			int i = 1;
			while (i <= numberOfColumns) {
				arrayList.add(resultSet.getString(i++));
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return arrayList;

}
}
