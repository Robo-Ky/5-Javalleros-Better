package main.java.edu.gatech;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubtractBalance extends Activity {
	private DBHandler database = new DBHandler(SubtractBalance.this);
	private EditText amountBox = (EditText) findViewById(R.id.subtractBalanceBox);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subtract_balance);
		Button subButton = (Button) findViewById(R.id.subBut);
		subButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				attemptSubtract();
			}
		});
		Button cancelBut = (Button) findViewById(R.id.cancelBut3);
		cancelBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("clicks", "Clicked return button");
				Intent i = new Intent(SubtractBalance.this, AccountsPageActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subtract_balance, menu);
		return true;
	}
	
	private void attemptSubtract() {
		String subtracting = amountBox.getText().toString();
		boolean subtracted = false;
		if (subtracting != null) {
			float amount = Float.valueOf(subtracting);
			if (amount > 0) {
				AccessingAccount.subtractFunds(amount);
				AccessingAccount.setTrans(new Transaction(AccessingAccount.getAccName(), null, amount));
				database.setBalanceAndLog();
				subtracted = false;
			}
		}
		if (!subtracted) {
			amountBox.setError("Failed to subtract funds.");
			amountBox.requestFocus();
		}
	}

}
