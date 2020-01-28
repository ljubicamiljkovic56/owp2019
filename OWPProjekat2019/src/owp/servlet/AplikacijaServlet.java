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
		
		String naziv = request.getParameter("naziv");
		naziv = (naziv != null? naziv: "");
		String reziser = request.getParameter("reziser");
		reziser = (reziser != null? reziser: "");
		String glumci = request.getParameter("glumci");
		glumci = (glumci != null? glumci: "");
		String zanrovi = request.getParameter("zanrovi");
		zanrovi = (zanrovi != null? zanrovi: "");
	//	int trajanje = Integer.parseInt(request.getParameter("trajanje"));
	//	trajanje = (trajanje > 0? trajanje: 0);
		String distributer = request.getParameter("distributer");
		distributer = (distributer != null? distributer: "");
		String zemljaPorekla = request.getParameter("zemljaPorekla");
		zemljaPorekla = (zemljaPorekla != null? zemljaPorekla: "");
	//	int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
	//	godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 0);
		String opis = request.getParameter("opis");
		opis = (opis != null? opis: "");
	

		List<Film> filterFilmovi = FilmDAO.getAll();
		//List<Film> filterFilmovi = FilmDAO.getAllIndex(naziv, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterFilmovi", filterFilmovi);

		request.setAttribute("data", data);
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
