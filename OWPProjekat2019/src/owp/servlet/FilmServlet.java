package owp.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.FilmDAO;
import owp.dao.KorisnikDAO;
import owp.model.Film;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;
@SuppressWarnings("serial")
public class FilmServlet extends HttpServlet {

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
		
		
		Map<String, Object> data = new LinkedHashMap<>();
		//data.put("film", film);
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
		try {
			String action = request.getParameter("action");
			switch (action) {
				case "add": {
					
					//int id = Integer.parseInt(request.getParameter("id"));
					//id = (id > 0? id: 100);
					String naziv = request.getParameter("naziv");
					naziv = (!"".equals(naziv)? naziv: "<nepopunjen naziv>");
					String reziser = request.getParameter("reziser");
					reziser = (!"".equals(reziser)? reziser: "<nepopunjen reziser>");
					String glumci = request.getParameter("glumci");
					glumci = (!"".equals(glumci)? glumci: "<nepopunjeni glumci>");
					String zanrovi = request.getParameter("zanrovi");
					zanrovi = (!"".equals(zanrovi)? zanrovi: "<nepopunjeni zanrovi>");
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					trajanje = (trajanje > 0? trajanje: 300);
					String distributer = request.getParameter("distributer");
					distributer = (!"".equals(distributer)? distributer: "<nepopunjen distributer>");
					String zemljaPorekla = request.getParameter("zemljaPorekla");
					zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: "<nepopunjena zemlja porekla>");
					int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
					godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 2030);
					String opis = request.getParameter("opis");
					opis = (!"".equals(opis)? opis: "<nepopunjen opis>");
					
					Film film = new Film(naziv,reziser,glumci,zanrovi,trajanje,distributer,zemljaPorekla,godinaProizvodnje,opis);
					FilmDAO.add(film);
					break;
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("id"));
					Film film = FilmDAO.get(id);
					
					String naziv = request.getParameter("naziv");
					naziv = (!"".equals(naziv)? naziv: film.getNaziv());
					String reziser = request.getParameter("reziser");
					reziser = (!"".equals(reziser)? reziser: film.getReziser());
					String glumci = request.getParameter("glumci");
					glumci = (!"".equals(glumci)? glumci: film.getGlumci());
					String zanrovi = request.getParameter("zanrovi");
					zanrovi = (!"".equals(zanrovi)? zanrovi: film.getZanrovi());
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					trajanje = (trajanje > 0? trajanje: film.getTrajanje());
					String distributer = request.getParameter("distributer");
					distributer = (!"".equals(distributer)? distributer: film.getDistributer());
					String zemljaPorekla = request.getParameter("zemljaPorekla");
					zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: film.getZemljaPorekla());
					int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
					godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: film.getGodinaProizvodnje());
					String opis = request.getParameter("opis");
					opis = (!"".equals(opis)? opis: film.getOpis());

					film.setId(id);
					film.setNaziv(naziv);
					film.setReziser(reziser);
					film.setGlumci(glumci);
					film.setZanrovi(zanrovi);
					film.setTrajanje(trajanje);
					film.setDistributer(distributer);
					film.setZemljaPorekla(zemljaPorekla);
					film.setGodinaProizvodnje(godinaProizvodnje);
					film.setOpis(opis);
					FilmDAO.update(film);
					break;
				}
				case "delete": {
					String id = request.getParameter("id");
					FilmDAO.delete(id);
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