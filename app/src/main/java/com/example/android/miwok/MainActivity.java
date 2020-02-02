/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Get the view from its id, specified in the activity_main.xml file
        TextView numbers = findViewById(R.id.numbers);
        // Get the view from its id, specified in the activity_main.xml file
        TextView family = findViewById(R.id.family);
        // Get the view from its id, specified in the activity_main.xml file
        TextView colors = findViewById(R.id.colors);
        // Get the view from its id, specified in the activity_main.xml file
        TextView phrases = findViewById(R.id.phrases);

        // Set behaviour when a single tap event is intercepted
        // The behaviour is changing activity from the actual one to the TextView's one
        numbers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to link the MainActivity with the NumbersActivity
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                // Start the instantiated activity
                startActivity(numbersIntent);
            }
        });


        family.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(numbersIntent);
            }
        });


        colors.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(numbersIntent);
            }
        });


        phrases.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(numbersIntent);
            }
        });
    }
}
