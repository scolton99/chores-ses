package c1.ses.chores.activities.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import c1.ses.chores.R;
import c1.ses.chores.activities.kid.MainKidActivity;
import c1.ses.chores.activities.parent.MainParentActivity;

public class ChooseModeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button parentButton;
    private Button childButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_mode);

        this.parentButton = findViewById(R.id.parentButton);
        this.childButton = findViewById(R.id.childButton);

        this.parentButton.setOnClickListener(this);
        this.childButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        if (view == this.parentButton)
            intent = new Intent(this, MainParentActivity.class);
        else if (view == this.childButton)
            intent = new Intent(this, MainKidActivity.class);

        if (intent == null)
            return;

        startActivity(intent);
    }
}
