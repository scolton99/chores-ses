package c1.ses.chores;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static c1.ses.chores.R.*;

public class MainParentActivity extends AppCompatActivity {

    private TextView parentWelcome;
    private TextView parentSubtitle;
    private RecyclerView childList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.parents_main);

        parentWelcome = findViewById(id.parentWelcome);
        parentSubtitle = findViewById(id.parentSubtitle);
        childList = findViewById(id.childList);

    }
}
