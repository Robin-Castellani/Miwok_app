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

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("weṭeṭṭi", "red", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("chokokki", "green", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("ṭakaakki", "brown", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("ṭopoppi", "gray", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("kululli", "black", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("kelelli", "white", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("ṭopiisә", "dusty yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("chiwiiṭә", "mustard yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // create an ArrayAdapter, useful to handle data in a RecyclerView
        // it puts every element of words into an Android standard View, the
        //  simple_list_item_1
        // the ArrayAdapter is a concrete implementation of the ListAdapter interface
        WordAdapter adapter = new WordAdapter(
                this, words, R.color.category_colors
        );

        // get the id of the ListView
        final ListView listView = (ListView) findViewById(R.id.word_list);

        // attach the ArrayAdapter to the ListView
        listView.setAdapter(adapter);

        // set the behaviour when a list item is clicked
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // get the Word at the current position and get the pronunciation audio ID
                        Word currentWord = (Word) listView.getItemAtPosition(position);
                        Integer audioID = currentWord.getPronunciationResourceID();

                        // if no audio, display a Toast and exit
                        if (audioID == null) {
                            Toast.makeText(
                                    ColorsActivity.this,
                                    "No pronunciation to play",
                                    Toast.LENGTH_SHORT
                            ).show();
                            return;
                        }

                        // instantiate the MediaPlayer object and play the pronunciation
                        MediaPlayer pronunciationAudio = MediaPlayer.create(
                                getApplicationContext(), audioID
                        );
                        pronunciationAudio.start();
                    }
                }
        );
    }
}
