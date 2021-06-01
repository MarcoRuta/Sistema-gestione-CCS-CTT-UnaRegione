package it.unisannio.ingegneriaDelSoftware.Util;


import java.util.HashMap;



public class CTTStaticIpMap {

	public final static HashMap<String, String> indirizziCTT = new HashMap<String, String>(){
		{
			put ("CTT001","http://192.168.193.194:8080");
			put ("CTT002","http://192.168.193.38:8080");
			put ("CTT003","http://192.168.193.160:8080");
			put ("CTT004","http://192.168.193.55:8080");
			//put ("CTT005","http://192.168.193.55:8080");
			//put ("CTT006","http://192.168.193.194:8080");
			//put ("CTT007","http://192.168.193.194:8080");
		}
	};
	
}
