package main.java.edu.gatech;

public class AccessingAccount {
	private static AccessingAccount accInstance = null;
	private static Account accessAccount;
	private static Transaction trans;
	
	protected AccessingAccount(String accName, float accBalance) {
		accessAccount = new Account(accName, accBalance);
	}
	
	public static synchronized AccessingAccount getInstance(String accName, float accBalance) {
		if (accInstance == null) {
			accInstance = new AccessingAccount(accName, accBalance);
		}
		return accInstance;
	}
	
	public static void addFunds(float amount) {
		accessAccount.addFunds(amount);
	}
	
	public static void subtractFunds(float amount) {
		accessAccount.subtractFunds(amount);
	}
	
	public static void setTrans(Transaction transaction) {
		trans = transaction;
	}
	
	public static String getAccName() {
		return accessAccount.getName();
	}
	
	public static float getBalance() {
		return accessAccount.getBalance();
	}
	
	public static Transaction getTrans() {
		return trans;
	}
	
	public static void finish() {
		accInstance = null;
		accessAccount = null;
	}
}
