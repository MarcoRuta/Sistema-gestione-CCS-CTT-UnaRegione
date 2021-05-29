package it.unisannio.ingegneriaDelSoftware.Util;


import java.util.HashMap;



public class CTTStaticIpMap {
	

	
	@SuppressWarnings("serial")
	private final static HashMap<String, String> indirizziCTT = new HashMap<String, String>(){
		{
			put ("CTT001", "");
			put ("CTT002", "");
			put ("CTT003", "");
			put ("CTT004", "");
			put ("CTT005", "");
			put ("CTT006", "");
			put ("CTT007", "");
		}
	};

	public static HashMap<String, String> getIndirizzictt() {
		return indirizziCTT;
	}
	
}
