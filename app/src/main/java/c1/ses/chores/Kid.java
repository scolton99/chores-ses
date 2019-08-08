import java.util.HashMap;
import java.util.Map;

public class Kid {

	// Child name
	private String name;
	// ID of parent to link
	private String parent_id;
	// A goal (item, experience, etc.) that child is working for
	private Map<String, Double> goal = new HashMap<String, Double>();
	// Checking/savings account balances
	private Map<String, Double> accounts = new HashMap<String,Double>();	

	public Kid(String name, String parent, Double startCheckBal) {
		this.name = name;
		this.parent_id = parent;

		// Starting account balances
		// Checking is money given by the parents. Think of as direct deposit target.
		accounts.put("checking", startCheckBal);
		// Savings is money put towards a goal.
		accounts.put("savings", 0.0);
	}



}