package main.java.edu.gatech;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "Minimint";
	private static final String TABLE_ACCOUNTS = "Accounts";
	private static final String DATABASE_CREATE = "Create table Users(Email text primary key, Password text not null)";
	private static final String DATABASE_ACCOUNTS_CREATE = "Create table " +  TABLE_ACCOUNTS + "(Email text, AccountName text not null, Balance real)";
	
	public DBHelper(Context context){
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.beginTransaction();
		try {
			database.execSQL(DATABASE_CREATE);
			database.execSQL(DATABASE_ACCOUNTS_CREATE);
			database.setTransactionSuccessful();
		}
		finally {
			database.endTransaction();
		}
	}
	
	public void createTableTransactions(Account acc, SQLiteDatabase database) {
		String NAME = acc.getName();
		String LOG_TABLE_CREATE = "Create table " + NAME + "(Type text not null, Source text, Destination text, Amount real)";
		database.execSQL(LOG_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
