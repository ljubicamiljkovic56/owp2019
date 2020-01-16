package owp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import owp.model.Karta;


public class KartaDAO {
	
	public static Karta get(int id) {
		Karta karta = null;

		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = 
					"SELECT * FROM karta WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			
			//id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik
			while (rset.next()) {
				int index = 1;
				int idKarte = rset.getInt(index++);
				String projekcija = rset.getString(index++);
				int sediste = rset.getInt(index++);
				Date datumProdaje = rset.getDate(index++);
				Time vremeProdaje = rset.getTime(index++);
				String korisnik = rset.getString(index++);
			
				karta = new Karta(idKarte,projekcija, sediste, datumProdaje, vremeProdaje,korisnik);
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return karta;
	}
	
	public static List<Karta> getAll(int id) {
		List<Karta> karte = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karta WHERE "
					+ "id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			//id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik
			
			while (rset.next()) {
				index = 1;
				int idKarte = rset.getInt(index++);
				String projekcija = rset.getString(index++);
				int sediste = rset.getInt(index++);
				Date datumProdaje = rset.getDate(index++);
				Time vremeProdaje = rset.getTime(index++);
				String korisnik = rset.getString(index++);

				Karta karta = new Karta(idKarte, projekcija, sediste, datumProdaje, vremeProdaje, korisnik);
				karta.setId(rset.getInt(1));
				karta.setProjekcija(rset.getString(2));
				karta.setSediste(rset.getInt(3));
				karta.setDatumProdaje(rset.getDate(4));
				karta.setVremeProdaje(rset.getTime(5));
				karta.setKorisnik(rset.getString(6));
				karte.add(karta);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return karte;
	}


	public static boolean add(Karta karta) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO karta (projekcija, sediste, datumProdaje, vremeProdaje, korisnik) "
					+ "VALUES (?, ?, ?, ?, ?)";


			//id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik
			
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, karta.getProjekcija().toString());
			pstmt.setInt(index++, karta.getSediste());
			pstmt.setDate(index++, karta.getDatumProdaje());
			pstmt.setTime(index++, karta.getVremeProdaje());
			pstmt.setString(index++, karta.getKorisnik().getKorisnickoIme());
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}

	public static boolean update(Karta karta) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE karta SET projekcija = ?, sediste = ?, datumProdaje = ?, vremeProdaje = ?, korisnik = ? "
					+ "WHERE id = ?";

			//id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, karta.getProjekcija().toString());
			pstmt.setInt(index++, karta.getSediste());
			pstmt.setDate(index++, karta.getDatumProdaje());
			pstmt.setTime(index++, karta.getVremeProdaje());
			pstmt.setString(index++, karta.getKorisnik().getKorisnickoIme());
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}

	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM karta WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}

}

