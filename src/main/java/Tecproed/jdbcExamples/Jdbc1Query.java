package Tecproed.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1Query {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1-) ilgili driveri yuklemeliyiz

		Class.forName("oracle.jdbc.driver.OracleDirever");

		// 2-) baglanti olusturmaliyiz

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain", "suleyman", "suleyman63");

		// 3-) SQL Komutlari icin bir statment ifadesi olustur

		Statement st = con.createStatement();

		// 4-) SQL ifadelerini yazabiliriz.

		// (Personel tablosundaki personel_id’si 7369 olan personelin adini
		// sorgulayiniz)

		ResultSet isim = st.executeQuery("SELECT personel_isim FROM personel WHERE personel_id = 7369");

		// 5-) sonuclari aldik ve isledik

		while (isim.next()) {
			System.out.println("personel isim: " + isim.getString("personel_isim"));

		}

		// 6-) olusturulan nesneleri bellekten kaldiralim.
		con.close();
		st.close();
		isim.close();

	}

}
