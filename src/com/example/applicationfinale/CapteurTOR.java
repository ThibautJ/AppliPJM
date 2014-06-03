package com.example.applicationfinale;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CapteurTOR extends Capteur{
	protected boolean etatB;
	private Button button;
	private Activity activity;
	private LinearLayout layout;
	private ImageView check;

	public CapteurTOR(GestionReseau geR, int nombre){
		super(geR,nombre);
	}
	
	//Constructeur avec ajout de bouton
		public CapteurTOR(GestionReseau geR,int number,String name){
			super(geR,number);
			activity = geR.getAct();
			button = new Button(activity);
			layout = geR.getLayoutCapt();
			LinearLayout layoutI = new LinearLayout(activity);
			button.setText(name);
			check = new ImageView(activity);
			if(etatB){
				check.setImageDrawable(activity.getResources().getDrawable(R.drawable.valide));
			}
			else{
				check.setImageDrawable(activity.getResources().getDrawable(R.drawable.refuse));
			}
			button.setWidth(600);
			check.setPadding(50, 0, 50, 0);
			layoutI.addView(button);
			layoutI.addView(check);
			layout.addView(layoutI);
			geR.getAct().setLayoutCapt(layout);
			Log.v("moi","Fin du constructeur du capteurTOR");
		}

	protected boolean getEtat(){
		return etatB;
	}

	public boolean getEtatB() {
		return etatB;
	}

	public void setEtatB(boolean etatB) {
		this.etatB = etatB;
		if (etatB){
			check.setImageDrawable(activity.getResources().getDrawable(R.drawable.valide));
		}
		else{
			check.setImageDrawable(activity.getResources().getDrawable(R.drawable.refuse));
		}
	}
}
