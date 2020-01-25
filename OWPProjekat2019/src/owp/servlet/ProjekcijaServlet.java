package owp.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KorisnikDAO;
import owp.dao.ProjekcijaDAO;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;
import owp.model.Projekcija;
@SuppressWarnings("serial")
public class ProjekcijaServlet extends HttpServlet {
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

		List<Projekcija> filterProjekcije = ProjekcijaDAO.getAll();
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterProjekcije", filterProjekcije);
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
		
		//id, film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin
		try {
			String action = request.getParameter("action");
			switch (action) {
				case "add": {
					String film = request.getParameter("film");
					film = (!"".equals(film)? film: "<nepopunjen film>");
					String tipProjekcije = request.getParameter("tipProjekcije");
					tipProjekcije = (!"".equals(tipProjekcije)? tipProjekcije: "<nepopunjen tip projekcije>");
					String sala = request.getParameter("sala");
					sala = (!"".equals(sala)? sala: "<nepopunjena sala>");
					
					
					String datumPrikazivanjaString = request.getParameter("datumPrikazivanja");
					SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
					java.sql.Date datumPrikazivanja = new java.sql.Date(sdf3.parse(datumPrikazivanjaString).getTime());
					
					String vremePrikazivanja = request.getParameter("vremePrikazivanja");
					vremePrikazivanja = (!"".equals(vremePrikazivanja)? vremePrikazivanja: "<nepopunjeno vreme prikazivanja>");
					
					double cenaKarte = Double.parseDouble(request.getParameter("cenaKarte"));
					cenaKarte = (cenaKarte > 0? cenaKarte: 99999999.00);
					String admin = request.getParameter("admin");
					admin = (!"".equals(admin)? admin: "<nepopunjen admin>");

					Projekcija projekcija = new Projekcija(film,tipProjekcije,sala, datumPrikazivanja, vremePrikazivanja,cenaKarte,admin);
					ProjekcijaDAO.add(projekcija);
					break;
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("id"));
					Projekcija projekcija = ProjekcijaDAO.get(id);
					
					id = (id > 0? id: projekcija.getId());
					String film = request.getParameter("film");
					film = (!"".equals(film)? film: projekcija.getFilm());
					String tipProjekcije = request.getParameter("tipProjekcije");
					tipProjekcije = (!"".equals(tipProjekcije)? tipProjekcije: projekcija.getTipProjekcije());
					String sala = request.getParameter("sala");
					sala = (!"".equals(sala)? sala: projekcija.getSala());
					
					String datumPrikazivanjaString = request.getParameter("datumPrikazivanja");
					SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
					java.sql.Date datumPrikazivanja = new java.sql.Date(sdf3.parse(datumPrikazivanjaString).getTime());
					
					
					String vremePrikazivanja = request.getParameter("vremePrikazivanja");
					vremePrikazivanja = (!"".equals(vremePrikazivanja)? vremePrikazivanja: projekcija.getVremePrikazivanja());
					
					double cenaKarte = Double.parseDouble(request.getParameter("cenaKarte"));
					cenaKarte = (cenaKarte > 0? cenaKarte: projekcija.getCenaKarte());
					String admin = request.getParameter("admin");
					admin = (!"".equals(admin)? admin: projekcija.getAdmin());
					
					projekcija.setId(id);
					projekcija.setFilm(film);
					projekcija.setTipProjekcije(tipProjekcije);
					projekcija.setSala(sala);;
					projekcija.setDatumPrikazivanja(datumPrikazivanja);
					projekcija.setVremePrikazivanja(vremePrikazivanja);
					projekcija.setCenaKarte(cenaKarte);
					projekcija.setAdmin(admin);
					ProjekcijaDAO.update(projekcija);
					break;
				}
				case "delete": {
					int id = Integer.parseInt(request.getParameter("id"));
					ProjekcijaDAO.delete(id);
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
