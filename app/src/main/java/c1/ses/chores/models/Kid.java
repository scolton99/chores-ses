package c1.ses.chores.models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import c1.ses.chores.util.FirebaseDataListener;

/**
 * Represents a single child under a parent's account.
 *
 * @author Megan St. Hilaire
 * @author Spencer Colton
 */
public class Kid implements Serializable {
	private String name;
	private String parent_id;
	private Map<String, Object> goal = new HashMap<>();
	private Map<String, Double> accounts = new HashMap<>();

	/**
	 * Blank constructor is required for Firebase to utilize this class directly.
	 */
	@SuppressWarnings("unused")
	public Kid() {}

	/**
	 * Constructor so that all properties have usage.
	 *
	 * @param name The Kid's name
	 * @param parent_id The Kid's parent's user ID
	 * @param goal The Kid's goal
	 * @param accounts Information about the Kid's accounts
	 */
	@SuppressWarnings("unused")
	private Kid(String name,
				String parent_id,
				Map<String, Object> goal,
				Map<String, Double> accounts) {
		this.name = name;
		this.parent_id = parent_id;
		this.goal = goal;
		this.accounts = accounts;
	}

	/**
	 * This constructor is used when a parent creates a new child.
	 * @param name
	 * @param parent_id
	 * @param prevBal
	 */
	public Kid(String name,
				String parent_id,
				Double prevBal) {
		this.name = name;
		this.parent_id = parent_id;
		this.accounts.put("checking", prevBal);
		this.accounts.put("savings", 0.0);
	}


	/**
	 * Getter for the name variable. Required by Firebase.
	 *
	 * @return This Kid's name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Getter for the accounts variable. Required by Firebase.
	 *
	 * @return The accounts for this Kid
	 */
	@SuppressWarnings("unused")
	public Map<String, Double> getAccounts() {
		return this.accounts;
	}

	/**
	 * Getter for the goal variable. Required by Firebase.
	 *
	 * @return The goal for this Kid
	 */
	@SuppressWarnings("unused")
	public Map<String, ?> getGoal() {
		return this.goal;
	}

	/**
	 * Getter for the parent_id variable. Required by Firebase.
	 *
	 * @return The ID of this Kid's parent.
	 */
	@SuppressWarnings("unused")
	public String getParent_id() {
		return this.parent_id;
	}

	/**
	 * Gets all of the Kids for one parent by the parent's ID. Returns nothing because the request
	 * is asynchronous.
	 *
	 * @param db A connection to Firestore
	 * @param parent_id The ID of the parent
	 * @param listener The object to call back when the data arrives
	 */
	public static void getKidsByParent(FirebaseFirestore db, String parent_id, final FirebaseDataListener<List<Kid>> listener) {
		CollectionReference cr = db.collection("kids");
		Query query = cr.whereEqualTo("parent_id", parent_id);

		query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
			@Override
			public void onComplete(@NonNull Task<QuerySnapshot> task) {
				List<Kid> kids = new ArrayList<>();

				if (task.isSuccessful() && task.getResult() != null) {
					for (QueryDocumentSnapshot qdr : task.getResult()) {
						kids.add(qdr.toObject(Kid.class));
					}
				}

				listener.onData(kids);
			}
		});
	}

	/**
	 * @return The total value of all the Kid's accounts.
	 */
	public String getAccountsTotal(){
		DecimalFormat df = new DecimalFormat("#.##");
		Double sum = 0.0;
		for (Map.Entry<String, Double> curr : accounts.entrySet()) {
			sum += curr.getValue();
		}
		return df.format(sum);
	}

	/**
	 * @return The value of the Kid's checking account
	 */
	@SuppressWarnings("unused")
	Double getChecking(){
		return accounts.get("Checking");
	}

	/**
	 * @return The value of the Kid's savings account
	 */
	public Double getSavings(){
		return accounts.get("Savings");
	}

	@Override
	public String toString() {
		String nameAndParent = this.name + " of " + this.parent_id;
		String checkingValue = "Checking: " + this.accounts.get("checking");
		String savingsValue = "Savings: " + this.accounts.get("savings");
		String goalString = "Goal: " + this.goal.get("name");

		return nameAndParent + " (" + checkingValue + ", " + savingsValue + ", " + goalString + ")";
	}
}