package studentsmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBM {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// 사용 디비파일 정의
	private final String DB_NAME_MSG = "logdata.db";

	public DBM() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME_MSG);
			System.out.println("Opened database successfully(" + DB_NAME_MSG + ")");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public boolean createTable() {
		String query = "create table if not exists student (" + "name varchar(50) not null,"
				+ "number varchar(50) primary key not null," + "dept varchar(50) not null);";

		updateQuery(query);

		System.out.println("imported table successfully");
		return true;
	}

	public boolean closeDB() {
		try {
			close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ResultSet executeQuery(String query, Object... args) {
		try {
			stmt = conn.prepareStatement(query);

			int cnt = 1;
			for (Object i : args) {
				if (i instanceof Integer)
					stmt.setInt(cnt, (int) i);
				else if (i instanceof Double)
					stmt.setDouble(cnt, (double) i);
				else if (i instanceof String)
					stmt.setString(cnt, (String) i);
				cnt++;
			}

			rs = stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public int updateQuery(String query, String... args) {
		int result = 0;

		try {
			stmt = conn.prepareStatement(query);

			int cnt = 1;
			for (Object i : args) {
				if (i instanceof Integer)
					stmt.setInt(cnt, (int) i);
				else if (i instanceof Double)
					stmt.setDouble(cnt, (double) i);
				else if (i instanceof String)
					stmt.setString(cnt, (String) i);
				cnt++;
			}

			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void close() {
		try {
			if (rs != null)
				if (!rs.isClosed())
					rs.close();
			if (stmt != null)
				if (!stmt.isClosed())
					stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
