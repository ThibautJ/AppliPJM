package com.example.applicationfinale;



import java.util.StringTokenizer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);											//D�finition du background de l'application
		
		
		
		
		final Button buttonsecurite = (Button) findViewById(R.id.securite);				
		buttonsecurite.setOnClickListener(new OnClickListener()  {
			
			String CapteurP= "ferm�";
			String CapteurF= "ferm�";
		
		public void onClick(View v) {													//Lors de l'appui sur le bouton s�curit� 
			
			Bundle b=new Bundle();														//Cr�ation du bundle de passage des informations
			b.putString("CapteurP",CapteurP);											//Informations � transmettre pr�c�d�es de leur identifiant
			b.putString("CapteurF",CapteurF);
			Intent intent = new Intent(getApplicationContext(),SecuriteWindow.class);	//intent de lancement de l'activit� S�curit�
			intent.putExtras(b);							
			startActivity(intent);														//D�marrage de l'activit� S�curit�
			
			
		}
		
		});
		final Button buttonfonctions = (Button) findViewById(R.id.fonctions);			//Idem pour l'activit� Fonctions
		buttonfonctions.setOnClickListener(new OnClickListener()  {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,FonctionWindow.class);
				startActivity(intent);
			}
			
			});
		
		
		
		
		
		final Button buttonreglages = (Button) findViewById(R.id.reglages);				//Idem pour l'activit� R�glages
		buttonreglages.setOnClickListener(new OnClickListener()  {
		
		public void onClick(View v) {
			String maStr = "(test1; test2; test3; test4)";
			StringTokenizer st1=new StringTokenizer(maStr,";");
			String s1=st1.nextToken();
			s1 = s1.replaceAll("[^\\w]","");
			String s2=st1.nextToken();
			s2 = s2.replaceAll("[^\\w]","");
			String s3=st1.nextToken();
			s3 = s3.replaceAll("[^\\w]","");
			String s4=st1.nextToken();
			s4 = s4.replaceAll("[^\\w]","");
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			System.out.println(s4);
			
			Intent intent = new Intent(getApplicationContext(),Reglages.class);
			startActivity(intent);
		}
		
		});
		
		final Button buttondomotique = (Button) findViewById(R.id.domotique);				//Idem pour l'activit� R�glages
		buttondomotique.setOnClickListener(new OnClickListener()  {
		
		public void onClick(View v) {
		
			Intent intente = new Intent(getApplicationContext(),DomotiqueWindow.class);
			startActivity(intente);
		}
		
		});
		}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	

	}


