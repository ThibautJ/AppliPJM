package com.example.applicationfinale;


public class ActionneurAN extends Actionneur {
	protected int etatI;
	public static int[] listActionneurAN;
	
	public ActionneurAN(GestionReseau geR){
		super(geR);
	}
	
	protected int getEtat(){
		return etatI;
	}
	private void setEtat(int etatVrai){
		etatI = etatVrai;
	}
	private void commander(int consigne){
		etatI = etatI + consigne;
	}
	
}
