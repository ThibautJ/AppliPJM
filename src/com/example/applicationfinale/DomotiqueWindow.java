package com.example.applicationfinale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DomotiqueWindow extends Activity {

	private ActionneurTOR lampeTest;
	private GestionReseau gR;
	private LinearLayout layout;
	private TextView tv;
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("moi","debut domotiquewindow");
		
		setContentView(R.layout.activity_domotique_window);
		
		layout = (LinearLayout)findViewById(R.id.layout_activity_window);
		tv = (TextView)findViewById(R.id.communication);
		
		gR = new GestionReseau(this);
		new Thread(gR).start();


		lampeTest = new ActionneurTOR(gR);
		lampeTest.setEtatB(true);



		final Button buttonfonction = (Button) findViewById(R.id.lampe);
		buttonfonction.setOnClickListener(new OnClickListener()  {

			public void onClick(View v) {
				boolean etat;
				etat = lampeTest.getEtatB();
				System.out.println(etat);

				if (etat){
					lampeTest.eteindre();
					ImageView Lampe = (ImageView) findViewById(R.id.resultLampe);
					Lampe.setImageDrawable(getResources().getDrawable(R.drawable.lampeallumee));
				}
				else {
					lampeTest.allumer();
					ImageView Lampe = (ImageView) findViewById(R.id.resultLampe);
					Lampe.setImageDrawable(getResources().getDrawable(R.drawable.lampeeteinte));
				}
			}
		});
	}

	//Getters et Setters
	public GestionReseau getGr() {
		return gR;
	}

	public void setGr(GestionReseau gr) {
		this.gR = gr;
	}

	public void setTextCommunication(String text){
		TextView v = (TextView) findViewById(R.id.communication);
		v.setText(text);
	}

	public LinearLayout getLayout() {
		return layout;
	}

	public TextView getTv() {
		return tv;
	}
	
	
}
