package io.github.iexpense;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class ExpenseEditorActivity extends Activity {

    String[] tagSuggestion = {
            "you paid &#8377 1000 to Aman", "you borrow $ 30 from Aman"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_main);
        EditText num = (EditText) findViewById(R.id.editor_textInput);
//        actv.setAdapter(new ArrayAdapter<String>(this,R.layout.editor_main,tagSuggestion));
//        Log.d("add", "runs");
//        final int val = Integer.parseInt(num.getText().toString());
        Button button = (Button) findViewById(R.id.addExpense);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                int expense = val;
//
//                // Perform action on click
//            }
//        });

    }
}
