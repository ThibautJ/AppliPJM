package com.example.applicationfinale;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class ActionneurTOR extends Actionneur {
	private boolean etatB;
	private Button button;
	private LinearLayout layout;
	private Activity activity;
	private ImageView lampe;
	
	//Constructeur
	public ActionneurTOR(GestionReseau geR , int nombre){
		super(geR, nombre);	
	}
	//Constructeur avec ajout de bouton
	public ActionneurTOR(GestionReseau geR,int nombre,String name){
		super(geR, nombre);
		activity = geR.getAct();
		button = new Button(activity);
		layout = geR.getLayoutAct();
		LinearLayout layoutI = new LinearLayout(activity);
		button.setText(name);
		lampe = new ImageView(activity);
		if(etatB){
			lampe.setImageDrawable(activity.getResources().getDrawable(R.drawable.lampeallumee));
		}
		else{
			lampe.setImageDrawable(activity.getResources().getDrawable(R.drawable.lampeeteinte));
		}
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				boolean etat;
				etat = etatB;
				System.out.println(etat);
				

				if (etat){
					ActionneurTOR.this.eteindre();
					lampe.setImageDrawable(activity.getResources().getDrawable(R.drawable.lampeeteinte));
				}
				else {
					ActionneurTOR.this.allumer();
					lampe.setImageDrawable(activity.getResources().getDrawable(R.drawable.lampeallumee));
				}
			}
		});
		button.setWidth(600);
		lampe.setPadding(50, 0, 50, 0);
		layoutI.addView(button);
		layoutI.addView(lampe);
		layout.addView(layoutI);
		geR.getAct().setLayoutAct(layout);
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
		clicOn();
	}
	
	public void eteindre(){
		this.setEtatB(false);
		clicOff();
	}
	

	//Méthode de on
	public void clicOn(){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(gR.getSocket().getOutputStream())),
					true);
			out.println("(tor;"+nombre+";setEtat;1)");
			Log.v("moi", "LED allumée");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Méthode de off

	public void clicOff(){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(gR.getSocket().getOutputStream())),
					true);
			out.println("(tor;"+nombre+";setEtat;0)");
			Log.v("moi", "Led éteinte");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
