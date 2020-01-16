package owp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import owp.model.Film;

public class FilmDAO {
	
	//nadji film po id-u
	public static Film get(int id) {
		Connection conn = ConnectionManager.getConnection();
		Film film = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis FROM film WHERE id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				//int index = 1;
				//int id1 = rset.getInt(index++);
				String naziv = rset.getString(1);
				String reziser = rset.getString(2);
				String glumci = rset.getString(3);
				String zanrovi = rset.getString(4);
				int trajanje = rset.getInt(5);
				String distributer = rset.getString(6);
				String zemljaPorekla = rset.getString(7);
				int godinaProizvodnje = rset.getInt(8);
				String opis = rset.getString(9);
				
				film = new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
			}
		}		
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return film;
	}
	
	
//	public static Film getNaziv(String naziv) {
//		Connection conn = ConnectionManager.getConnection();
//
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
//			String query = "SELECT * FROM film WHERE naziv = ? ";
//			
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, id);
//			System.out.println(pstmt);
//
//			rset = pstmt.executeQuery();
//			
//			if (rset.next()) {
//				int index = 1;
//				int id1 = rset.getInt(index++);
//				String naziv1 = rset.getString(index++);
//				String reziser = rset.getString(index++);
//				String glumci = rset.getString(index++);
//				String zanrovi = rset.getString(index++);
//				int trajanje = rset.getInt(index++);
//				String distributer = rset.getString(index++);
//				String zemljaPorekla = rset.getString(index++);
//				int godinaProizvodnje = rset.getInt(index++);
//				String opis = rset.getString(index++);
//				
//				return new Film(id1, naziv1, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
//			}
//		}		
//		catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
//			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
//			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
//		}
//		
//		return null;
//	}
	
	public static List<Film> getAll(){
		List<Film> filmovi = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM film ";
			
			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int id1 = rset.getInt("id");
				String naziv1 = rset.getString("naziv");
				String reziser1 = rset.getString("reziser");
				String glumci1 = rset.getString("glumci");
				String zanrovi1 = rset.getString("zanrovi");
				int trajanje1 = rset.getInt("trajanje");
				String distributer1 = rset.getString("distributer");
				String zemljaPorekla1 = rset.getString("zemljaPorekla");
				int godinaProizvodnje1 = rset.getInt("godinaProizvodnje");
				String opis1 = rset.getString("opis");
				Film film = new Film(id1, naziv1, reziser1, glumci1, zanrovi1, trajanje1, distributer1, zemljaPorekla1, godinaProizvodnje1, opis1);
				film.setId(id1);
				film.setNaziv(naziv1);
				film.setReziser(reziser1);
				film.setGlumci(glumci1);
				film.setZanrovi(zanrovi1);
				film.setTrajanje(trajanje1);
				film.setDistributer(distributer1);
				film.setZemljaPorekla(zemljaPorekla1);
				film.setGodinaProizvodnje(godinaProizvodnje1);
				film.setOpis(opis1);
				filmovi.add(film);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return filmovi;
		
	}

	
//	public static List<Film> getAll(){
//				
//		
//		List<Film> filmovi = new ArrayList<Film>();
//		
//		Connection conn = ConnectionManager.getConnection();
//		
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
////			String query = "SELECT * FROM film WHERE "
////					+ "naziv = ? AND reziser = ? AND glumci = ? AND zanrovi = ? AND distributer = ? AND zemljaPorekla = ? AND opis = ?";
////			
////			if (trajanje > 0) {
////				query += " AND trajanje = ?";
////			}
////			if (godinaProizvodnje > 0) {
////				query += " AND godinaProizvodnje = ?";
////			}
//			String query = "SELECT * FROM film";
//
//
//			pstmt = conn.prepareStatement(query);
////			int index = 1;
////			pstmt.setString(index++, "%" + naziv + "%");
////			pstmt.setString(index++, reziser);
////			pstmt.setString(index++, glumci);
////			pstmt.setString(index++, zanrovi + "%");
////			pstmt.setString(index++, distributer + "%");
////			pstmt.setString(index++, zemljaPorekla + "%");
////			pstmt.setString(index++, opis + "%");
////			if (trajanje > 0) {
////				pstmt.setInt(index++, trajanje);
////			}
////			if (godinaProizvodnje > 0) {
////				pstmt.setInt(index++, godinaProizvodnje);
////			}
//			
//			
//			System.out.println(pstmt);
//
//			rset = pstmt.executeQuery();
//
//			System.out.println("presao query");
//			System.out.println(rset + " evo rseta");
//			
//			while (rset.next()) {
////				index = 1;
////				System.out.println("usao u while petlju");
////				System.out.println(index);
////				int filmId = rset.getInt(index++);
////				String filmNaziv = rset.getString(index++);
////				String filmReziser = rset.getString(index++);
////				String filmGlumci = rset.getString(index++);
////				String filmZanrovi = rset.getString(index++);
////				int filmTrajanje = rset.getInt(index++);
////				String filmDistributer = rset.getString(index++);
////				String filmZemljaPorekla = rset.getString(index++);
////				int filmGodinaProizvodnje = rset.getInt(index++);
////				String filmOpis = rset.getString(index++);
//
////				Film film = new Film(filmId, filmNaziv, filmReziser, filmGlumci, filmZanrovi, filmTrajanje, filmDistributer, filmZemljaPorekla, filmGodinaProizvodnje, filmOpis);
////				film.setId(rset.getInt(1));
////				film.setNaziv(rset.getString(2));
////				film.setReziser(rset.getString(3));
////				film.setGlumci(rset.getString(4));
////				film.setZanrovi(rset.getString(5));
////				film.setTrajanje(rset.getInt(6));
////				film.setDistributer(rset.getString(7));
////				film.setZemljaPorekla(rset.getString(8));
////				film.setGodinaProizvodnje(rset.getInt(9));
////				film.setOpis(rset.getString(10));
////				filmovi.add(film);
////				
////				System.out.println("kraj metode ");
////				System.out.println(filmovi);
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}finally {
//			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
//			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
//			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
//		}
//		return filmovi;
//		
//	}
	
	public static List<Film> getAllIndex(String naziv, String zanrovi, int trajanje, String distributer, 
			String zemljaPorekla, int godinaProizvodnje, String opis){
		
		List<Film> filmovi = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM film WHERE "
					+ "naziv = ? AND zanrovi = ? AND distributer = ? AND zemljaPorekla = ? ";
			
			if (trajanje > 0) {
				query += " AND trajanje = ?";
			}
			if (godinaProizvodnje > 0) {
				query += " AND godinaProizvodnje = ?";
			}
			
			// gore mora biti like
			// za trajanje i godinu proizvodnje mora da postoji od do, gornja i donja granica
			//% koristimo sa LIKE, ako ne koristimo LIKe vec = ?, onda nam ne trebaju

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, naziv);
			pstmt.setString(index++, zanrovi);
			pstmt.setString(index++, distributer);
			pstmt.setString(index++, zemljaPorekla);
			pstmt.setString(index++, opis);
			if (trajanje > 0) {
				pstmt.setInt(index++, trajanje);
			}
			if (godinaProizvodnje > 0) {
				pstmt.setInt(index++, godinaProizvodnje);
			}
			
			System.out.println(pstmt);
			System.out.println(pstmt + " evo statementa");

			rset = pstmt.executeQuery();
			
			System.out.println(pstmt.executeQuery() + "  jel ovo execute query");

			System.out.println("nakon execute query");
			System.out.println(query);
			
			if(rset.next()) {
				int id = rset.getInt(1);
				String filmNaziv = rset.getString(2);
				String reziser = rset.getString(3);
				String glumci = rset.getString(4);
				String filmZanrovi = rset.getString(5);
				int filmTrajanje = rset.getInt(6);
				String filmDistributer = rset.getString(7);
				String filmZemljaPorekla = rset.getString(8);
				int filmGodinaProizvodnje = rset.getInt(9);
				String filmOpis = rset.getString(10);
				
			//	Film film = new Film(id, filmNaziv, reziser, glumci, filmZanrovi, filmTrajanje, filmDistributer, filmZemljaPorekla, filmGodinaProizvodnje, filmOpis);
			//	filmovi.add(film);

				Film film = new Film(id,filmNaziv,reziser,glumci,filmZanrovi,filmTrajanje,filmDistributer,filmZemljaPorekla,filmGodinaProizvodnje,filmOpis);
				film.setId((rset.getInt(1)));
				film.setNaziv(rset.getString(2));
				film.setReziser((rset.getString(3)));
				film.setGlumci(rset.getString(4));
				film.setZanrovi((rset.getString(5)));
				film.setTrajanje((rset.getInt(6)));
				film.setDistributer((rset.getString(7)));
				film.setZemljaPorekla((rset.getString(8)));
				film.setGodinaProizvodnje(rset.getInt(9));
				film.setOpis(rset.getString(10));
				filmovi.add(film);	


			}
			//rset.close();
		//	pstmt.close();
			System.out.println("zasto nema ispisa?");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return filmovi;
		
	}
	

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

	//add
	public static boolean add(Film film) {
		Connection conn = ConnectionManager.getConnection();
		boolean retVal = false;
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO film (naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
		//	pstmt.setInt(index++, film.getId());
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			System.out.println(pstmt);

			//return pstmt.executeUpdate() == 1;
			
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
				film.setId(getInsertedId(conn));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}
	
	
	//update
	public static boolean update(Film film) {
		Connection conn = ConnectionManager.getConnection();

		boolean retVal = false;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE film SET naziv = ?, reziser = ?, glumci = ?, zanrovi = ?, trajanje = ?, distributer = ?, zemlja = ?, godina = ?, opis = ?"
					+ "WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, film.getId());
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			System.out.println(pstmt);

			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			//return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}
	//delete
	public static boolean delete(String id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM film WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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
