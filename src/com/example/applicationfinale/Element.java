package com.example.applicationfinale;

public abstract class Element {
	protected String nom;
	protected boolean connecte;
	protected String piece;
	
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
