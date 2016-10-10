package io.github.iexpense;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ExpenseEditorActivity extends Activity {

    String[] tagSuggestion = {
            "you paid &#8377 1000 to Aman", "you borrow $ 30 from Aman"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editor_main);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        actv.setAdapter(new ArrayAdapter<String>(this,R.layout.editor_main,tagSuggestion));

    }
}
