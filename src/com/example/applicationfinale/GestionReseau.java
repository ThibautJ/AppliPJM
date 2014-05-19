package com.example.applicationfinale;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GestionReseau implements Runnable{
	private static final int SERVERPORT = 9999;
	private static final String SERVER_IP = "192.168.167.102";
	private Socket socket;
	private BufferedReader bReader;
	private String on;
	private String off;
	private boolean b;
	private EditText et;
	private TextView tv;
	private Lecteur lec;
	private String[] tab;
	private String str0;
	private DomotiqueWindow act;
	final Handler handler = new Handler();
	private LinearLayout layout;
	
	
	
	public GestionReseau(DomotiqueWindow ac){
		act = ac;
	}
	

	@Override
	public void run() {

		on = "a";
		off = "b";
		b = true;
		
		

		//Définition de la connexion et appel de la socket
		try {
			Log.v("moi","début création de socket");
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
			socket = new Socket(serverAddr, SERVERPORT);
			Log.v("moi","Socket créée");
		} catch (UnknownHostException e1) {
			//Si le socket ne s'est pas créé, alors il y a cette exception
			Log.v("moi","Problème de connexion");
			act.setTextCommunication("Problème de connexion");
			act.finish();
			b = false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Import du layout
		layout = act.getLayout();
		//Import du TextView
		tv = act.getTv();

		
		try {
			//Création du Reader
			Log.v("moi","debut création du reader");
			InputStream is = socket.getInputStream();
			Log.v("moi", "InputStream créé");
			Reader reader = new InputStreamReader(new BufferedInputStream(is));
			bReader = new BufferedReader(reader);
			Log.v("moi", "Le Reader est créé");

			//Création du Lecteur
			lec = new Lecteur();
			

			
			//POURQUOI NE PAS METTRE LA SUITE DANS UN RUNNABLE ET LANCER CE RUNNABLE DANS UN THREAD???
			//Edition en temps réel du lecteur
			while(true){
				String str = bReader.readLine();
				Log.v("moi", "J'ai lu quelquechose");
				lec.setTab(0, str);
				Log.v("moi", "Ce quelquechose a été écrit dans le lecteur");
				tv.post(new Runnable(){
					public void run(){
						tab = getLec().getTab();
						str0 = tab[0];
						act.setTextCommunication(str0);
						Log.v("moi", "setTextCommunication effectué");
					}
				});
				//reading(lec,str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("moi","Exception dans la main");
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		
	}


	//Méthode qui se déclenche quand on appuie sur send
	public void siClic(View view) {
		try {
			Log.v("moi", "Message envoyé");
			String str = et.getText().toString();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(str);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Méthode de on
	public void clicOn(){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(on);
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
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(off);
			Log.v("moi", "Led éteinte");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reading(Lecteur lec, String str){
		final int c;
		final char type;
		final int number;
		final int indexOfLastChar;
		final String name;
		final String order;

		
		c = 6;
		type = str.charAt(2);
		indexOfLastChar = str.indexOf(')');
		
		if (type == 't'){
			//Si c'est un TOR
			number = str.charAt(6);
			name = "TOR " + number;
			order = str.substring(8,indexOfLastChar);
			lec.setTab(number,str);
			if (order.equals("setInit")){
				//ajout bouton				
				new ActionneurTOR(this,number,name);
			}
		}
	}
	

	//Socket & Getters

	public DomotiqueWindow getAct() {
		return act;
	}


	public Socket getSocket() {
		return socket;
	}



	public void setSocket(Socket socket) {
		this.socket = socket;
	}



	public Lecteur getLec() {
		return lec;
	}


	public LinearLayout getLayout() {
		return layout;
	}
	
	

}


