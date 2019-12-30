package owp.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import owp.dao.KorisnikDAO;
import owp.model.Korisnik;
import owp.model.Korisnik.Uloga;

@SuppressWarnings("serial")
public class KorisnikServlet extends HttpServlet {
	
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

		String korisnickoIme = request.getParameter("korisnickoIme");
		Korisnik korisnik = KorisnikDAO.get(korisnickoIme);
		
		Map<String, Object> data = new LinkedHashMap<>();
		
		data.put("korisnik", KorisnikDAO.getAll());

		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "ulogovanKorisnikUloga": {
					data.put("ulogovanKorisnikUloga", ulogovanKorisnik.getUloga());
					break;
				}
			}
		}

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
		
		try {
			String action = request.getParameter("action");
			switch(action) {
				case "add": {
			
					int id = ulogovanKorisnik.getId();
					String korisnickoIme = ulogovanKorisnik.getKorisnickoIme();
					korisnickoIme = (!"".equals(korisnickoIme)? korisnickoIme: "<nepopunjeno>");
					String lozinka = ulogovanKorisnik.getLozinka();
					lozinka = (!"".equals(lozinka)? lozinka: "<nepopunjeno>");
					String datumRegString = request.getParameter("datumReg");
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
					java.sql.Date datumReg = new java.sql.Date(sdf3.parse(datumRegString).getTime());
					String ulogaS = request.getParameter("uloga");
					 
					Korisnik korisnik = new Korisnik(id, korisnickoIme, lozinka, datumReg, Uloga.valueOf(ulogaS));
					KorisnikDAO.add(korisnickoIme, lozinka);
					break;
					
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("id"));
					Korisnik korisnik = KorisnikDAO.get(id);
					
					String korisnickoIme = request.getParameter("korisnickoIme");
					korisnickoIme = (!"".equals(korisnickoIme)? korisnickoIme: korisnik.getKorisnickoIme());
					String lozinka = request.getParameter("lozinka");
					lozinka = (!"".equals(lozinka)? lozinka: korisnik.getLozinka());
				
					
					String datumRegString = request.getParameter("datumReg");
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
					java.sql.Date datumReg = new java.sql.Date(sdf3.parse(datumRegString).getTime());
					Uloga uloga = Uloga.valueOf(request.getParameter("uloga"));
					
					korisnik.setKorisnickoIme(korisnickoIme);
					korisnik.setLozinka(lozinka);
					korisnik.setDatumReg(datumReg);
					korisnik.setUloga(uloga);
					KorisnikDAO.update(korisnik);
					break;
					
				}
				case "delete": {
					String id = request.getParameter("id");
					KorisnikDAO.delete(id);
					break;
				}
			}
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}catch(Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
		
	}

}
