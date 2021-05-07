package it.unisannio.TipiAggiuntivi;

public class Cdf {
	private String codiceFiscale;
	
	public Cdf (String cdf) {
		assert(cdf.length()==16);
		this.codiceFiscale = cdf;
		}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}
/*
	public void setCodiceFiscale(String codiceFiscale) {
		assert(codiceFiscale.length()==16);
		this.codiceFiscale = codiceFiscale;
	}*/
}
