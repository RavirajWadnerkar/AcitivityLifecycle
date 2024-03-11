package com.example.actiivitylifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    TextView threadCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        threadCounterTextView = findViewById(R.id.threadCounterTextView);
        if (savedInstanceState == null) {
            CounterManager.increment();
        }

        findViewById(R.id.startActivityBButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityB.class);
            startActivity(intent);
        });

        findViewById(R.id.startActivityCButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityC.class);
            startActivity(intent);
        });

        findViewById(R.id.closeAppButton).setOnClickListener(v -> {
            CounterManager.reset();
            finish();
        });
        Button dialogButton = findViewById(R.id.dialogButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (threadCounterTextView != null) {
            if ("ActivityB".equals(CounterManager.lastActivityOpened)) {
                CounterManager.add(5);
            } else if ("ActivityC".equals(CounterManager.lastActivityOpened)) {
                CounterManager.add(10);
            }
            CounterManager.lastActivityOpened = "";
            threadCounterTextView.setText("Counter: " + CounterManager.getCounter());
        }
    }
    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        Button closeButton = dialogView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
