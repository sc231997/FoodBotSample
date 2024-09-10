package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.csjbot.coshandler.core.Action;
import com.csjbot.coshandler.core.CsjRobot;
import com.csjbot.coshandler.listener.OnAuthenticationListener;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "";
    private static final String USER_KEY = "";

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

        CsjRobot.authentication(this, API_KEY, USER_KEY, new OnAuthenticationListener() {
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

//        CsjRobot.enableSlam(true);
        CsjRobot.setRobotType(CsjRobot.RobotType.TIMO);
//        CsjRobot.getInstance().init(this);
//        Action action = CsjRobot.getInstance().getAction();
//        action.moveForward();
    }

    public void handleButtonClick(View view) {
        Button button = (Button) view;
        int id = button.getId();
        if (id == R.id.forward) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.backward) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.left) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.right) {
            Toast.makeText(this, "Button clicked! " + button.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}