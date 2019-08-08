/* Megan St. Hilaire
   Capital One SES Hackathon --> DUCKLING$
   This file represents a single child under a parent account.
*/
package c1.ses.chores;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kid {

	// Child name
	private String name;
	// ID of parent to link
	private String parent_id;
	// A goal (item, experience, etc.) that child is working for
	private Map<String, Object> goal = new HashMap<>();
	// Checking/savings account balances
	private Map<String, Double> accounts = new HashMap<>();

	// Required by Firebase
	public Kid() {}

	public Kid(String name, String parent, Double startCheckBal) {
		this.name = name;
		this.parent_id = parent;

		// Starting account balances
		// Checking is money given by the parents. Think of as direct deposit target.
		accounts.put("checking", startCheckBal);
		// Savings is money put towards a goal.
		accounts.put("savings", 0.0);
	}

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

	/*
		Constructor to test appearance of tiles on main parent page
	 */
	public Kid(String name, Double check, Double save){
		this.name = name;
		accounts.put("checking", check);
		accounts.put("savings", save);
	}

	// Required by Firebase
	private Kid(String name, String parent_id, Map<String, Object> goal, Map<String, Double> accounts) {
		this.name = name;
		this.parent_id = parent_id;
		this.goal = goal;
		this.accounts = accounts;
	}

	public String getName(){
		return name;
	}

	public Map<String, Double> getAccounts() {
		return this.accounts;
	}

	public Map<String, ?> getGoal() {
		return this.goal;
	}

	public String getParent_id() {
		return this.parent_id;
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

	@Override
	public String toString() {
		return this.name + " of " + this.parent_id + " (Checking: " + this.accounts.get("checking") +  ", Savings: " + this.accounts.get("savings") + ", Goal: " + this.goal + ")";
	}
}