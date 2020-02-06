package owp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;

public class KorisnikDAO {
	
	//pri logovanju, poziva se u LoginServletu
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
				Date datumReg = rset.getDate("datumReg");
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
	
	//u ProfilKorisnikaServlet se poziva da bismo dobili korisnika
	public static Korisnik getKorisnickoIme(String korisnickoIme) {
		Connection conn = ConnectionManager.getConnection();
		Korisnik korisnik = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT id, lozinka, datumReg, uloga FROM korisnik WHERE korisnickoIme = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String korisnickoIme1 = rset.getString(index++);
				String lozinka = rset.getString("lozinka");
				Date datumReg = rset.getDate("datumReg");
				Uloga uloga = Uloga.valueOf(rset.getString("uloga"));
				
				korisnik = new Korisnik(id, korisnickoIme1, lozinka, datumReg, uloga);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return korisnik;
	}
	
	//get id u servletu se koristi pre update-a
	public static Korisnik get(int id) {
		Connection conn = ConnectionManager.getConnection();
		Korisnik korisnik = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT korisnickoIme, lozinka, datumReg, uloga FROM korisnik WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				String korisnickoIme = rset.getString(1);
				String lozinka = rset.getString(2);
				Date datumReg = rset.getDate(3);
				Uloga uloga = Uloga.valueOf(rset.getString(4));
				
				korisnik =  new Korisnik(id,korisnickoIme,lozinka,datumReg,uloga);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}

		return korisnik;
	}
	
	//get po korisnickom imenu kod provere da li vec postoji korisnicko ime pri registraciji
	// i pri logovanju
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
				Date datumReg = rset.getDate("datumReg");
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

	//prikaz svih korisnika kod admina
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
				Date datumReg1 = rset.getDate("datumReg");
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
	
	//registracija, tj dodavanje korisnika
	public static boolean add(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();
		
		boolean retVal = false;
		
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO korisnik (korisnickoIme, lozinka, datumReg, uloga) "
					+ "VALUES (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());			
			pstmt.setDate(index++, korisnik.getDatumReg());
			pstmt.setString(index++, korisnik.getUloga().toString());
			System.out.println(pstmt);


			if (pstmt.executeUpdate() == 1) {
				retVal = true;
				korisnik.setId(getInsertedId(conn));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}

	//izmena korisnika
	public static boolean update(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();

		boolean retVal = false;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnik SET korisnickoIme = ?, lozinka = ?, datumReg = ?, uloga = ?"
					+ "WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setDate(index++, korisnik.getDatumReg());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setInt(index++, korisnik.getId());
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
	

	//brisanje korisnika po id-u
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


}
