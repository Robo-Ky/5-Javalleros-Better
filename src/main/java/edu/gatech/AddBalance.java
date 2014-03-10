package main.java.edu.gatech;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AddBalance extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_balance);
		Button cancelBut = (Button) findViewById(R.id.cancelBut2);
		cancelBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("clicks", "Clicked return button");
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

}
