package main.java.edu.gatech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHandler {
	
	private DBHelper helper;
	private SQLiteDatabase database;
	private static final String TABLENAME = "Users";
	private static final String TABLENAME_2 = "Accounts";
	private static String loggedInEmail = User.getLoggedInEmail();
	
	public DBHandler(Context context){
		helper = new DBHelper(context);
		database = helper.getWritableDatabase();
	}
	
	public long createUser(String email, String password){
		Log.d("createUser", "Entering createUser()");
		ContentValues values = new ContentValues();
		values.put("Email", email);
		values.put("Password", password);
		Log.d("createUser", "Creating: " + email + " and " + password);
		long temp = database.insert(TABLENAME, null, values);
		return temp;
	}
	
	public Cursor selectUser(String email, String password){
		Log.d("selectUser", "Entering selectUser()");
		Cursor myCursor = database.rawQuery("select * from Users where Email=? and Password=?" ,new String [] {email, password});
		if (myCursor != null) {
			Log.d("selectUser", "myCursor != null");
		}
		return myCursor;
	}
	
	public long createAccount(String email, String accountName) {
		Log.d("createAccount", "Entering createAccount()");
		ContentValues values = new ContentValues();
		values.put("Email", email);
		values.put("AccountName", accountName);
		Log.d("createAccount", "Creating account: " + email + " and " + accountName);
		long temp = database.insert(TABLENAME_2, null, values);
		return temp;
	}
	
	public Cursor selectAccount(String email, String accountName) {
		Log.d("selectAccount", "Entering selectAccount");
		Cursor myCursor = database.rawQuery("select * from " +  TABLENAME_2 + " where Email=? and AccountName=?", new String[]{email, accountName});
		if (myCursor != null) {
			Log.d("selectAccount", "myCursor != null");
		}
		return myCursor;
	}
	
	public String getAllAccounts() {
		Log.d("getAllAccounts", "Entering getAllAccounts()");
		Cursor myCursor = database.rawQuery("select * from Accounts", null);
		String end = "";
		if (myCursor.moveToFirst()) {
			while (!myCursor.isAfterLast()) {
				String emailKey = myCursor.getString(myCursor.getColumnIndex("Email"));
				if (emailKey.equals(loggedInEmail)) {
					String name = myCursor.getString(myCursor.getColumnIndex("AccountName"));
					end += name + "\n";
				}
				myCursor.moveToNext();
			}
		}
		return end;
	}
}

