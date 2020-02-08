package owp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import owp.model.Projekcija;

public class ProjekcijaDAO {
	
	//uzimanje projekcije po id-u
	public static Projekcija get(int id) {
		Projekcija projekcija = null;

		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = 
					"SELECT * FROM projekcija WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			
			//id, film, tipprojekcije, sala, datum, vreme, cena, admin
			while (rset.next()) {
				int index = 1;
				int idPro = rset.getInt(index++);
				String film = rset.getString(index++);
				String tipProjekcije = rset.getString(index++);
				String sala = rset.getString(index++);
				Date datumPrikazivanja = rset.getDate(index++);
				String vremePrikazivanja = rset.getString(index++);
				double cenaKarte = rset.getDouble(index++);
				String admin = rset.getString(index++);
			
				projekcija = new Projekcija(idPro, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin);
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projekcija;
	}
	
	//prikaz svih projekcija
	public static List<Projekcija> getAll() {
		List<Projekcija> projekcije = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM projekcija ";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int id1 = rset.getInt("id");
				String film = rset.getString("film");
				String tipProjekcije = rset.getString("tipProjekcije");
				String sala = rset.getString("sala");
				Date datumPrikazivanja = rset.getDate("datumPrikazivanja");
				String vremePrikazivanja = rset.getString("vremePrikazivanja");
				double cenaKarte = rset.getDouble("cenaKarte");
				String admin = rset.getString("admin");
				

				Projekcija projekcija = new Projekcija(id1, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin);
				projekcija.setId(id1);
				projekcija.setFilm(film);
				projekcija.setTipProjekcije(tipProjekcije);
				projekcija.setSala(sala);
				projekcija.setDatumPrikazivanja(datumPrikazivanja);
				projekcija.setVremePrikazivanja(vremePrikazivanja);
				projekcija.setCenaKarte(cenaKarte);
				projekcija.setAdmin(admin);
				projekcije.add(projekcija);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projekcije;
	}
	
	//prikaz svih projekcija i kod korisnika i kod admina sa filterom
	public static List<Projekcija> getAllProjekcija(String film, String tipProjekcije, String sala, String datumPrikazivanja,
			 String vremePrikazivanja,  double cenaKarteV, double cenaKarteN, String admin){
		
		List<Projekcija> projekcije = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM projekcija WHERE film LIKE ? AND tipProjekcije LIKE ? AND sala LIKE ? "
					+ "AND datumPrikazivanja LIKE ? AND vremePrikazivanja LIKE ? AND cenaKarte >= ? AND cenaKarte <= ? AND admin LIKE ?";
			
					
//		if (!datumPrikazivanja.equals("")) {
//			query += " AND datumPrikazivanja LIKE ?";
//		}
		
		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setString(index++, "%" + film + "%");
		pstmt.setString(index++, tipProjekcije + "%");
		pstmt.setString(index++, sala + "%");
		pstmt.setString(index++, datumPrikazivanja + "%");
		pstmt.setString(index++, vremePrikazivanja + "%");
		pstmt.setDouble(index++, cenaKarteV);
		pstmt.setDouble(index++, cenaKarteN);
		pstmt.setString(index++, admin + "%");
//		
//		if (!datumPrikazivanja.equals("")) {
//			pstmt.setString(index++, datumPrikazivanja + "%");
//		}
//		
		System.out.println(pstmt);
		System.out.println(pstmt + " evo statementa");

		rset = pstmt.executeQuery();
		
		System.out.println(pstmt.executeQuery() + "  jel ovo execute query");

		System.out.println("nakon execute query");
		System.out.println(query);
		
		while(rset.next()) {
			int id = rset.getInt("id");
			String filmP = rset.getString("film");
			String tipProjekcijeP = rset.getString("tipProjekcije");
			String salaP = rset.getString("sala");
			Date datumPrikazivanjaP = rset.getDate("datumPrikazivanja");
			String vremePrikazivanjaP = rset.getString("vremePrikazivanja");
			Double cenaKarteP = rset.getDouble("cenaKarte");
			String adminP = rset.getString("admin");
			
			Projekcija projekcija = new Projekcija(id,filmP, tipProjekcijeP, salaP, datumPrikazivanjaP, vremePrikazivanjaP, cenaKarteP, adminP);
			projekcija.setId(id);
			projekcija.setFilm(filmP);
			projekcija.setTipProjekcije(tipProjekcijeP);
			projekcija.setSala(salaP);
			projekcija.setDatumPrikazivanja(datumPrikazivanjaP);
			projekcija.setVremePrikazivanja(vremePrikazivanjaP);
			projekcija.setCenaKarte(cenaKarteP);
			projekcija.setAdmin(adminP);
			projekcije.add(projekcija);	


			}
			System.out.println("zasto nema ispisa?");
					
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projekcije;
		
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

	//dodavanje projekcije
	public static boolean add(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();
		boolean retVal = false;
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO projekcija (film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
		//	pstmt.setInt(index++, film.getId());
			pstmt.setString(index++, projekcija.getFilm());
			pstmt.setString(index++, projekcija.getTipProjekcije());
			pstmt.setString(index++, projekcija.getSala());
			pstmt.setDate(index++, projekcija.getDatumPrikazivanja());
			pstmt.setString(index++, projekcija.getVremePrikazivanja());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdmin());
			System.out.println(pstmt);

			//return pstmt.executeUpdate() == 1;
			
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
				projekcija.setId(getInsertedId(conn));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}
	
	
	//izmena projekcije
	public static boolean update(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();

		boolean retVal = false;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE projekcija SET film = ?, tipProjekcije = ?, sala = ?, datumPrikazivanja = ?, vremePrikazivanja = ?, cenaKarte = ?, admin = ?"
					+ "WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, projekcija.getFilm());
			pstmt.setString(index++, projekcija.getTipProjekcije());
			pstmt.setString(index++, projekcija.getSala());
			pstmt.setDate(index++, projekcija.getDatumPrikazivanja());
			pstmt.setString(index++, projekcija.getVremePrikazivanja());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdmin());
			pstmt.setInt(index++, projekcija.getId());
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

	//brisanje projekcije
	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM projekcija WHERE id = ?";

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
