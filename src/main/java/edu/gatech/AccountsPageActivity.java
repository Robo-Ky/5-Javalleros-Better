package main.java.edu.gatech;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class AccountsPageActivity extends Activity {
	private DBHandler database = new DBHandler(AccountsPageActivity.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accounts_homepage);
		Button btn1 = (Button) findViewById(R.id.returnButton);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("clicks", "Clicked return button");
				Intent i = new Intent(AccountsPageActivity.this, MainMenu.class);
				startActivity(i);
			}
		});
		Button btn2 = (Button) findViewById(R.id.transButton);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(AccountsPageActivity.this, TransactionActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	private void setSpinnerElements() {
		List<String> accounts = database.getAllAccounts();
	}
}
