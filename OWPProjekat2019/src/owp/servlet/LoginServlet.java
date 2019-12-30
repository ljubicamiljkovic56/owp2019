package owp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KorisnikDAO;
import owp.model.Korisnik;
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka = request.getParameter("lozinka");

		Korisnik korisnik = KorisnikDAO.get(korisnickoIme, lozinka);
		if (korisnik == null) {
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
			return;
		}

		request.getSession().setAttribute("logKorisnickoIme", korisnik.getKorisnickoIme());
		
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		return;
	}

}
