package io.github.iexpense;

import android.app.Activity;
import android.os.Bundle;

public class ExpenseEditorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editor_main);
    }
}
