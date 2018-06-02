package com.example.ji.lab6_1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText txtData;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = (EditText) findViewById(R.id.txtData);
        btn1 = (Button) findViewById(R.id.writeBtn);
        btn2 = (Button) findViewById(R.id.clearBtn);
        btn3 = (Button) findViewById(R.id.readBtn);
        btn4 = (Button) findViewById(R.id.finishBtn);

        btn1.setOnClickListener(new View.OnClickListener() { //write
            @Override
            public void onClick(View view) {
                try {
                    File dir = getExternalFilesDir( "myFile");
                    File sdCard = new File(dir, "mysdfile.txt");

                    dir.mkdirs();

                    if(!sdCard.exists()) { // doesn't exists, create file
                        sdCard.createNewFile();
                    }

                    FileOutputStream fOut = new FileOutputStream(sdCard, true);

                    fOut.write(txtData.getText().toString().getBytes()); //write data file
                    fOut.close();

                    Toast.makeText(MainActivity.this, "Done writing SD 'mysdfile.txt'", Toast.LENGTH_LONG).show(); //send message
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        btn2.setOnClickListener(new View.OnClickListener() { //clear
            @Override
            public void onClick(View view) {
                txtData.setText("");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() { //read
            public void onClick(View view) {
                try {
                    File sdCard = new File (getExternalFilesDir("myFile"), "mysdfile.txt");


                    FileInputStream fIn = new FileInputStream(sdCard);
                    InputStreamReader isr = new InputStreamReader(fIn);

                    byte[] data = new byte[fIn.available()];
                    while (fIn.read(data) != -1) { //read data
                    }
                    txtData.setText(new String(data));
                    fIn.close();

                    Toast.makeText(MainActivity.this, "Done reading SD 'mysdfile.txt'", Toast.LENGTH_LONG).show(); //send message

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() { //finish
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
