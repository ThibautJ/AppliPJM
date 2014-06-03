package com.example.applicationfinale;

import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class CapteurAN extends Capteur {
	protected int etatI;
	private Button button;
	private LinearLayout layout;
	public static CapteurAN[] listCapteurAN;
	
	public CapteurAN(GestionReseau geR, int nombre){
		super(geR, nombre);
	}
	//Constructeur avec ajout de bouton
		public CapteurAN(GestionReseau geR,int nombre,String name){
			super(geR,nombre);
			button = new Button(geR.getAct());
			layout = geR.getLayoutCapt();
			button.setText(name);
			layout.addView(button);
			geR.getAct().setLayoutCapt(layout);
			Log.v("moi","Fin du constructeur du CapteurAN");
		}
	
	
	protected int getEtat(){
		return etatI;
	}
	public void setEtat(int etatVrai){
		etatI = etatVrai;		
	}

}

