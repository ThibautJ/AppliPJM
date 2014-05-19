package com.example.applicationfinale;

public class CapteurTOR extends Capteur{
	protected String etatB;
	public static int[] listCapteurTOR;

	public CapteurTOR(GestionReseau geR){
		super(geR);
	}

	protected String getEtat(){
		return etatB;
	}
}
