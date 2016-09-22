package com.linus.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class SQLExceptionUtil {
	/**
	 * Filter sql exception.
	 * @param e SQLException
	 * @return
	 */
	public static boolean ignoreSQLException(SQLException e) {
		if (e.getSQLState() == null) {
			System.out.println("The SQL state is not defined!");

			return false;
		}
			
		
		return false;
	}
	
	/**
	 * Print sql exception information: message, sql state, error code.
	 * @param e SQLException
	 */
	public static void printSQLException(SQLException e) {
		
		if (ignoreSQLException(e)) return;
		
		System.err.println("Message: " + e.getMessage());
		
		System.err.println("SQL State: " + e.getSQLState());
		
		System.err.println("Error Code: " + e.getErrorCode());

		Throwable t = e.getCause();
		while (t != null) {
			System.err.println("Cause: " + t);
			t = t.getCause();
		}
	}
	
	public static void printWarningsFromResultSet(ResultSet rs) throws SQLException {
		printWarnings(rs.getWarnings());
	}
	
	public static void printWarningsFromStatement(Statement s) throws SQLException {
		printWarnings(s.getWarnings());
	}
	
	/**
	 * Print sql warning infomation: message, sql state, error code.
	 * @param warning
	 */
	public static void printWarnings(SQLWarning warning) {
		if (warning != null) {
			System.out.println("\n---Warning---\n");
		}
		
		while (warning != null) {
			System.out.println("Message: " + warning.getMessage());
			System.out.println("SQLState: " + warning.getSQLState());
			System.out.println("Vendor error code: " + warning.getErrorCode());
			System.out.println("");  // a new blank line
			warning = warning.getNextWarning();
		}
	}
}
