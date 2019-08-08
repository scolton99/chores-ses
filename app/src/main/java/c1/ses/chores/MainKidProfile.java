package c1.ses.chores;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static c1.ses.chores.R.*;

public class MainKidProfile extends AppCompatActivity {

    private ImageButton childIcon;
    private TextView childName;
    private RecyclerView childTiles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.kid_profile);

        childIcon = findViewById(id.childIcon);
        childName = findViewById(id.parentWelcome);
        childTiles = findViewById(id.childTiles);

    }
}
