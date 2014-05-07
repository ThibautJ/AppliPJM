package com.example.applicationfinale;

public class CapteurAN extends Capteur {
	protected int etatI;
	
	public CapteurAN(GestionReseau geR){
		super(geR);
	}
	
	
	protected int getEtat(){
		return etatI;
	}
	public void setEtat(int etatVrai){
		etatI = etatVrai;		
	}

}

