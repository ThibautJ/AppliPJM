package com.example.applicationfinale;


public class ActionneurTOR extends Actionneur {
	private boolean etatB;
	
	public ActionneurTOR(GestionReseau geR){
		super(geR);
	}
	
	public boolean getEtatB(){
		return etatB;
	}
	
	public void setEtatB(boolean etatVrai){
		etatB = etatVrai;
	}
	
	public void allumer(){
		this.setEtatB(true);
			
	}
	
	public void eteindre(){
		this.setEtatB(false);
	}
}
