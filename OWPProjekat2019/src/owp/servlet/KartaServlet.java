package owp.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KartaDAO;
import owp.dao.KorisnikDAO;
import owp.dao.ProjekcijaDAO;
import owp.model.Karta;
import owp.model.Korisnik;
import owp.model.Projekcija;
import owp.model.Korisnik.Uloga;

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
		int id = Integer.parseInt(request.getParameter("id"));
		Karta karta = KartaDAO.get(id);
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("karta", karta);
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
		if (ulogovanKorisnik.getUloga() != Uloga.admin) {
			request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
			return;
		}
		
		//id, projekcija, sediste, datumProdaje, vremeProdaje, korisnik
		try {
			String action = request.getParameter("action");
			switch (action) {
				case "add": {
					int id = Integer.parseInt(request.getParameter("id"));
					id = (id > 0? id: 0);
					String projekcija = request.getParameter("projekcija");
					projekcija = (!"".equals(projekcija)? projekcija: "<nepopunjena projekcija>");
					int sediste = Integer.parseInt(request.getParameter("sediste"));
					sediste = (sediste > 0? sediste: 0);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
					String najranijiDatum = "01-01-2019";
					java.util.Date temp = sdf.parse(najranijiDatum);
					java.sql.Date sqlNajranijiDatum = new java.sql.Date(temp.getTime());
					
					String najkasnijiDatum = "01-01-2021";
					temp = sdf.parse(najkasnijiDatum);
					java.sql.Date sqlNajkasnijiDatum = new java.sql.Date(temp.getTime());
					
					String datumProdajeString = request.getParameter("datumProdaje");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
					java.sql.Date datumProdaje = new java.sql.Date(sdf2.parse(datumProdajeString).getTime());
					
					if (datumProdaje.before(sqlNajkasnijiDatum) && datumProdaje.after(sqlNajranijiDatum)) {
						//prikazi taj datum
					}
					
					//ne znam kako vreme da parsiram
//					String vreme = "00:00";
//					Time vremeProdaje = new Time(vreme)
				
					String korisnik = request.getParameter("korisnik");
					korisnik = (!"".equals(korisnik)? korisnik: "<nepopunjen korisnik>");

					Karta karta = new Karta(id,projekcija, sediste, datumProdaje, vremeProdaje,korisnik);
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
					sediste = (sediste > 0? sediste: karta.getSediste().getRedniBroj());
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
					String najranijiDatum = "01-01-2019";
					java.util.Date temp = sdf.parse(najranijiDatum);
					java.sql.Date sqlNajranijiDatum = new java.sql.Date(temp.getTime());
					
					String najkasnijiDatum = "01-01-2021";
					temp = sdf.parse(najkasnijiDatum);
					java.sql.Date sqlNajkasnijiDatum = new java.sql.Date(temp.getTime());
					
					String datumProdajeString = request.getParameter("datumProdaje");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
					java.sql.Date datumProdaje = new java.sql.Date(sdf2.parse(datumProdajeString).getTime());
					
					if (datumProdaje.before(sqlNajkasnijiDatum) && datumProdaje.after(sqlNajranijiDatum)) {
						//prikazi taj datum
					}
					
					//ne znam kako vreme da parsiram
//					String vreme = "00:00";
//					Time vremePrikazivanja = new Time(vreme)
					
					String korisnik = request.getParameter("korisnik");
					korisnik = (!"".equals(korisnik)? korisnik: karta.getKorisnik().getKorisnickoIme());
					
					karta.setId(id);
					karta.setProjekcija(projekcija);
					karta.setSediste(sediste);
					karta.setDatumProdaje(datumProdaje);
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
