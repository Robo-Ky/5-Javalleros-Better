package main.java.edu.gatech;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBalance extends Activity {
	private DBHandler database = new DBHandler(AddBalance.this);
	private EditText amountBox = (EditText) findViewById(R.id.addBalanceBox);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_balance);
		Button addButton = (Button) findViewById(R.id.addBut);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				attemptAdd();
			}
		});
		Button cancelBut = (Button) findViewById(R.id.cancelBut2);
		cancelBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("clicks", "Clicked return button");
				AccessingAccount.finish();
				Intent i = new Intent(AddBalance.this, AccountsPageActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_balance, menu);
		return true;
	}
	
	private void attemptAdd() {
		String adding = amountBox.getText().toString();
		boolean added = false;
		if (adding != null) {
			float amount = Float.valueOf(adding);
			if (amount > 0) {
				AccessingAccount.addFunds(amount);
				AccessingAccount.setTrans(new Transaction(null, AccessingAccount.getAccName(), amount));
				database.setBalanceAndLog();
				added = true;
			}
		}
		if (!added) {
			amountBox.setError("Failed to add funds.");
			amountBox.requestFocus();
		}
	}
}
