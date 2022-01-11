package com.example.a.b.activityresultcontractsample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.a.b.activityresultcontractsample.NextActivity.EXTRA_KEY_RESULT_TEXT;
import static com.example.a.b.activityresultcontractsample.NextActivity.EXTRA_KEY_TEXT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "MainActivity";
    private TextView txtResult;
    private TextView txtResult2;

    private final ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                // 遷移先画面から戻ってきたときに呼ばれるコールバックメソッド.
                @Override
                public void onActivityResult(ActivityResult result) {

                    final int resultCode = result.getResultCode();
                    final Intent data = result.getData();
                    Log.d(LOG_TAG, " resultCode: " + resultCode + " data: " + data);

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // 処理成功時
                        if (data == null) return;
                        String resultText = data.getStringExtra(EXTRA_KEY_RESULT_TEXT);
                        txtResult.setText(resultText);
                    }

                }
            });

    private final ActivityResultLauncher<Intent> mStartForResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                final int resultCode = result.getResultCode();
                final Intent data = result.getData();
                Log.d(LOG_TAG, " resultCode: " + resultCode + " data: " + data);

                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 処理成功時
                    if (data == null) return;
                    String resultText = data.getStringExtra(EXTRA_KEY_RESULT_TEXT);
                    txtResult2.setText(resultText);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        txtResult2 = findViewById(R.id.txtResult2);

        final Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> {
            final Intent intent = new Intent(MainActivity.this, NextActivity.class);
            intent.putExtra(EXTRA_KEY_TEXT, txtResult.getText().toString());
            mStartForResult.launch(intent);
        });

        final Button btnNext2 = findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(v -> {
            final Intent intent = new Intent(MainActivity.this, NextActivity.class);
            intent.putExtra(EXTRA_KEY_TEXT, txtResult2.getText().toString());
            mStartForResult2.launch(intent);
        });
    }
}