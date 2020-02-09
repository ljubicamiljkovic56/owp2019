package owp.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KorisnikDAO;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;

@SuppressWarnings("serial")
public class KorisnikServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logKorisnickoIme = (String) request.getSession().getAttribute("logKorisnickoIme");
		if (logKorisnickoIme == null) {
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		Korisnik ulogovanKorisnik = KorisnikDAO.get(logKorisnickoIme);
		if (ulogovanKorisnik == null) {
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		
		String korisnickoIme = request.getParameter("filterKorisnickoIme");
		korisnickoIme = (korisnickoIme != null? korisnickoIme: "");
		//String lozinka = request.getParameter("filterLozinka");
		//lozinka = (lozinka != null? lozinka: "");
//		String stringDatumReg = "2020-01-22";
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date;
//		try {
//			date =  dateFormat.parse(stringDatumReg);
//			System.out.println(date);
//			java.sql.Date sqlDate = java.sql.Date.valueOf(stringDatumReg);
//			System.out.println(sqlDate);
//		}catch (ParseException pex) {
//			pex.printStackTrace();
//		}
//		String datumReg = request.getParameter("filterDatumReg");
		String ulogaS = request.getParameter("filterUloga");
		ulogaS = (ulogaS != null? ulogaS: "");
		
		//List<Korisnik> filterKorisnici = KorisnikDAO.getAll();
		
		List<Korisnik> filterKorisnici = KorisnikDAO.getAllKorisnik(korisnickoIme, ulogaS);

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterKorisnici", filterKorisnici);
		System.out.println(filterKorisnici);

		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "ulogovanKorisnikUloga": {
					data.put("ulogovanKorisnikUloga", ulogovanKorisnik.getUloga());
					break;
				}
			}
		}

		request.setAttribute("data", data);
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logKorisnickoIme = (String) request.getSession().getAttribute("logKorisnickoIme");
		if (logKorisnickoIme == null) {
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		Korisnik ulogovanKorisnik = KorisnikDAO.get(logKorisnickoIme);
		if (ulogovanKorisnik == null) {
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		
		try {
			String action = request.getParameter("action");
			switch(action) {
				case "add": {
			
					//int id = ulogovanKorisnik.getId();
					String korisnickoIme = request.getParameter("korisnickoIme");
					korisnickoIme = (!"".equals(korisnickoIme)? korisnickoIme: "<nepopunjeno korisnickoIme>");
					String lozinka = request.getParameter("lozinka");
					lozinka = (!"".equals(lozinka)? lozinka: "<nepopunjena lozinka>");
					
					LocalDateTime currentDateTime = LocalDateTime.now();
					final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					//Format LocalDateTime
					String formattedDateTime = currentDateTime.format(formatter);
					//Verify
					System.out.println("Formatted LocalDateTime : " + formattedDateTime);    
					String tajDatum = formattedDateTime;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
					java.sql.Date sqlDate = new java.sql.Date(sdf.parse(tajDatum).getTime());
					System.out.println("String converted to java.sql.Date :" + sqlDate);
					
					String ulogaS = request.getParameter("uloga");
					ulogaS = (!"".equals(ulogaS)? ulogaS: "<nepopunjena uloga>");
					 
					Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, sqlDate, Uloga.valueOf(ulogaS));
					KorisnikDAO.add(korisnik);
					break;
					
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("id"));
					Korisnik korisnik = KorisnikDAO.get(id);
					
					String korisnickoIme = request.getParameter("korisnickoIme");
					korisnickoIme = (!"".equals(korisnickoIme)? korisnickoIme: korisnik.getKorisnickoIme());
					String lozinka = request.getParameter("lozinka");
					lozinka = (!"".equals(lozinka)? lozinka: korisnik.getLozinka());
					
					LocalDateTime currentDateTime = LocalDateTime.now();
					final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					//Format LocalDateTime
					String formattedDateTime = currentDateTime.format(formatter);
					//Verify
					System.out.println("Formatted LocalDateTime : " + formattedDateTime);    
					String tajDatum = formattedDateTime;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
					java.sql.Date sqlDate = new java.sql.Date(sdf.parse(tajDatum).getTime());
					System.out.println("String converted to java.sql.Date :" + sqlDate);
					
//					String datumRegString = request.getParameter("datumReg");
//					SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
//					java.sql.Date datumReg = new java.sql.Date(sdf3.parse(datumRegString).getTime());
					
					String ulogaS = request.getParameter("uloga");
					ulogaS = (!"".equals(ulogaS)? ulogaS: korisnik.getUloga().toString());
					
					korisnik.setId(id);
					korisnik.setKorisnickoIme(korisnickoIme);
					korisnik.setLozinka(lozinka);
					korisnik.setDatumReg(sqlDate);
					korisnik.setUloga(Uloga.valueOf(ulogaS));
					KorisnikDAO.update(korisnik);
					break;
					
				}
				case "delete": {
					String id = request.getParameter("id");
					KorisnikDAO.delete(id);
					break;
				}
			}
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}catch(Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
		
	}

}
