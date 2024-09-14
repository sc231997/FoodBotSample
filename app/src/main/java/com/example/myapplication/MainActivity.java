package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.csjbot.coshandler.core.Action;
import com.csjbot.coshandler.core.CsjRobot;
import com.csjbot.coshandler.listener.OnAuthenticationListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "0d3579e3-6185-4f51-851e-a8c9bc13f2b2";
    private static final String USER_KEY = "80919167EBF6D9A2B470B3262E2A841D";
    TextView tvApiKey, tvUserKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvApiKey = findViewById(R.id.editTextApiKey);
        tvUserKey = findViewById(R.id.editTextUserKey);

        tvApiKey.setText(API_KEY);
        tvUserKey.setText(USER_KEY);
    }

    public void handleMovement(View view) {
        Button button = (Button) view;
        Action action = CsjRobot.getInstance().getAction();
        int id = button.getId();
        if (id == R.id.forward) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
            action.moveForward();
        } else if (id == R.id.backward) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
            action.moveBack();
        } else if (id == R.id.left) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
            action.moveLeft();
        } else if (id == R.id.right) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
            action.moveRight();
        }
    }

    public void handleSave(View view) {
        Button button = (Button) view;

        CsjRobot.authentication(this, tvApiKey.getText().toString(), tvUserKey.getText().toString(), new OnAuthenticationListener() {
            @Override
            public void success() {
                Log.d("TAG", "Authorization succeeded!");
            }

            @Override
            public void error() {
                Log.d("TAG", "privilege grant failed!");
            }
        });

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CsjRobot.setRobotType(CsjRobot.RobotType.ALICE);
        try {
            CsjRobot.getInstance().init(this);
        } catch (Exception e) {
            Log.e("Tag", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_LONG).show();
        }
    }
}