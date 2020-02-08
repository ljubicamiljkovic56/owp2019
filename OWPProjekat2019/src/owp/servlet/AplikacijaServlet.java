package owp.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.FilmDAO;
import owp.dao.KorisnikDAO;
import owp.model.Film;
import owp.model.Korisnik;
@SuppressWarnings("serial")
public class AplikacijaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logKorisnickoIme = (String) request.getSession().getAttribute("logKorisnickoIme");
		if (logKorisnickoIme == null) {
			System.out.println("Nesto....");
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		Korisnik ulogovanKorisnik = KorisnikDAO.get(logKorisnickoIme);
		if (ulogovanKorisnik == null) {
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
	
		String naziv = request.getParameter("filterNaziv");
		naziv = (naziv != null? naziv: "");
		String reziser = request.getParameter("filterReziser");
		reziser = (reziser != null? reziser: "");
		String glumci = request.getParameter("filterGlumci");
		glumci = (glumci != null? glumci: "");
		String zanrovi = request.getParameter("filterZanrovi");
		zanrovi = (zanrovi != null? zanrovi: "");
		int trajanjeV = 0;
		try {
			String filterTrajanjeVString = request.getParameter("filterTrajanjeV");
			trajanjeV = Integer.parseInt(filterTrajanjeVString);
			trajanjeV = (trajanjeV > 0? trajanjeV: 0);
		}catch (Exception ex){}
		int trajanjeN = 0;
		try {
			String filterTrajanjeNString = request.getParameter("filterTrajanjeN");
			trajanjeN = Integer.parseInt(filterTrajanjeNString);
			trajanjeN = (trajanjeN > 0? trajanjeN: 0);
		}catch (Exception ex){}
		String distributer = request.getParameter("filterDistributer");
		distributer = (distributer != null? distributer: "");
		String zemljaPorekla = request.getParameter("filterZemljaPorekla");
		zemljaPorekla = (zemljaPorekla != null? zemljaPorekla: "");
		int godinaProizvodnjeV = 0;
		try {
			String filterGodinaProizvodnjeVString = request.getParameter("filterGodinaProizvodnjeV");
			godinaProizvodnjeV = Integer.parseInt(filterGodinaProizvodnjeVString);
			godinaProizvodnjeV = (godinaProizvodnjeV > 0? godinaProizvodnjeV: 0);
		}catch (Exception ex){ }
		int godinaProizvodnjeN = 0;
		try {
			String filterGodinaProizvodnjeNString = request.getParameter("filterGodinaProizvodnjeN");
			godinaProizvodnjeN = Integer.parseInt(filterGodinaProizvodnjeNString);
			godinaProizvodnjeN = (godinaProizvodnjeN > 0? godinaProizvodnjeN: 0);
		}catch (Exception ex){ }
	//	godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 0);
		String opis = request.getParameter("filterOpis");
		opis = (opis != null? opis: "");
		
		
		int brojSedista = 0;
		try {
			String brojSedistaFilter = request.getParameter("brojSedistaFilter");
			brojSedista = Integer.parseInt(brojSedistaFilter);
			brojSedista = (brojSedista > 0? brojSedista: 0);
		}catch (Exception ex){}

		//List<Film> filterFilmovi = FilmDAO.getAll();
		List<Film> filterFilmovi = FilmDAO.getAllIndex(naziv,reziser, glumci, zanrovi, trajanjeV, trajanjeN, distributer, zemljaPorekla, godinaProizvodnjeV, godinaProizvodnjeN, opis);

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterFilmovi", filterFilmovi);

		request.setAttribute("data", data);
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
