package c1.ses.chores;
/**
 * Jennifer Carballo
 * 8.8.2019
 * Creates the page the parent user sees when first "logging into the app"
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static c1.ses.chores.R.*;

public class MainParentActivity extends AppCompatActivity implements ChildAdapter.KidClickedListener{

    private TextView parentWelcome;
    private TextView parentSubtitle;
    private RecyclerView childList;

    ArrayList<Kid> children = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.parents_main);

        parentWelcome = findViewById(R.id.parentWelcome);
        parentSubtitle = findViewById(R.id.parentSubtitle);
        childList = findViewById(R.id.childList);

        children.add(new Kid("Rich Fairbank", 234.23, 34.36));
        children.add(new Kid("Dana Fairbank", 2.23, 344.36));

        ChildAdapter adapter = new ChildAdapter(children, this);

        childList.setLayoutManager(new LinearLayoutManager(this));
        childList.setAdapter(adapter);

        parentWelcome = findViewById(id.parentWelcome);
        parentSubtitle = findViewById(id.parentSubtitle);
        childList = findViewById(id.childList);
    }

    /**
     * Called when the user clicks on any of the kid in the list. From here, you could
     * open up a new screen to further show kid details.
     */
    @Override
    public void onKidClicked(int pos) {
//        Toast.makeText(MainParentActivity.this, "Login Success!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainParentActivity.this, MainKidProfile.class);
//        intent.putExtra("name", children.get(pos).getName());
//        intent.putExtra("checking", children.get(pos).getChecking());
//        intent.putExtra("savings", children.get(pos).getSavings());
        startActivity(intent);
    }
}
