package it.unisannio.ingegneriaDelSoftware.Util;

import java.text.SimpleDateFormat;

public class Constants {
	
	public static final String DB_NAME = "CCS";
	
	public static final String COLLECTION_CTT = "CTT";
	public static final String ELEMENT_NUMERO = "numero";
	public static final String ELEMENT_DENOMINAZIONE = "denominazione";
	public static final String ELEMENT_PROVINCIA = "provincia";
	public static final String ELEMENT_CITTA = "citta";
	public static final String ELEMENT_INDIRIZZO = "indirizzo";
	public static final String ELEMENT_TELEFONO = "telefono";
	public static final String ELEMENT_EMAIL = "e_mail";
	public static final String ELEMENT_LATITUDINE = "latitudine";
	public static final String ELEMENT_LONGITUDINE = "longitudine";
	
	public static final String COLLECTION_DIPENDENTI = "DIPENDENTI";
	public static final String ELEMENT_CDF = "cdf";
	public static final String ELEMENT_NOME = "nome";
	public static final String ELEMENT_COGNOME = "cognome";
	public static final String ELEMENT_DATADINASCITA = "dataDiNascita";
	public static final String ELEMENT_RUOLO = "ruolo";
	public static final String ELEMENT_USERNAME = "username";
	public static final String ELEMENT_PASSWORD = "password";

	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public static final String DATEFORMAT = "yyyy-MM-dd";
}