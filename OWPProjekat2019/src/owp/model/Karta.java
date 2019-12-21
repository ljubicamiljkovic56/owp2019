package owp.model;

import java.sql.Date;
import java.sql.Time;

public class Karta {
	
	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private Date datumProdaje;
	private Time vremeProdaje;
	private Korisnik korisnik;
	
	
	
	public Karta(int id, Projekcija projekcija, Sediste sediste, Date datumProdaje, Time vremeProdaje,
			Korisnik korisnik) {
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumProdaje = datumProdaje;
		this.vremeProdaje = vremeProdaje;
		this.korisnik = korisnik;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Projekcija getProjekcija() {
		return projekcija;
	}
	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}
	public Sediste getSediste() {
		return sediste;
	}
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
	public Date getDatumProdaje() {
		return datumProdaje;
	}
	public void setDatumProdaje(Date datumProdaje) {
		this.datumProdaje = datumProdaje;
	}
	public Time getVremeProdaje() {
		return vremeProdaje;
	}
	public void setVremeProdaje(Time vremeProdaje) {
		this.vremeProdaje = vremeProdaje;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
	

}
