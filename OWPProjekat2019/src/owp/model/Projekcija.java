package owp.model;

import java.sql.Date;

public class Projekcija {
	
	private int id;
	private String film;
	private String tipProjekcije;
	private String sala;
	private Date datumPrikazivanja;
	private String vremePrikazivanja;
	private double cenaKarte;
	private String admin;
	
	
	public Projekcija(int id, String film, String tipProjekcije, String sala, Date datumPrikazivanja,
			String vremePrikazivanja, double cenaKarte, String admin) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumPrikazivanja = datumPrikazivanja;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
	}
	
	public Projekcija(String film, String tipProjekcije, String sala, Date datumPrikazivanja, String vremePrikazivanja,
			double cenaKarte, String admin) {
		super();
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumPrikazivanja = datumPrikazivanja;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilm() {
		return film;
	}
	public void setFilm(String film) {
		this.film = film;
	}
	public String getTipProjekcije() {
		return tipProjekcije;
	}
	public void setTipProjekcije(String tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public Date getDatumPrikazivanja() {
		return datumPrikazivanja;
	}
	public void setDatumPrikazivanja(Date datumPrikazivanja) {
		this.datumPrikazivanja = datumPrikazivanja;
	}
	public String getVremePrikazivanja() {
		return vremePrikazivanja;
	}
	public void setVremePrikazivanja(String vremePrikazivanja) {
		this.vremePrikazivanja = vremePrikazivanja;
	}
	public double getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
	
	
}
