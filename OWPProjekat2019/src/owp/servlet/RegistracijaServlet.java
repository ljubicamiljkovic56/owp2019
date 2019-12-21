package owp.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KorisnikDAO;
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
			
			KorisnikDAO.add(korisnickoIme, lozinka);

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
