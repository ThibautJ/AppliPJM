package com.example.applicationfinale;

import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;


public class ActionneurAN extends Actionneur {
	protected int etatI;
	private Button button;
	private LinearLayout layout;
	public static int[] listActionneurAN;
	
	public ActionneurAN(GestionReseau geR, int nombre){
		super(geR, nombre);
	}
	
	//Constructeur avec ajout de bouton
		public ActionneurAN(GestionReseau geR,int nombre,String name){
			super(geR,nombre);
			button = new Button(geR.getAct());
			layout = geR.getLayoutAct();
			button.setText(name);
			layout.addView(button);
			geR.getAct().setLayoutAct(layout);
			Log.v("moi","Fin du constructeur de l'ActionneurAN");
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
