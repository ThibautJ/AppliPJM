package com.example.applicationfinale;

import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class ActionneurTOR extends Actionneur {
	private boolean etatB;
	public static int[] listActionneurTOR;
	private Button button;
	private LinearLayout layout;
	
	//Constructeur
	public ActionneurTOR(GestionReseau geR){
		super(geR);		
	}
	//Constructeur avec ajout de bouton
	public ActionneurTOR(GestionReseau geR,int number,String name){
		super(geR);
		button = new Button(geR.getAct());
		layout = geR.getLayout();
		button.setText(name);
		layout.addView(button);
		geR.getAct().setLayout(layout);
		Log.v("moi","Fin du constructeur de l'ActionneurTOR");
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
