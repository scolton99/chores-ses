package c1.ses.chores;
/**
 * Jennifer Carballo
 * 8.8.2019
 * Creates the page the parent user sees when first "logging into the app"
 */
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static c1.ses.chores.R.*;

public class MainParentActivity extends AppCompatActivity implements FirebaseDataListener<List<Kid>> {

    private TextView parentWelcome;
    private TextView parentSubtitle;
    private RecyclerView childList;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.parents_main);

        parentWelcome = findViewById(R.id.parentWelcome);
        parentSubtitle = findViewById(R.id.parentSubtitle);
        childList = findViewById(R.id.childList);

        Intent intent = getIntent();
        ArrayList<Kid> children = new ArrayList<>();

        childList.setLayoutManager(new LinearLayoutManager(this));

        this.mAuth = FirebaseAuth.getInstance();

        List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), 123);
    }

    @Override
    public void onStart() {
        super.onStart();

        updateKids();
    }

    public void updateKids() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (currentUser == null)
            return;

        Kid.getKidsByParent(db, currentUser.getUid(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK) {
            updateKids();
        }
    }

    @Override
    public void onData(List<Kid> kids) {
        childList.setAdapter(new ChildAdapter(kids));
    }
}
