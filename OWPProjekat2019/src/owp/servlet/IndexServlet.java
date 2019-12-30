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
import owp.model.Film;
@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String naziv = request.getParameter("filterNaziv");
		naziv = (naziv != null? naziv: "");
		String reziser = request.getParameter("filterReziser");
		String glumci = request.getParameter("filterGlumci");
		String zanrovi = request.getParameter("filterZanrovi");
		zanrovi = (zanrovi != null? zanrovi: "");
		int trajanje = 0;
		try {
			String filterTrajanjeString = request.getParameter("filterTrajanje");
			trajanje = Integer.parseInt(filterTrajanjeString);
			trajanje = (trajanje > 0? trajanje: 0);
		}catch (Exception ex){}
		String distributer = request.getParameter("filterDistributer");
		distributer = (distributer != null? distributer: "");
		String zemljaPorekla = request.getParameter("filterZemljaPorekla");
		zemljaPorekla = (zemljaPorekla != null? zemljaPorekla: "");
		int godinaProizvodnje = 0;
		try {
			String filterGodinaProizvodnjeString = request.getParameter("filterGodinaProizvodnje");
			godinaProizvodnje = Integer.parseInt(filterGodinaProizvodnjeString);
			godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 0);
		}catch (Exception ex){}
		godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 0);
		String opis = request.getParameter("filterOpis");
		opis = (opis != null? opis: "");
	

		List<Film> filterFilmovi = FilmDAO.getAll();

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterFilmovi", filterFilmovi);
		System.out.println(filterFilmovi);

		request.setAttribute("data", data);
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
