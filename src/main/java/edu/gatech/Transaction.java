package main.java.edu.gatech;

public class Transaction {
	private String type, sName, dName;
	private float amount;
	
	public Transaction(Account source, Account destination, float amount) {
		this.amount = amount;
		if (source != null && destination != null) {
			type = "Transfer";
			
		}
		else if (source == null) {
			type = "Deposit";
			
		}
		else if (destination == null) {
			type = "Withdrawal";
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
