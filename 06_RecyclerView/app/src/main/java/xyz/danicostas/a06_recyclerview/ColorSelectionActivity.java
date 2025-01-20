package xyz.danicostas.a06_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class ColorSelectionActivity extends AppCompatActivity {
    SeekBar seekBarRed;
    SeekBar seekBarGreen;
    SeekBar seekBarBlue;
    View colorPreview;
    Button btGuardar;
    public static final int OK = 1;
    public static final int KO = 0;
    int color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selection);

        seekBarRed = findViewById(R.id.seekBarRed);
        seekBarGreen = findViewById(R.id.seekBarGreen);
        seekBarBlue = findViewById(R.id.seekBarBlue);
        colorPreview = findViewById(R.id.colorPreview);
        btGuardar = findViewById(R.id.btGuardarColor);

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        btGuardar.setOnClickListener(view -> {
            Intent data = new Intent();

            data.putExtra("Color", String.valueOf(color));
            setResult(OK, data);
            finish();
        });
    }

    private void updateColor() {
        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();
        color = Color.rgb(red, green, blue);
        colorPreview.setBackgroundColor(color);
    }

}