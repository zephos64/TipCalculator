package com.codepath.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText etAmount;
	private TextView tvTipAmount;
	private EditText etCustTipAmount;
	private RadioGroup rgTipAmount;
	
	private double tipAmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etAmount = (EditText) findViewById(R.id.etAmount);
		etCustTipAmount = (EditText) findViewById(R.id.etCustTipAmount);
		tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
		
		rgTipAmount = (RadioGroup) findViewById(R.id.rgTipAmount);
		
		tipAmount = .15;
		rgTipAmount.check(R.id.rgbMiddleTip);
		
		setUpViewListener();
		displayTip();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void displayTip() {
		double amount;
		if(etAmount.length() == 0) {
			amount = 0;
		} else {
			amount = Integer.valueOf(etAmount.getText().toString());
		}
		
		tvTipAmount.setText("$"+String.valueOf(tipAmount * amount));
	}
	
	private void custTipErr() {
		Toast.makeText(
				this,
				"No custom tip amount selected,  defaulting to 15%",
				Toast.LENGTH_SHORT)
				.show();
	}
	
	private void setUpViewListener() {
		etAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
					displayTip();
			}
		});
		
		etCustTipAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(rgTipAmount.getCheckedRadioButtonId() == R.id.rgbCustTip) {
					setCustTip();
					displayTip();
				}
			}
		});
		
		rgTipAmount.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId) {
					case R.id.rgbSmallTip:
						tipAmount=.10;
						break;
					case R.id.rgbMiddleTip:
						tipAmount=.15;
						break;
					case R.id.rgbLargeTip:
						tipAmount=.20;
						break;
					case R.id.rgbCustTip:
						setCustTip();
						break;
					default:
						break;
				}
				
				displayTip();
			}
		});
	}
	
	private void setCustTip() {
		if(etCustTipAmount.length() == 0) {
			custTipErr();
			tipAmount=.15;
		} else {
			tipAmount= .01 * Integer.valueOf(etCustTipAmount.getText().toString());
		}
	}
}
