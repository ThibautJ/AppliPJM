package com.example.applicationfinale;

public abstract class Element {
	protected String nom;
	protected boolean connecte;
	protected String piece;
	protected GestionReseau gR;
	public static Element[] liste;
	protected int nombre;
	
	public Element(GestionReseau geR, int nombre){
		gR = geR;
		liste[nombre] = this;
	}
	
	
	
	protected String getNom(){
		return nom;
	}
	protected boolean getConnecte(){
		return connecte;
	}
	protected String getPiece(){
		return piece;
	}
}
