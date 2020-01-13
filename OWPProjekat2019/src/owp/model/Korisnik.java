package owp.model;

import java.sql.Date;

public class Korisnik {

	private int id;
	private String korisnickoIme;
	private String lozinka;
	private Date datumReg;
	private Uloga uloga;
	public enum Uloga {korisnik, admin}
	
	
	
	public Korisnik(int id, String korisnickoIme, String lozinka, Date datumReg, Uloga uloga) {
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumReg = datumReg;
		this.uloga = uloga;
	}
	
	
	public Korisnik(String korisnickoIme, String lozinka, Date datumReg, Uloga uloga) {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumReg = datumReg;
		this.uloga = uloga;
	}





	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public Date getDatumReg() {
		return datumReg;
	}
	public void setDatumReg(Date datumReg) {
		this.datumReg = datumReg;
	}
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	};
	
	
	
	
}
