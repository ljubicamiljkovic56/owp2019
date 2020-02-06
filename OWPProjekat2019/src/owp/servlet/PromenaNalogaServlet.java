package owp.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import owp.dao.KorisnikDAO;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;

@SuppressWarnings("serial")
public class PromenaNalogaServlet extends HttpServlet {

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
			switch (action) {

			case "update": {
				//int id = Integer.parseInt(request.getParameter("id"));
				//Korisnik korisnik = KorisnikDAO.get(id);
				Korisnik korisnik = KorisnikDAO.getKorisnickoIme(ulogovanKorisnik.getKorisnickoIme());
				
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
				
				String ulogaS = request.getParameter("uloga");
				ulogaS = (!"".equals(ulogaS)? ulogaS: korisnik.getUloga().toString());
				
				//korisnik.setId(id);
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik.setLozinka(lozinka);
				korisnik.setDatumReg(sqlDate);
				korisnik.setUloga(Uloga.valueOf(ulogaS));
				KorisnikDAO.update(korisnik);
				break;
				
			}
		}
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	
	}

}
