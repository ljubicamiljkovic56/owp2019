package owp.model;

import java.sql.Date;

public class Projekcija {
	
	private int id;
	//private Film film;
	//private TipProjekcije tipProjekcije;
	//private Sala sala;
	private String film;
	private String tipProjekcije;
	private String sala;
	private Date datumIVremePrikazivanja;
//	private Time vremePrikazivanja;
	private double cenaKarte;
	private String admin;
	
	
	
	public Projekcija(int id, String film, String tipProjekcije, String sala, Date datumIVremePrikazivanja,
			double cenaKarte, String admin) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumIVremePrikazivanja = datumIVremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
	}
	
	
	
	public Projekcija(String film, String tipProjekcije, String sala, Date datumIVremePrikazivanja, double cenaKarte,
			String admin) {
		super();
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumIVremePrikazivanja = datumIVremePrikazivanja;
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
	public Date getDatumIVremePrikazivanja() {
		return datumIVremePrikazivanja;
	}
	public void setDatumIVremePrikazivanja(Date datumIVremePrikazivanja) {
		this.datumIVremePrikazivanja = datumIVremePrikazivanja;
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
	
	
//	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, Date datumPrikazivanja,
//			Time vremePrikazivanja, double cenaKarte, Korisnik admin) {
//		this.id = id;
//		this.film = film;
//		this.tipProjekcije = tipProjekcije;
//		this.sala = sala;
//		this.datumPrikazivanja = datumPrikazivanja;
//		this.vremePrikazivanja = vremePrikazivanja;
//		this.cenaKarte = cenaKarte;
//		this.admin = admin;
//		
//	}
	



	
	
}
