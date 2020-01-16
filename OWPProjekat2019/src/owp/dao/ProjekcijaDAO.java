package owp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import owp.model.Projekcija;

public class ProjekcijaDAO {
	
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
				Date datumIVremeProjekcije = rset.getDate(index++);
				double cenaKarte = rset.getDouble(index++);
				String admin = rset.getString(index++);
			
				projekcija = new Projekcija(idPro, film, tipProjekcije, sala, datumIVremeProjekcije, cenaKarte, admin);
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
				Date datumIVremePrikazivanja = rset.getDate("datumIVremePrikazivanja");
				double cenaKarte = rset.getDouble("cenaKarte");
				String admin = rset.getString("admin");
				
			
//				PreparedStatement pstmt2 = null;
//				ResultSet rset2 = null;
//				try {
//					String query2 = "SELECT * FROM film WHERE naziv = '\" + film + \"'\" ";
//						
//					pstmt = conn.prepareStatement(query);
//					System.out.println(pstmt);
//
//					rset = pstmt.executeQuery();
//						
//					if (rset.next()) {
//							
//					String naziv = rset.getString(1);
//					String reziser = rset.getString(2);
//					String glumci = rset.getString(3);
//					String zanrovi = rset.getString(4);
//					int trajanje = rset.getInt(5);
//					String distributer = rset.getString(6);
//					String zemljaPorekla = rset.getString(7);
//					int godinaProizvodnje = rset.getInt(8);
//					String opis = rset.getString(9);
//							
//					Film film = new Film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
//						}
//					}		
//					catch (Exception ex) {
//						ex.printStackTrace();
//					} finally {
//						try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
//						try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
//						try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
//					}
//					
//				}
				

				Projekcija projekcija = new Projekcija(id1, film, tipProjekcije, sala, datumIVremePrikazivanja, cenaKarte, admin);
				projekcija.setId(id1);
				projekcija.setFilm(film);
				projekcija.setTipProjekcije(tipProjekcije);
				projekcija.setSala(sala);
				projekcija.setDatumIVremePrikazivanja(datumIVremePrikazivanja);
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


	public static boolean add(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO projekcija (film, tipProjekcije, sala, datumProjekcije, cenaKarte, admin) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";


			//id,film,tipProjekcije,sala,datumProjekcije,vremeProjekcije,cenaKarte,admin
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, projekcija.getFilm());
			pstmt.setString(index++, projekcija.getTipProjekcije());
			pstmt.setString(index++, projekcija.getSala());
			pstmt.setDate(index++, projekcija.getDatumIVremePrikazivanja());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdmin().toString());
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

	public static boolean update(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE projekcija SET film = ?, tipProjekcije = ?, sala = ?, datumProjekcije = ?, vremeProjekcije = ?, cenaKarte = ?, admin = ? "
					+ "WHERE id = ?";

			//id,film,tipProjekcije,sala,datumProjekcije,vremeProjekcije,cenaKarte,admin
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, projekcija.getFilm());
			pstmt.setString(index++, projekcija.getTipProjekcije());
			pstmt.setString(index++, projekcija.getSala());
			pstmt.setDate(index++, projekcija.getDatumIVremePrikazivanja());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdmin());
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
