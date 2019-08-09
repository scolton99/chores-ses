package c1.ses.chores.activities.parent;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import c1.ses.chores.R;
import c1.ses.chores.models.Kid;

public class NewKidActivity extends AppCompatActivity {

    private String nkName;
    private Double nkPrevBal;
    private String parentID;

    private EditText nameTextBox;
    private EditText prevBalText;
    private Button okAdd;
    private Button cancelAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_kid);

        Intent intent = getIntent();
        parentID = intent.getStringExtra("parentID");

        nameTextBox = findViewById(R.id.nameTextBox);
        prevBalText = findViewById(R.id.prevBalText);
        okAdd = findViewById(R.id.okAdd);
        okAdd.setEnabled(false);
        cancelAdd = findViewById(R.id.cancelAdd);

        nameTextBox.addTextChangedListener(textWatcher);
        prevBalText.addTextChangedListener(textWatcher);

        okAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputtedName= nameTextBox.getText().toString().trim();
                Double inputtedBalNum = Double.parseDouble(prevBalText.getText().toString().trim());

                Kid newKid = new Kid(inputtedName, parentID, inputtedBalNum);

                // ADD CHILD TO FIREBASE LIST

                // Pass back to MainParentActivity
                Intent backwardsIntent = new Intent(NewKidActivity.this, MainParentActivity.class);
                backwardsIntent.putExtra("newKid", newKid);
                startActivity(backwardsIntent);

            }
        });

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String inputtedName = nameTextBox.getText().toString().trim();
            String inputtedBal = prevBalText.getText().toString().trim();
            boolean enable = !inputtedName.isEmpty() && !inputtedBal.isEmpty();
            okAdd.setEnabled(enable);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

}
