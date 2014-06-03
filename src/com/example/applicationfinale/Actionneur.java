package com.example.applicationfinale;

public abstract class Actionneur extends Element {
	protected boolean commande;
	
	public Actionneur(GestionReseau geR, int nombre){
		super(geR, nombre);
	}
}
