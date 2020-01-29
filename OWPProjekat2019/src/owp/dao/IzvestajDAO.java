package owp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import owp.model.Izvestaj;

public class IzvestajDAO {
	
	public static List<Izvestaj> getAll() {
		List<Izvestaj> izvestaji = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * from izvestaj ";
			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int id = rset.getInt("id");
				String nazivFilma = rset.getString("nazivFilma");
				int brojProjekcija = rset.getInt("brojProjekcija");
				int brojKarata = rset.getInt("brojKarata");
				double ukupnaCenaKarata = rset.getDouble("ukupnaCenaKarata");
				
				Izvestaj izvestaj = new Izvestaj(id, nazivFilma, brojProjekcija, brojKarata, ukupnaCenaKarata);
				izvestaj.setId(id);
				izvestaj.setNazivFilma(nazivFilma);
				izvestaj.setBrojProjekcija(brojProjekcija);
				izvestaj.setBrojKarata(brojKarata);
				izvestaj.setUkupnaCenaKarata(ukupnaCenaKarata);
				izvestaji.add(izvestaj);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}

		return izvestaji;
	}

}
