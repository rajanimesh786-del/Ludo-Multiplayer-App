package com.example.ludomultiplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImage;
    private Button rollButton, controlButton;
    private NumberPicker dicePicker;
    private boolean controlMode = false;

    private int[] diceFaces = {
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceImage = findViewById(R.id.diceImage);
        rollButton = findViewById(R.id.rollButton);
        controlButton = findViewById(R.id.controlButton);
        dicePicker = findViewById(R.id.dicePicker);

        // Number picker setup (1-6)
        dicePicker.setMinValue(1);
        dicePicker.setMaxValue(6);

        rollButton.setOnClickListener(v -> rollDice());
        controlButton.setOnClickListener(v -> toggleControlMode());
    }

    private void rollDice() {
        int result;
        if (controlMode) {
            result = dicePicker.getValue();
            Toast.makeText(this, "Controlled roll: " + result, Toast.LENGTH_SHORT).show();
        } else {
            result = new Random().nextInt(6) + 1;
            Toast.makeText(this, "Random roll: " + result, Toast.LENGTH_SHORT).show();
        }
        diceImage.setImageResource(diceFaces[result - 1]);
    }

    private void toggleControlMode() {
        controlMode = !controlMode;
        if (controlMode) {
            controlButton.setText("Control ON");
            dicePicker.setVisibility(View.VISIBLE);
        } else {
            controlButton.setText("Control OFF");
            dicePicker.setVisibility(View.GONE);
        }
    }
}
