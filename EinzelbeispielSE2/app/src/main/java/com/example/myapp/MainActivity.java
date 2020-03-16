package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                EditText txt;
                txt = findViewById(R.id.matrikelnummer);

                TextView textfeld;
                textfeld = findViewById(R.id.textfeld);

                //parse into String
                String text = txt.getText().toString();

                //implementing client
                JavaClient client = new JavaClient(text);
                client.start();

                try{
                    client.join();
                } catch (InterruptedException e ){
                    e.printStackTrace();
                }

                textfeld.setText(client.modifiedSentence);

                //parse String into int array
                int[] arr = new int[text.length()];
                for(int i = 0; i < arr.length; i++){
                    arr[i] = text.charAt(i) - '0';
                }

                //getting the index of two numbers which have gcd
                for(int i = 0; i < arr.length; i++){
                    for(int j = 1; j < arr.length; j++){
                        try{
                            if(arr[i] % arr[j] == 0 && arr[i] / arr[j] > 1){
                                textfeld.setText("Die Indices der Ziffern mit gemeinsamen Teiler lauten: " + i + "&" + j);
                            }
                        } catch (ArithmeticException e){
                            //System.out.println("Cannot divide by zero!");
                        }
                    }
                }

            }

        });
    }
}
