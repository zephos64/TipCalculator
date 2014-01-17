package com.codepath.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText etAmount;
	private TextView tvTipAmount;
	private Button btnSmallTip;
	private Button btnMiddleTip;
	private Button btnLargeTip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etAmount = (EditText) findViewById(R.id.etAmount);
		tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
		btnSmallTip = (Button) findViewById(R.id.btnSmallTip);
		btnMiddleTip = (Button) findViewById(R.id.btnMiddleTip);
		btnLargeTip = (Button) findViewById(R.id.btnLargeTip);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void newTip(View v) {
		int amount = Integer.valueOf(etAmount.getText().toString());
		Button clickedButton = (Button) v;
		
		switch(clickedButton.getId()) {
			case R.id.btnSmallTip:
				amount*=.10;
				break;
			case R.id.btnMiddleTip:
				amount*=.15;
				break;
			case R.id.btnLargeTip:
				amount*=.20;
				break;
			default:
				break;
		}
		
		tvTipAmount.setText("$"+String.valueOf(amount));
	}
}
