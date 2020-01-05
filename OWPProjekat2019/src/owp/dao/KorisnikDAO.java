package owp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;

public class KorisnikDAO {
	
	public static Korisnik get(String korisnickoIme, String lozinka) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM korisnik WHERE korisnickoIme = ? AND lozinka = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnickoIme);
			pstmt.setString(index++, lozinka);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			
			if (rset.next()) {
				int id = rset.getInt("id");
				String korisnickoIme1 = rset.getString("korisnickoIme");
				String lozinka1 = rset.getString("lozinka");
				String datumRegString = rset.getString("datumReg");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				java.sql.Date datumReg = new java.sql.Date(sdf.parse(datumRegString).getTime());
				Uloga uloga = Uloga.valueOf(rset.getString("uloga"));

				return new Korisnik(id, korisnickoIme1,lozinka1, datumReg, uloga);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}

		return null;
	}
	
	public static Korisnik get(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT lozinka, datumReg, uloga FROM korisnici WHERE korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int id1 = rset.getInt(index++);
				String korisnickoIme = rset.getString(index++);
				String lozinka = rset.getString(index++);
				String datumRegString = rset.getString("datumReg");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				java.sql.Date datumReg = new java.sql.Date(sdf.parse(datumRegString).getTime());
				Uloga uloga = Uloga.valueOf(rset.getString(index++));
				

				return new Korisnik(id1, korisnickoIme,lozinka,datumReg,uloga);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}

		return null;
	}
	
	public static Korisnik get(String korisnickoIme) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT lozinka, datumReg, uloga FROM korisnik WHERE korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String lozinka = rset.getString(index++);
				String datumRegString = rset.getString("datumReg");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				java.sql.Date datumReg = new java.sql.Date(sdf.parse(datumRegString).getTime());
				Uloga uloga = Uloga.valueOf(rset.getString(index++));

				return new Korisnik(id, korisnickoIme,lozinka,datumReg,uloga);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}

		return null;
	}
//
//	public static List<Korisnik> getAll() {
//		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
//		String query = "SELECT id, korisnickoIme, lozinka, datumReg, uloga FROM korisnik";
//
//		Connection conn = ConnectionManager.getConnection();
//		
//		PreparedStatement pstmt;
//		try {
//			pstmt = conn.prepareStatement(query);
//
//			ResultSet rset = pstmt.executeQuery();
//			
//			if (rset.next()) {
//				int index = 1;
//				int id = rset.getInt(index++);
//				String lozinka = rset.getString(index++);
//				String datumRegString = rset.getString("datumReg");
//				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
//				java.sql.Date datumReg = new java.sql.Date(sdf.parse(datumRegString).getTime());
//				Uloga uloga = Uloga.valueOf(rset.getString(index++));
//
//				return (List<Korisnik>) new Korisnik(id, korisnickoIme,lozinka,datumReg,uloga);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return korisnici;
//	}
//	
	public static List<Korisnik> getAll(){
		List<Korisnik> korisnici = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM korisnik ";
			
			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int id1 = rset.getInt("id");
				String korisnickoIme1 = rset.getString("korisnickoIme");
				String lozinka1 = rset.getString("lozinka");
				String datumRegString = rset.getString("datumReg");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				java.sql.Date datumReg1 = new java.sql.Date(sdf.parse(datumRegString).getTime());
				Uloga uloga1 = Uloga.valueOf(rset.getString("uloga"));
		
				Korisnik korisnik = new Korisnik(id1, korisnickoIme1, lozinka1, datumReg1, uloga1);
				korisnik.setId(id1);
				korisnik.setKorisnickoIme(korisnickoIme1);
				korisnik.setLozinka(lozinka1);
				korisnik.setDatumReg(datumReg1);
				korisnik.setUloga(uloga1);
				korisnici.add(korisnik);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return korisnici;
		
	}
	
	public static boolean add(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO korisnici (korisnickoIme, lozinka, datumReg, uloga) "
					+ "VALUES (?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setDate(index++, korisnik.getDatumReg());
			pstmt.setString(index++, Uloga.korisnik.toString());
			
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

	public static boolean update(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnik SET korisnickoIme = ?, lozinka = ?, uloga = ?"
						+ "WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setDate(index++, korisnik.getDatumReg());
			pstmt.setString(index++, korisnik.getUloga().toString());
			System.out.println(pstmt);
			
			return pstmt.executeUpdate() == 1;
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static boolean delete(String id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM korisnik WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static void add(String korisnickoIme, String lozinka) {
		
		
	}

}
