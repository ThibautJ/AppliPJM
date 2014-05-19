package com.example.applicationfinale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FonctionWindow extends Activity {

	private ActionneurTOR lampeTest;
	private GestionReseau gr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fonction_window);
		
		lampeTest = new ActionneurTOR(gr);
		lampeTest.setEtatB(true);
		
		final Button buttonfonction = (Button) findViewById(R.id.resultLampe);				
		buttonfonction.setOnClickListener(new OnClickListener()  {
			
			public void onClick(View v) {
			
			
			boolean etat;
			etat = lampeTest.getEtatB();
			System.out.println(etat);
		
			if (etat){
			lampeTest.setEtatB(false);
			}
			else {
				lampeTest.setEtatB(true);
			}			
	}

	

		});
	}
}
