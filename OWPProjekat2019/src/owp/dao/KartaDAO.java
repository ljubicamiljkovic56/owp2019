package owp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import owp.model.Karta;

public class KartaDAO {
	
	//za update koristimo get po id-u
	public static Karta get(int id) {
		Karta karta = null;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = 
					"SELECT projekcija, sediste, datumProdaje, vremeProdaje, korisnik FROM karta WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			
			if (rset.next()) {
				String projekcija = rset.getString(1);
				int sediste = rset.getInt(2);
				Date datumProdaje = rset.getDate(3);
				String vremeProdaje = rset.getString(4);
				String korisnik = rset.getString(5);
				
				karta =  new Karta(id,projekcija, sediste, datumProdaje, vremeProdaje, korisnik);
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
	//u KorisnikovaKartaServlet pozivamo da dobijemo kartu/karte logovanog korisnika
	public static List<Karta> getKorisnikovaKarta(String korisnik) {
		Connection conn = ConnectionManager.getConnection();
		List<Karta> karta1 = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT id, projekcija, sediste, datumProdaje, vremeProdaje FROM karta WHERE korisnik = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnik);
			System.out.println(pstmt);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				//int index = 1;
				int id = rset.getInt("id");
				String projekcija = rset.getString("projekcija");
				int sediste = rset.getInt("sediste");
				Date datumProdaje = rset.getDate("datumProdaje");
				String vremeProdaje = rset.getString("vremeProdaje");
				
				Karta karta = new Karta(id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik);
				karta.setId(id);
				karta.setProjekcija(projekcija);
				karta.setSediste(sediste);
				karta.setDatumProdaje(datumProdaje);
				karta.setVremeProdaje(vremeProdaje);
				karta.setKorisnik(korisnik);
				karta1.add(karta);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return karta1;
	}
	
	//pregled karata sa filterima
	public static List<Karta> getAllKarta(String projekcija, int sedisteV, int sedisteN, String datumProdaje, String vremeProdaje, String korisnik){
		List<Karta> karte = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karta WHERE projekcija LIKE ? AND datumProdaje LIKE ? AND vremeProdaje LIKE ? AND korisnik LIKE ? ";
			
			if (sedisteV > 0) {
				query += " AND sediste >= ?";
			}
			if (sedisteN > 0) {
				query += " AND sediste <= ?";
			}
			
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, "%" + projekcija + "%");
			pstmt.setString(index++, datumProdaje + "%");
			pstmt.setString(index++, vremeProdaje + "%");
			pstmt.setString(index++, korisnik + "%");
			
			if (sedisteV > 0) {
				pstmt.setInt(index++, sedisteV);
			}
			if (sedisteN > 0) {
				pstmt.setInt(index++, sedisteN);
			}
			
			rset = pstmt.executeQuery();
			
			System.out.println("nakon execute query");
			System.out.println(query);
			
			while(rset.next()) {
				int id = rset.getInt("id");
				String projekcija1 = rset.getString("projekcija");
				int sediste1 = rset.getInt("sediste");
				Date datumProdaje1 = rset.getDate("datumProdaje");
				String vremeProdaje1 = rset.getString("vremeProdaje");
				String korisnik1 = rset.getString("korisnik");
				
				Karta karta = new Karta(id, projekcija1, sediste1, datumProdaje1, vremeProdaje1, korisnik1);
				karta.setId(id);
				karta.setProjekcija(projekcija1);
				karta.setSediste(sediste1);
				karta.setDatumProdaje(datumProdaje1);
				karta.setVremeProdaje(vremeProdaje1);
				karta.setKorisnik(korisnik1);
				karte.add(karta);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return karte;
		
	}
	
	
	//pregled karata
	public static List<Karta> getAll() {
		List<Karta> karte = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karta ";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				int idKarte = rset.getInt("id");
				String projekcija = rset.getString("projekcija");
				int sediste = rset.getInt("sediste");
				Date datumProdaje = rset.getDate("datumProdaje");
				String vremeProdaje = rset.getString("vremeProdaje");
				String korisnik = rset.getString("korisnik");

				Karta karta = new Karta(idKarte, projekcija, sediste, datumProdaje, vremeProdaje, korisnik);
				karta.setId(idKarte);
				karta.setProjekcija(projekcija);
				karta.setSediste(sediste);
				karta.setDatumProdaje(datumProdaje);
				karta.setVremeProdaje(vremeProdaje);
				karta.setKorisnik(korisnik);
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

	
	//poslednji dodati id u bazi
	protected static int getInsertedId(Connection conn) throws SQLException {
		String query = "SELECT last_insert_rowid();";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		int retVal = -1;
		if (rset.next())
			retVal = rset.getInt(1);
		rset.close();
		stmt.close();
		return retVal;
	}
	

	//dodavanje tj. kupovina karte
	public static boolean add(Karta karta) {
		Connection conn = ConnectionManager.getConnection();
		
		boolean retVal = false;

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO karta (projekcija, sediste, datumProdaje, vremeProdaje, korisnik) "
					+ "VALUES (?, ?, ?, ?, ?)";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, karta.getProjekcija());
			pstmt.setInt(index++, karta.getSediste());
			pstmt.setDate(index++, karta.getDatumProdaje());
			pstmt.setString(index++, karta.getVremeProdaje());
			pstmt.setString(index++, karta.getKorisnik());
			System.out.println(pstmt);

			if (pstmt.executeUpdate() == 1) {
				retVal = true;
				karta.setId(getInsertedId(conn));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}

	//izmenu karte ne koristim
	public static boolean update(Karta karta) {
		Connection conn = ConnectionManager.getConnection();
		
		boolean retVal = false;

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE karta SET projekcija = ?, sediste = ?, datumProdaje = ?, vremeProdaje = ?, korisnik = ? "
					+ "WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, karta.getProjekcija());
			pstmt.setInt(index++, karta.getSediste());
			pstmt.setDate(index++, karta.getDatumProdaje());
			pstmt.setString(index++, karta.getVremeProdaje());
			pstmt.setString(index++, karta.getKorisnik());
			System.out.println(pstmt);

			if (pstmt.executeUpdate() == 1) {
				retVal = true;
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}

	//brisanje karte po id-u
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

