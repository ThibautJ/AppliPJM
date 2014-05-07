package com.example.applicationfinale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DomotiqueWindow extends Activity {

	private CapteurTOR test;
	private GestionReseau gR;
	private TextView tv;
	private Runnable edit;
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_domotique_window);

		tv = (TextView)findViewById(R.id.communication);
		
		Log.v("moi","debut domotiquewindow");
		gR = new GestionReseau(tv);
		new Thread(gR).start();
		
		
		test=new CapteurTOR(gR);
		test.setEtat("1");
		
		
		
		/*Définition du runnable d'édition du TextView
		edit1 = new Runnable(){
			public void run(){
				tab = gR.getLec().getTab();
				str0 = tab[0];
				Log.v("moi", "J'ai lu le lecteur dans le run");
				tv.setText(str0);
				Log.v("moi", "J'ai mis a jour le TextView");
			}
		};
		edit = new Runnable(){
			public void run(){
				while(true){
					handler.post(edit1);
				}
			}
		};
		
		//Lancement du Thread de l'édition de texte
		new Thread(edit).start();*/
		
		
		final Button buttonfonction = (Button) findViewById(R.id.lampe);
		buttonfonction.setOnClickListener(new OnClickListener()  {
			
			public void onClick(View v) {
			
			
			String etat;
			etat=test.getEtat();
			System.out.println(etat);
		
			if (etat.equals("1"))
			{test.setEtat("0");
			ImageView Lampe = (ImageView) findViewById(R.id.resultLampe);
			Lampe.setImageDrawable(getResources().getDrawable(R.drawable.lampeallumee));}
			else if (etat.equals("0"))
			{test.setEtat("1");
			ImageView Lampe = (ImageView) findViewById(R.id.resultLampe);
			Lampe.setImageDrawable(getResources().getDrawable(R.drawable.lampeeteinte));}
			}
		});
	}

	//Méthode qui affiche en rouge en haut de l'activity
	public void printUp(){
		handler.post(edit);
	}
	
	//Getters et Setters
	public GestionReseau getGr() {
		return gR;
	}

	public void setGr(GestionReseau gr) {
		this.gR = gr;
	}
}
