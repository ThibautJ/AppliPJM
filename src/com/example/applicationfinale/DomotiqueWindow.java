package com.example.applicationfinale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DomotiqueWindow extends Activity {

	private GestionReseau gR;
	private LinearLayout layoutAct;
	private LinearLayout layoutCapt;
	private LinearLayout layout;
	private TextView tv;
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("moi","debut domotiquewindow");
		
		setContentView(R.layout.activity_domotique_window);
		
		layout = (LinearLayout)findViewById(R.id.layout_activity_window);
		layoutAct = (LinearLayout)findViewById(R.id.layout_activity_window_act);
		layoutCapt = (LinearLayout)findViewById(R.id.layout_activity_window_capt);
		tv = (TextView)findViewById(R.id.communication);
		
		
		gR = new GestionReseau(this);
		new Thread(gR).start();
	}

	//Getters et Setters
	public GestionReseau getGr() {
		return gR;
	}

	public void setGr(GestionReseau gr) {
		this.gR = gr;
	}


	public LinearLayout getLayoutAct() {
		return layoutAct;
	}
	

	public void setLayoutAct(LinearLayout layout) {
		this.layoutAct = layout;
	}

	public TextView getTv() {
		return tv;
	}

	public LinearLayout getLayoutCapt() {
		return layoutCapt;
	}

	public void setLayoutCapt(LinearLayout layoutCapt) {
		this.layoutCapt = layoutCapt;
	}

	public LinearLayout getLayout() {
		return layout;
	}

	public void setLayout(LinearLayout layout) {
		this.layout = layout;
	}
	
	
	
	
}
