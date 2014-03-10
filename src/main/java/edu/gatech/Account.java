package main.java.edu.gatech;

public class Account {
	private String name;
	private float balance;
	
	public Account(String name) {
		this.name = name;
		balance = 0;
	}
	
	public Account(String name, float balance) {
		this.name = name;
		this.balance = balance;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public void addFunds(float amount) {
		balance += amount;
	}
	
	public void subtractFunds(float amount) {
		balance -= amount;
	}
}
