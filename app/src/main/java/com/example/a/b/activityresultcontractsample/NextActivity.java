package com.example.a.b.activityresultcontractsample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_TEXT = "EXTRA_KEY_TEXT";
    public static final String EXTRA_KEY_RESULT_TEXT = "messageFromNext";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        final String text = getIntent().getStringExtra(EXTRA_KEY_TEXT);
        final EditText editText = findViewById(R.id.editText);
        editText.setText(text);

        final Button finishButton = findViewById(R.id.button_finish);
        finishButton.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.putExtra(EXTRA_KEY_RESULT_TEXT, editText.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}

