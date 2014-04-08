package com.example.applicationfinale;

public class CapteurTOR extends Capteur{
	protected String etatB;
	
	protected String getEtat(){
		return etatB;
	}
	public void setEtat(String etatVrai){
		etatB = etatVrai;
	}
}
