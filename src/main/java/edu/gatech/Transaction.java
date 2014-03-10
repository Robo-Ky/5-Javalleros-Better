package main.java.edu.gatech;

public class Transaction {
	private String type, sName, dName;
	private float amount;
	
	public Transaction(String source, String destination, float amount) {
		this.amount = amount;
		if (source != null && destination != null) {
			type = "Transfer";
			sName = source;
			dName = destination;
		}
		else if (source == null) {
			type = "Deposit";
			sName = null;
			dName = destination;
		}
		else if (destination == null) {
			type = "Withdrawal";
			sName = source;
			dName = null;
		}
	}
	
	public float getAmount() {
		return amount;
	}
	
	public String getDest() {
		return dName;
	}
	
	public String getSrc() {
		return sName;
	}
	
	public String getType() {
		return type;
	}
}
