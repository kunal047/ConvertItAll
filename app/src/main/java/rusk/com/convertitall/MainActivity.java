package rusk.com.convertitall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent mIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView octalToBinary = (TextView) findViewById(R.id.octal_to_binary);
        TextView hexToBinary = (TextView) findViewById(R.id.hex_to_binary);
        TextView decToBinary = (TextView) findViewById(R.id.dec_to_binary);
        TextView mixItUp = (TextView) findViewById(R.id.mix_it_up);

        octalToBinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MainActivity.this, OctalToBinary.class);
                startActivity(mIntent);
            }
        });

        hexToBinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MainActivity.this, HexToBinary.class);
                startActivity(mIntent);
            }
        });

        decToBinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MainActivity.this, DecToBinary.class);
                startActivity(mIntent);

            }
        });

        mixItUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MainActivity.this, MixItUp.class);
                startActivity(mIntent);

            }
        });
    }
}
