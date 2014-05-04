package com.example.applicationfinale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class DomotiqueWindow extends Activity {

	private CapteurTOR test;
	private GestionReseau gr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_domotique_window);
		
		test=new CapteurTOR();
		test.setEtat("1");
		
		
		gr = new GestionReseau();
		new Thread(gr).start();
		
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

	public GestionReseau getGr() {
		return gr;
	}

	public void setGr(GestionReseau gr) {
		this.gr = gr;
	}
}
