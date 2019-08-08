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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static c1.ses.chores.R.*;

public class MainParentActivity extends AppCompatActivity {

    private TextView parentWelcome;
    private TextView parentSubtitle;
    private RecyclerView childList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.parents_main);

        parentWelcome = findViewById(R.id.parentWelcome);
        parentSubtitle = findViewById(R.id.parentSubtitle);
        childList = findViewById(R.id.childList);

        Intent intent = getIntent();
        ArrayList<Kid> children = new ArrayList<>();
        children.add(new Kid("Rich Fairbank", 234.23, 34.36));
        children.add(new Kid("Dana Fairbank", 2.23, 344.36));

        ChildAdapter adapter = new ChildAdapter(children);

        childList.setLayoutManager(new LinearLayoutManager(this));
        childList.setAdapter(adapter);
    }
}
