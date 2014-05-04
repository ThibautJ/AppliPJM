package com.example.applicationfinale;

public class CapteurTOR extends Capteur{
	protected String etatB;
	protected GestionReseau gr;
	
	
	protected String getEtat(){
		return etatB;
	}
	public void setEtat(String etatVrai){
		etatB = etatVrai;
		if (etatVrai.equals("1")){
			gr.clicOn();
		}
		else if(etatVrai.equals("0")){
			gr.clicOff();
		}
		
	}
}
