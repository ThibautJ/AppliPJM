package com.example.applicationfinale;

public class Lecteur {
	protected String[] tab;

	public Lecteur(){
		tab = new String[5];
	}

	public Lecteur(int a){
		tab = new String[a];
	}
	//Getters setters
	public String[] getTab() {
		return tab;
	}

	public void setTab(int i, String str) {
		tab[i] = str;
	}
}
