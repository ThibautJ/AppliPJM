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

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GestionReseau implements Runnable{
	private static final int SERVERPORT = 9999;
	private static final String SERVER_IP = "192.168.167.102";
	private Socket socket;
	private BufferedReader bReader;
	private EditText et;
	private Lecteur lec;
	private String str;
	private DomotiqueWindow act;
	private boolean attente;
	final Handler handler = new Handler();
	private LinearLayout layoutAct;
	private LinearLayout layoutCapt;
	private LinearLayout layout;
	private TextView tv;



	public GestionReseau(DomotiqueWindow ac){
		act = ac;
	}


	@Override
	public void run() {

		
		tv = (TextView) act.findViewById(R.id.communication);



		//Définition de la connexion et appel de la socket
		try {
			Log.v("moi","début création de socket");
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
			socket = new Socket(serverAddr, SERVERPORT);
			Log.v("moi","Socket créée");
			tv.post(new Runnable(){
				public void run(){
					tv.setText("");
				}
			});
		} catch (UnknownHostException e1) {
			Log.v("moi","Problème de connexion");
			e1.printStackTrace();
		} catch (IOException e1) {
			Log.v("moi","Problème de connexion");
			tv.post(new Runnable(){
				public void run(){
					tv.setText("Problème de connexion");
				}
			});
			e1.printStackTrace();
		}

		//Import du layout
		layoutAct = act.getLayoutAct();
		layoutCapt = act.getLayoutCapt();
		layout = act.getLayout();


		try {
			//Création du Reader
			Log.v("moi","debut création du reader");
			InputStream is = socket.getInputStream();
			Log.v("moi", "InputStream créé");
			Reader reader = new InputStreamReader(new BufferedInputStream(is));
			bReader = new BufferedReader(reader);
			Log.v("moi", "Le Reader est créé");
			//Thread pour recevoir en continu l'état des capteurs
			new Thread(new Runnable(){
				public void run(){
					try {
						while(true){
						PrintWriter out = new PrintWriter(new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream())),
								true);
						out.println("(all;getEtat)");
						Thread.sleep(15000);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					).start();



			//POURQUOI NE PAS METTRE LA SUITE DANS UN RUNNABLE ET LANCER CE RUNNABLE DANS UN THREAD???
			//Edition en temps réel du lecteur
			while(true){
				while(attente==false){
					str = bReader.readLine();
					Log.v("moi", "Instruction reçu : " + str);
					attente = true;

					//Changement des boutons
					layout.post(new Runnable(){
						public void run(){
							//Exécution du translate
							Log.v("moi", "Début de la lecture de l'instruction");
							translate(str);
							Log.v("moi", "La lecture a été effectuée");
							attente = false;
						}
					});
					Thread.sleep(20);
				}			

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("moi","Exception dans la main");
		} catch (NullPointerException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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



	public void translate(String str){
		final int number;
		final int index1;
		final int index2;
		final int index3;
		final int index4;
		
		final String type;
		String typeBis;
		String name;
		final String order;
		final String etat;


		index1 = str.indexOf(';');
		index2 = str.indexOf(';', index1 + 1);
		index3 = str.indexOf(';', index2 + 1);
		type = str.substring(1, index1);
		number = Integer.parseInt(str.substring(index1 + 1, index2));
		order = str.substring(index2 + 1,index3);
		typeBis = null;
		name = null;


		//L'initialisation des éléments
		if(order.equals("setInit")){
			index4 = str.indexOf(';', index3 + 1);
			typeBis = str.substring(index3 + 1,index4);
			name = str.substring(index4 + 1, str.length() - 1);
			//Début modif
			if(type.equals("tor")){
				if (typeBis.equals("output")){
					//Si c'est un ActionneurTOR	
					Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);		
					new ActionneurTOR(this,number,name);
				}

				else if (typeBis.equals("input")){
					//Si c'est un CapteurTOR
					Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);			
					new CapteurTOR(this,number,name);
				}
			}
			else if (type.equals("analogique")){
				if (typeBis.equals("output")){
					//Si c'est un ActionneurAN
					Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);		
					new ActionneurAN(this,number,name);	
				}
				else if (typeBis.equals("input")){
					//Si c'est un CapteurAN
					Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);
					new CapteurAN(this,number,name);
				}
			}
		}
		//fin modif
		//Pour une mise à jour de l'état		
				else if(order.equals("etat")){
					etat = str.substring(index3 + 1, str.length() - 1);
					Element[] liste = Element.liste;
					if(type.equals("tor")){
						Boolean b;
						if(etat.equals("1")){
							b = true;
						}
						else{
							b = false;
						}
						if (liste[number] instanceof ActionneurTOR){
							((ActionneurTOR)liste[number]).setEtatB(b);
						}
						else if (liste[number] instanceof CapteurTOR){
							((CapteurTOR)liste[number]).setEtatB(b);
						}
					}
					else if (type.equals("analogique")){
						//A écrire
					}
				}

		/*if(type.equals("tor")){
			if (typeBis.equals("output")){
				Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);
				//Si c'est un ActionneurTOR
				switch(o){
				case 1 :
					Log.v("moi", "Tentative d'ajout de bouton d'actTOR");
					//ajout bouton				
					new ActionneurTOR(this,number,name);
					break;
				case 2 :
					Boolean b = Boolean.valueOf(name);
					Log.v("moi", "Réception de l'état de l'actTOR");
					Element[] liste = ActionneurTOR.liste;
					//liste[number].setEtatB(b);
				default:
					Log.v("moi", "Ordre intraduisible : " + order);
					break;
				}
			}
			else if (typeBis.equals("input")){
				Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);
				//Si c'est un CapteurTOR
				switch(o){
				case 1 :
					Log.v("moi", "Tentative d'ajout de bouton de capteurTOR");
					//ajout bouton				
					new CapteurTOR(this,number,name);
					break;
				case 2 :
					Boolean b;
					if(name.equals("1")){
						b = true;
					}
					else{
						b = false;
					}
					Log.v("moi", "Réception de l'état de l'actTOR");
					//CapteurTOR[] liste = CapteurTOR.listCapteurTOR;
					//liste[number].setEtatB(b);
					break;
				default:
					Log.v("moi", "Ordre intraduisible : " + order);
					break;
				}
			}
		}
		else if (type.equals("analogique")){
			if (typeBis.equals("output")){
				Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);
				//Si c'est un ActionneurAN
				switch(o){
				case 1 :
					Log.v("moi", "Tentative d'ajout de bouton de Actionneur AN");
					//ajout bouton				
					new ActionneurAN(this,number,name);
					break;
				default:
					Log.v("moi", "Ordre intraduisible : " + order);
					break;
				}
			}
			else if (typeBis.equals("input")){
				Log.v("moi", "type = "+ type + " number = " + number + " typeBis = " + typeBis);
				//Si c'est un CapteurAN
				switch(o){
				case 1 :
					Log.v("moi", "Tentative d'ajout de bouton de capteurAN");
					//ajout bouton				
					new CapteurAN(this,number,name);
					break;
				default:
					Log.v("moi", "Ordre intraduisible : " + order);
					break;
				}
			}
		}*/	
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


	public LinearLayout getLayoutAct() {
		return layoutAct;
	}


	public void setLayoutAct(LinearLayout layoutAct) {
		this.layoutAct = layoutAct;
	}


	public LinearLayout getLayoutCapt() {
		return layoutCapt;
	}


	public void setLayoutCapt(LinearLayout layoutCapt) {
		this.layoutCapt = layoutCapt;
	}


	public void setLayout(LinearLayout layout) {
		this.layout = layout;
	}
	
	
}


