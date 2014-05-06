package com.example.applicationfinale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FonctionWindow extends Activity {

	private CapteurTOR test;
	private GestionReseau gr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fonction_window);
		
		test=new CapteurTOR(gr);
		test.setEtat("1");
		
		final Button buttonfonction = (Button) findViewById(R.id.resultLampe);				
		buttonfonction.setOnClickListener(new OnClickListener()  {
			
			public void onClick(View v) {
			
			
			String etat;
			etat=test.getEtat();
			System.out.println(etat);
		
			if (etat.equals("1"))
			{test.setEtat("0");}
			else if (etat.equals("0"))
			{test.setEtat("1");}
			
	}

	

		});
	}
}
