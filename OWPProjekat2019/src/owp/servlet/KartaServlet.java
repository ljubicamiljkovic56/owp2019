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

import owp.dao.KartaDAO;
import owp.dao.KorisnikDAO;
import owp.model.Karta;
import owp.model.Korisnik;
@SuppressWarnings("serial")
public class KartaServlet extends HttpServlet {
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
		
		String projekcija = request.getParameter("projekcija");
		projekcija = (projekcija != null? projekcija: "");
		int sedisteV = 0;
		try {
			String filterSedisteVString = request.getParameter("filterSediste");
			sedisteV = Integer.parseInt(filterSedisteVString);
			sedisteV = (sedisteV > 0? sedisteV: 0);
		}catch (Exception e) {}
		int sedisteN = 0;
		try {
			String filterSedisteNString = request.getParameter("filterSediste");
			sedisteN = Integer.parseInt(filterSedisteNString);
			sedisteN = (sedisteN > 0? sedisteN: 0);
		}catch (Exception e) {}
		String stringDatumProdaje = "2020-01-22";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		try {
			date =  dateFormat.parse(stringDatumProdaje);
			System.out.println(date);
			java.sql.Date sqlDate = java.sql.Date.valueOf(stringDatumProdaje);
			System.out.println(sqlDate);
		}catch (ParseException pex) {
			pex.printStackTrace();
		}
		String datumProdaje = request.getParameter("datumProdaje");
		String vremeProdaje = request.getParameter("vremeProdaje");
		vremeProdaje = (vremeProdaje != null? vremeProdaje: "");
		String korisnik = request.getParameter("korisnik");
		korisnik = (korisnik != null? korisnik: "");
		
		
		List<Karta> filterKarte = KartaDAO.getAll();
		//List<Karta> filterKarte = KartaDAO.getAllKarta(projekcija, sedisteV, sedisteN, datumProdaje, vremeProdaje, korisnik);
				
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterKarte", filterKarte);
		data.put("ulogovanKorisnikUloga", ulogovanKorisnik.getUloga());

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
//		if (ulogovanKorisnik.getUloga() != Uloga.admin) {
//			request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
//			return;
//		}
		
		//id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik
		try {
			String action = request.getParameter("action");
			switch (action) {
				case "add": {
					//int id = Integer.parseInt(request.getParameter("id"));
					//id = (id > 0? id: 0);
					String projekcija = request.getParameter("projekcija");
					projekcija = (!"".equals(projekcija)? projekcija: "<nepopunjena projekcija>");
					int sediste = Integer.parseInt(request.getParameter("sediste"));
					sediste = (sediste > 0? sediste: 0);
					
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
					
					String vremeProdaje = request.getParameter("vremeProdaje");
					vremeProdaje = (!"".equals(vremeProdaje)? vremeProdaje: "<nepopunjeno vreme>");
				
					String korisnik = request.getParameter("korisnik");
					korisnik = (!"".equals(korisnik)? korisnik: "<nepopunjen korisnik>");
					

					Karta karta = new Karta(projekcija, sediste, sqlDate, vremeProdaje, ulogovanKorisnik.getKorisnickoIme());
					KartaDAO.add(karta);
					break;
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("id"));
					Karta karta = KartaDAO.get(id);
					
					id = (id > 0? id: karta.getId());
					String projekcija = request.getParameter("projekcija");
					projekcija = (!"".equals(projekcija)? projekcija: karta.getProjekcija().toString());
					int sediste = Integer.parseInt(request.getParameter("sediste"));
					sediste = (sediste > 0? sediste: karta.getSediste());
					
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
					
					String vremeProdaje = request.getParameter("vremeProdaje");
					vremeProdaje = (!"".equals(vremeProdaje)? vremeProdaje: karta.getVremeProdaje());
					
					String korisnik = request.getParameter("korisnik");
					korisnik = (!"".equals(korisnik)? korisnik: ulogovanKorisnik.getKorisnickoIme());
					
					karta.setId(id);
					karta.setProjekcija(projekcija);
					karta.setSediste(sediste);
					karta.setDatumProdaje(sqlDate);
					karta.setVremeProdaje(vremeProdaje);
					karta.setKorisnik(korisnik);
					KartaDAO.update(karta);
					break;
				}
				case "delete": {
					int id = Integer.parseInt(request.getParameter("id"));
					KartaDAO.delete(id);
					break;
				}
			}
			
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

}
