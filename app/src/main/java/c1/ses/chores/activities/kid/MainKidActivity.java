package c1.ses.chores.activities.kid;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.List;

import c1.ses.chores.R;
import c1.ses.chores.models.Kid;
import c1.ses.chores.models.Task;
import c1.ses.chores.util.ChildTaskAdapter;
import c1.ses.chores.util.FirebaseDataListener;

public class MainKidActivity extends AppCompatActivity implements FirebaseDataListener<Kid> {
    protected static final String KID_ID = "Az3cVbBbSDT52W4OGvZp";

    private Kid kid;

    private TextView checkingValue;
    private TextView savingsValue;

    private ProgressBar checkingProgress;
    private ProgressBar savingsProgress;

    private RecyclerView kidTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kid_main);

        this.checkingValue = findViewById(R.id.checkingValue);
        this.savingsValue = findViewById(R.id.savingsValue);

        this.checkingProgress = findViewById(R.id.checkingProgress);
        this.savingsProgress = findViewById(R.id.savingsProgress);

        this.kidTasks = findViewById(R.id.kidTasks);
        this.kidTasks.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Kid.getKidByID(db, KID_ID, this);
        Task.getTasksByKid(db, KID_ID, new FirebaseDataListener<List<Task>>() {
            @Override
            public void onData(List<Task> tasks) {
                MainKidActivity.this.kidTasks.setAdapter(new ChildTaskAdapter(tasks));
            }
        });
    }

    private void updateUI() {
        if (this.kid == null)
            return;

        DecimalFormat df = new DecimalFormat("$###,###.##");
        String checking = df.format(kid.getAccounts().get("checking"));
        String savings = df.format(kid.getAccounts().get("savings"));

        this.checkingValue.setText(checking);
        this.savingsValue.setText(savings);

        this.checkingValue.setVisibility(View.VISIBLE);
        this.savingsValue.setVisibility(View.VISIBLE);

        this.checkingProgress.setVisibility(View.GONE);
        this.savingsProgress.setVisibility(View.GONE);
    }

    @Override
    public void onData(Kid kid) {
        this.kid = kid;
        this.updateUI();
    }
}
