package c1.ses.chores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static c1.ses.chores.R.*;

/**
 * Jennifer Carballo
 * 8.8.2019
 * Creates the page the parent user sees when first "logging into the app"
 *
 * @author Jennifer Carballo
 * @author Spencer Colton
 */
public class MainParentActivity extends AppCompatActivity
        implements FirebaseDataListener<List<Kid>>, ChildAdapter.KidClickedListener {
    private TextView parentWelcome;
    private RecyclerView childList;
    private FirebaseAuth mAuth;
    private List<Kid> children;

    private static final int LOGIN_RC = 237;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.parents_main);

        parentWelcome = findViewById(R.id.parentWelcome);
        childList = findViewById(R.id.childList);

        childList.setLayoutManager(new LinearLayoutManager(this));

        this.mAuth = FirebaseAuth.getInstance();

        List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), LOGIN_RC);
    }

    @Override
    public void onStart() {
        super.onStart();

        updateUI();
    }

    /**
     * Updates the text and list on screen once Firebase data loads.
     */
    private void updateUI() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (currentUser == null)
            return;

        Kid.getKidsByParent(db, currentUser.getUid(), this);

        String name = currentUser.getDisplayName();
        String title = getApplicationContext().getString(string.parent_title, name);
        parentWelcome.setText(title);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that this was the Login activity returning and that it was not cancelled
        if (requestCode == LOGIN_RC && resultCode == RESULT_OK) {
            updateUI();
        }
    }

    @Override
    public void onData(List<Kid> kids) {
        this.children = kids;
        childList.setAdapter(new ChildAdapter(kids, this));
    }

    /**
     * Called when the user clicks on any of the kid in the list. From here, you could
     * open up a new screen to further show kid details.
     */
    @Override
    public void onKidClicked(int pos) {
        // Toast.makeText(MainParentActivity.this, "Login Success!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainParentActivity.this, MainKidProfile.class);

        // intent.putExtra("name", children.get(pos).getName());
        // intent.putExtra("checking", children.get(pos).getChecking());
        intent.putExtra("savings", children.get(pos).getSavings());

        startActivity(intent);
    }
}
