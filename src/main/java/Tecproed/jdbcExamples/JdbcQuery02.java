package Tecproed.jdbcExamples;

import java.sql.*;

public class JdbcQuery02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDirever");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain",
				"kemal4141", "kemal4141");

		Statement st = con.createStatement();

		// Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.

		ResultSet bolumlerTablosu = st.executeQuery("SELECT * FROM bolumler");

		while (bolumlerTablosu.next()) {
			System.out.println(bolumlerTablosu.getInt(1) + " " + bolumlerTablosu.getString(2) + " "
					+ bolumlerTablosu.getString(3));
		}

		con.close();
		st.close();
		bolumlerTablosu.close();

	}

}
