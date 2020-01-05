package owp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
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
				int sala = rset.getInt(index++);
				Date datumProjekcije = rset.getDate(index++);
				Time vremeProjekcije = rset.getTime(index++);
				double cenaKarte = rset.getDouble(index++);
				String admin = rset.getString(index++);
			
				projekcija = new Projekcija(idPro,film,tipProjekcije,sala,datumProjekcije,vremeProjekcije,cenaKarte,admin);
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
//	
//	public static List<Projekcija> getProjekcija(int id){
//		List<Projekcija> projekcija = new ArrayList<>();
//		
//		for(Projekcija pr : getAll(id)) {
//			if(pr.getId().equals(id)) {
//				projekcija.addAll(id);
//			}
//		}
//		
//		
//		return rezervacije;
//	}
	
	public static List<Projekcija> getAll(int id) {
		List<Projekcija> projekcije = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM projekcija WHERE "
					+ "id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			//id,film,tipProjekcije,sala,datumProjekcije,vremeProjekcije,cenaKarte,admin
			while (rset.next()) {
				index = 1;
				int idPro = rset.getInt(index++);
				String film = rset.getString(index++);
				String tipProjekcije = rset.getString(index++);
				int sala = rset.getInt(index++);
				Date datumProjekcije = rset.getDate(index++);
				Time vremeProjekcije = rset.getTime(index++);
				double cenaKarte = rset.getDouble(index++);
				String admin = rset.getString(index++);

				Projekcija projekcija = new Projekcija(id, film, tipProjekcije, sala, datumProjekcije, vremeProjekcije, cenaKarte, sadmin);
				projekcija.setId(rset.getInt(1));
				projekcija.setFilm(rset.getString(2));
				projekcija.setTipProjekcije(rset.getString(3));
				projekcija.setSala(rset.getInt(4));
				projekcija.setDatumPrikazivanja(rset.getDate(5));
				projekcija.setVremePrikazivanja(rset.getTime(6));
				projekcija.setCenaKarte(rset.getDouble(7));
				projekcija.setAdmin(rset.getString(8).toString());
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
			String query = "INSERT INTO projekcija (film, tipProjekcije, sala, datumProjekcije, vremeProjekcije, cenaKarte, admin) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";


			//id,film,tipProjekcije,sala,datumProjekcije,vremeProjekcije,cenaKarte,admin
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, projekcija.getFilm().getNaziv());
			pstmt.setString(index++, projekcija.getTipProjekcije().getNaziv());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setDate(index++, projekcija.getDatumPrikazivanja());
			pstmt.setTime(index++, projekcija.getVremePrikazivanja());
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
			pstmt.setString(index++, projekcija.getFilm().getNaziv());
			pstmt.setString(index++, projekcija.getTipProjekcije().getNaziv());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setDate(index++, projekcija.getDatumPrikazivanja());
			pstmt.setTime(index++, projekcija.getVremePrikazivanja());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdmin().getKorisnickoIme());
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
