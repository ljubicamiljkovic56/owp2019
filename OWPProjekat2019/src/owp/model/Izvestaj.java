package owp.model;

public class Izvestaj {
	
	private int id;
	private String nazivFilma;
	private int brojProjekcija;
	private int brojKarata;
	private double ukupnaCenaKarata;
	
	
	public Izvestaj(int id, String nazivFilma, int brojProjekcija, int brojKarata, double ukupnaCenaKarata) {
		super();
		this.id = id;
		this.nazivFilma = nazivFilma;
		this.brojProjekcija = brojProjekcija;
		this.brojKarata = brojKarata;
		this.ukupnaCenaKarata = ukupnaCenaKarata;
	}
	

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getNazivFilma() {
		return nazivFilma;
	}


	public void setNazivFilma(String nazivFilma) {
		this.nazivFilma = nazivFilma;
	}


	public int getBrojProjekcija() {
		return brojProjekcija;
	}


	public void setBrojProjekcija(int brojProjekcija) {
		this.brojProjekcija = brojProjekcija;
	}


	public int getBrojKarata() {
		return brojKarata;
	}


	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}


	public double getUkupnaCenaKarata() {
		return ukupnaCenaKarata;
	}


	public void setUkupnaCenaKarata(double ukupnaCenaKarata) {
		this.ukupnaCenaKarata = ukupnaCenaKarata;
	}
	
	

}
