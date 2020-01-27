package owp.model;

import java.sql.Date;

public class Karta {
	
	private int id;
	private String projekcija;
	private int sediste;
	private Date datumProdaje;
	private String vremeProdaje;
	private String korisnik;
	
	
	public Karta(int id, String projekcija, int sediste, Date datumProdaje, String vremeProdaje, String korisnik) {
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumProdaje = datumProdaje;
		this.vremeProdaje = vremeProdaje;
		this.korisnik = korisnik;
	}
	
	
	
	public Karta(String projekcija, int sediste, Date datumProdaje, String vremeProdaje, String korisnik) {
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
	public String getProjekcija() {
		return projekcija;
	}
	public void setProjekcija(String projekcija) {
		this.projekcija = projekcija;
	}
	public int getSediste() {
		return sediste;
	}
	public void setSediste(int sediste) {
		this.sediste = sediste;
	}
	public Date getDatumProdaje() {
		return datumProdaje;
	}
	public void setDatumProdaje(Date datumProdaje) {
		this.datumProdaje = datumProdaje;
	}	
	
	public String getVremeProdaje() {
		return vremeProdaje;
	}

	public void setVremeProdaje(String vremeProdaje) {
		this.vremeProdaje = vremeProdaje;
	}



	public String getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}
	
	
	

}
