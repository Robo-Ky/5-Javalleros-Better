package main.java.edu.gatech;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.os.*;
import android.widget.*;
import android.util.*;

public class AccountForTrans extends Activity {
	
	private AsyncTask userTaskSelectAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_for_trans);
		Button returnbutton = (Button) findViewById(R.id.button1);
        returnbutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Log.i("clicks","You clicked Return");
        		Intent i = new Intent (
        				AccountForTrans.this,
        				TransactionActivity.class);
        		startActivity(i);
        	}
  
        });
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_for_trans, menu);
		return true;
	}
	
	
	
	public class userTaskSelectAccount extends AsyncTask<Void, Void, Boolean>{
		private DBHandler accounts = new DBHandler(AccountForTrans.this);
		private TextView viewAccounts = (TextView)findViewById(R.id.textView1);
		//Button buttons[] = new Button(ac);

	
		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			String accountList = accounts.getAllAccounts();
			String[] accounts = accountList.split("//s+");
			Button[] buttons = new Button[accounts.length];
			for(int i=0; i< accounts.length; i++){
				viewAccounts.setText(i);
			}
			viewAccounts.setText(accountList);
			
			
			return null;
		}
		
		protected void onPostExecute(){
			userTaskSelectAccount = null;
		}
		
		protected void onCancelled(){ 
			userTaskSelectAccount = null;
		}
		
	}

}
	
