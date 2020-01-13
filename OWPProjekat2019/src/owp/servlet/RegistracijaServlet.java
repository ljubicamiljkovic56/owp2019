package owp.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KorisnikDAO;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;
@SuppressWarnings("serial")
public class RegistracijaServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String korisnickoIme = request.getParameter("korisnickoIme");
			if (KorisnikDAO.get(korisnickoIme) != null)
				throw new Exception("Korisnicko ime vec postoji!");
			if ("".equals(korisnickoIme))
				throw new Exception("Korisnicko ime je prazno!");

			String lozinka = request.getParameter("lozinka");
			if ("".equals(lozinka))
				throw new Exception("Lozinka je prazna!");
			
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
			
			
			
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, sqlDate , Uloga.korisnik);
			KorisnikDAO.add(korisnik);
			//KorisnikDAO.add(korisnickoIme, lozinka);

			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			String poruka = ex.getMessage();
			if (poruka == null) {
				poruka = "Nepredvidjena greska!";
				ex.printStackTrace();
			}

			Map<String, Object> data = new LinkedHashMap<>();
			data.put("poruka", poruka);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

}
