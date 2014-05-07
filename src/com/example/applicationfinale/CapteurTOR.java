package com.example.applicationfinale;

public class CapteurTOR extends Capteur{
	protected String etatB;
	
	public CapteurTOR(GestionReseau geR){
		super(geR);
	}
	
	
	protected String getEtat(){
		return etatB;
	}
	public void setEtat(String etatVrai){
		etatB = etatVrai;
		if (etatVrai.equals("1")){
			gR.clicOn();
		}
		else if(etatVrai.equals("0")){
			gR.clicOff();
		}
		
	}
}
