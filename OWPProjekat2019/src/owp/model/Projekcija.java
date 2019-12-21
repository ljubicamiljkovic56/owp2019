package owp.model;

import java.sql.Date;
import java.sql.Time;

public class Projekcija {
	
	private int id;
	private Film film;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private Date datumPrikazivanja;
	private Time vremePrikazivanja;
	private double cenaKarte;
	private Korisnik admin;
	
	
	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, Date datumPrikazivanja,
			Time vremePrikazivanja, double cenaKarte, Korisnik admin) {
		this.id = id;
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
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public TipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}
	public void setTipProjekcije(TipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Date getDatumPrikazivanja() {
		return datumPrikazivanja;
	}
	public void setDatumPrikazivanja(Date datumPrikazivanja) {
		this.datumPrikazivanja = datumPrikazivanja;
	}
	public Time getVremePrikazivanja() {
		return vremePrikazivanja;
	}
	public void setVremePrikazivanja(Time vremePrikazivanja) {
		this.vremePrikazivanja = vremePrikazivanja;
	}
	public double getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}


	public Korisnik getAdmin() {
		return admin;
	}


	public void setAdmin(Korisnik admin) {
		this.admin = admin;
	}



	
	
}
