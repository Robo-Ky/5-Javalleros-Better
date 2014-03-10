package main.java.edu.gatech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

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
		if (tableExists(accountName)) {
			return 0;
		}
		ContentValues values = new ContentValues();
		values.put("Email", email);
		values.put("AccountName", accountName);
		values.put("Balance", 0);
		Log.d("createAccount", "Creating account: " + email + " and " + accountName);
		long temp = database.insert(TABLENAME_2, null, values);
		String createLog = "Create table " + accountName + "(Type text not null, Amount real, Destination text)";
		database.execSQL(createLog);
		return temp;
	}
	
	private boolean tableExists(String tableName) {
		Cursor myCursor = database.rawQuery("select DISTINCT tbl_name from Minimint where tbl_name = '" + tableName + "'", null);
		if (myCursor != null) {
			return true;
		}
		return false;
	}
	
	public Cursor selectAccount(String email, String accountName) {
		Log.d("selectAccount", "Entering selectAccount");
		Cursor myCursor = database.rawQuery("select * from " +  TABLENAME_2 + " where Email=? and AccountName=?", new String[]{email, accountName});
		if (myCursor != null) {
			Log.d("selectAccount", "myCursor != null");
		}
		return myCursor;
	}
	
	public ArrayList<String> getAllAccounts() {
		Log.d("getAllAccounts", "Entering getAllAccounts()");
		Cursor myCursor = database.rawQuery("select * from Accounts", null);
		ArrayList<String> accounts = new ArrayList();
		if (myCursor.moveToFirst()) {
			while (myCursor.moveToNext()) {
				String emailKey = myCursor.getString(myCursor.getColumnIndex("Email"));
				if (emailKey.equals(loggedInEmail)) {
					String name = myCursor.getString(myCursor.getColumnIndex("AccountName"));
					accounts.add(name);
				}
				myCursor.moveToNext();
			}
		}
		return accounts;
	}
	
	public void setBalanceAndLog() {
		String accName = AccessingAccount.getAccName();
		Transaction trans = AccessingAccount.getTrans();
		String updateQuery = "update Accounts set Balance=" + AccessingAccount.getBalance() + "where Id='" + User.getLoggedInEmail() + "'";
		database.execSQL(updateQuery);
		ContentValues values = new ContentValues();
		values.put("Type", trans.getType());
		values.put("Amount", trans.getAmount());
		values.put("Destination", trans.getDest());
		database.insert(accName, null, values);
	}
}

