package main.java.edu.gatech;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountsPageActivity extends Activity {
	
	private ShowAccountsTask accShowTask;
	
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
		accShowTask = new ShowAccountsTask();
		accShowTask.execute((Void) null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	private class ShowAccountsTask extends AsyncTask<Void, Void, Boolean> {
		private DBHandler database = new DBHandler(AccountsPageActivity.this);
		private TextView accountsView = (TextView) findViewById(R.id.textView2);

		@Override
		protected Boolean doInBackground(Void... arg0) {
			String accountList = database.getAllAccounts();
			accountsView.setText(accountList);
			return true;
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
			accShowTask = null;
		}
		
		@Override
		protected void onCancelled() {
			accShowTask = null;
		}
	}
}
