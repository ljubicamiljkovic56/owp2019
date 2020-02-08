package owp.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.ProjekcijaDAO;
import owp.model.Projekcija;
@SuppressWarnings("serial")
public class ProjekcijeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String film = request.getParameter("filterFilm");
		film = (film != null? film: "");
		String tipProjekcije = request.getParameter("filterTipProjekcije");
		tipProjekcije = (tipProjekcije != null? tipProjekcije: "");
		String sala = request.getParameter("filterSala");
		sala = (sala != null? sala: "");
		
		String stringDatumPrikazivanja = "2020-01-22";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		try {
			date =  dateFormat.parse(stringDatumPrikazivanja);
			System.out.println(date);
			java.sql.Date sqlDate = java.sql.Date.valueOf(stringDatumPrikazivanja);
			System.out.println(sqlDate);
		}catch (ParseException pex) {
			pex.printStackTrace();
		}
		
		String datumPrikazivanja = request.getParameter("filterDatumPrikazivanja");
	//	String datumPrikazivanjaN = request.getParameter("filterDatumPrikazivanjaN");
		String vremePrikazivanja = request.getParameter("filterVremePrikazivanja");
		vremePrikazivanja = (vremePrikazivanja != null? vremePrikazivanja: "");
		//String vremePrikazivanjaN = request.getParameter("filterVremePrikazivanjaN");
		//vremePrikazivanjaN = (vremePrikazivanjaN != null? vremePrikazivanjaN: "");
		double cenaKarteV = 0.0;
		try {
			String filterCenaKarteVString = request.getParameter("filterCenaKarteV");
			cenaKarteV = Double.parseDouble(filterCenaKarteVString);
			cenaKarteV = (cenaKarteV >= 0.0? cenaKarteV: 0.0);
		}catch (Exception e) {}
		double cenaKarteN = Double.MAX_VALUE;
		try {
			String filterCenaKarteNString = request.getParameter("filterCenaKarteN");
			cenaKarteN = Double.parseDouble(filterCenaKarteNString);
			cenaKarteN = (cenaKarteN >= 0.0? cenaKarteN: 0.0);
		}catch (Exception e) {}
		String admin = request.getParameter("filterAdmin");
		admin = (admin != null? admin: "");

		//List<Projekcija> filterProjekcije = ProjekcijaDAO.getAll();
		
		List<Projekcija> filterProjekcije = ProjekcijaDAO.getAllProjekcija(film, tipProjekcije, sala, datumPrikazivanja, vremePrikazivanja, cenaKarteV, cenaKarteN, admin);

		Map<String, Object> data = new LinkedHashMap<>();
		data.put("filterProjekcije", filterProjekcije);
		System.out.println(filterProjekcije);

		request.setAttribute("data", data);
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
