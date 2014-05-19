package com.example.applicationfinale;

import android.app.Activity;
import android.widget.TextView;

public class GestionView {
	
	private Activity act;
	
	public GestionView(Activity ac){
		ac = act;
	}
		
	public void setTextCommunication(String text){
		TextView v = (TextView) act.findViewById(R.id.communication);
		v.setText(text);
	}
}
