package Tecproed.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jdbc5CRD {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String yol = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection(yol, "suleyman", "suleyman63");

		Statement st = con.createStatement();

		/*
		 * =======================================================================
		 * ORNEK1: urunler adinda bir tablo olusturalim id(NUMBER(3), isim VARCHAR2(10)
		 * fiyat NUMBER(6,2)
		 * ========================================================================
		 */

		/*
		 * String createQuery = "CREATE TABLE urunler(" + "id NUMBER(3)," +
		 * "isim VARCHAR(10)," + "fiyat NUMBER(7,2))";
		 * 
		 * st.execute(createQuery); System.out.println("urunler tablosu olusturuldu..");
		 */

		/*
		 * =======================================================================
		 * ORNEK2: urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim
		 * ========================================================================
		 */
		
		
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir.
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari
		// icin) bir yontemdir.
		// Bunun icin;
		// 1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		// 2) POJO Class nesnelerini saklayacak bir collection olusturulur
		// 3) bir dongu ile kayitlar eklenir.

		
		
		List<Urun> kayitlar = new ArrayList<>();
		kayitlar.add(new Urun(101, "Laptop", 6500));
		kayitlar.add(new Urun(102, "PC", 4500));
		kayitlar.add(new Urun(103, "Telefon", 4500));
		kayitlar.add(new Urun(104, "Anakart", 1500));
		kayitlar.add(new Urun(105, "Klavye", 200));
		kayitlar.add(new Urun(106, "Fare", 100));

		String insertQuery = "INSERT INTO urunler VALUES(?,?,?)";

		PreparedStatement pst = con.prepareStatement(insertQuery);

		for (Urun each : kayitlar) {

			pst.setInt(1, each.getId());
			pst.setString(2, each.getIsim());
			pst.setDouble(3, each.getFiyat());

			pst.addBatch();

		}
		
		// int[] sonuc = pst.executeBatch();
		// System.out.println(sonuc.length + "Kayit eklendi");

		/*
		 * =======================================================================
		 * ORNEK3: urunler tablosundaki PC’nin fiyatini %10 zam yapiniz
		 * ========================================================================
		 */
		   
		/*
		
				String updateQuery1= "UPDATE urunler "
				+ "SET fiyat= fiyat * 1.1 "
				+ "WHERE isim='PC'";
		    
            int s1 = st.executeUpdate(updateQuery1);
            System.out.println(s1 + "satir guncellendi");
            
            */
             
		 /*
		 * =======================================================================
		 * ORNEK4: urunler tablosuna 107, Monitor, 1250 sekilnde bir kayit ekleyiniz
		 * ========================================================================
		 */
            
			// String updateQuery1= "INSERT INTO urunler VALUES(107,'Monitor',1250)";

           //  int s4 = st.executeUpdate(updateQuery1);
           //  System.out.println(s4 + "monitor eklendi");
            		
            
		/*
		 * =======================================================================
		 * ORNEK5: urunler tablosundaki Klavyeyi siliniz.
		 * ========================================================================
		 */

			// String deleteQuery1 = "DELETE FROM urunler WHERE isim = 'Klavye'";
			// int s5 = st.executeUpdate(deleteQuery1);
			// System.out.println(s5 + "klavye silindi...");
		/*
		 * =======================================================================
		 * ORNEK6: urunler tablosuna Marka adinda ve Default degeri ASUS olan yeni bir
		 * sutun ekleyiniz.
		 * ========================================================================
		 */
		
			// String alterQuery="ALTER TABLE urun ADD marka VARCHAR2(10) DEFAULT 'Asus' ";
			// st.executeUpdate(alterQuery);
			// System.out.println("sutun eklenmistir...");

		/*
		 * =======================================================================
		 * ORNEK7: urunler tablosundaki kayitlari sorgulayiniz.
		 * ========================================================================
		 */
           
			// ResultSet rs=st.executeQuery("SELECT * FROM urun");
			// while(rs.next()) {
            // System.out.println("ID: " + rs.getInt(1)+" "+"Isim:" + rs.getString(2)+" "+ "Fiyat: "+rs.getDouble(3)+" "+"Marka: "+rs.getString(4));
           
        
		/*
		 * =======================================================================
		 * ORNEK8: urunler tablosunu siliniz.
		 * ========================================================================
		 */
           // String dropQuery="DROP TABLE urun";
            
            // st.execute(dropQuery);
            // System.out.println("Tablo silindi");

		
		con.close();
		st.close();
	}

}
