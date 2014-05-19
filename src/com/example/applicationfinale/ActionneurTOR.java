package com.example.applicationfinale;

import android.widget.Button;
import android.widget.RelativeLayout;


public class ActionneurTOR extends Actionneur {
	private boolean etatB;
	public static int[] listActionneurTOR;
	private Button button;
	private RelativeLayout layout;
	
	//Constructeur
	public ActionneurTOR(GestionReseau geR){
		super(geR);		
	}
	//Constructeur avec ajout de bouton
	public ActionneurTOR(GestionReseau geR,int number,String name){
		super(geR);
		button.setText(name);
		geR.getLayout().addView(button);
		geR.getAct().setContentView(layout);		
	}
	
	public boolean getEtatB(){
		return etatB;
	}
	
	public void setEtatB(boolean etatVrai){
		etatB = etatVrai;
	}
	
	public void allumer(){
		this.setEtatB(true);
		gR.clicOn();
	}
	
	public void eteindre(){
		this.setEtatB(false);
		gR.clicOff();
	}
}
