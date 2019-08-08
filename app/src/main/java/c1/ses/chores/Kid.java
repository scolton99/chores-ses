/* Megan St. Hilaire
   Capital One SES Hackathon --> DUCKLING$
   This file represents a single child under a parent account.
*/

<<<<<<< HEAD
import java.text.DecimalFormat;
=======
package c1.ses.chores;
>>>>>>> 8f167a59814ab98b77304834ebb45d2b41576ea6
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

	/*
		Constructor to test appearance of tiles on main parent page
	 */
	public Kid(String name, Double check, Double save){
		this.name = name;
		accounts.put("Checking", check);
		accounts.put("Savings", save);
	}

	public String getName(){
		return name;
	}

	public String getAccountsTotal(){
		DecimalFormat df = new DecimalFormat("#.##");
		Double sum = 0.0;
		for (Map.Entry<String, Double> curr : accounts.entrySet()) {
			sum += curr.getValue();
		}
		return df.format(sum);
	}

	public void setGoal(String gName, Double amount) {
		goal.put(gName, amount);
	}
}