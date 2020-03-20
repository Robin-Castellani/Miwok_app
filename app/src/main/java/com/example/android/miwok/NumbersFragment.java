package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer pronunciationAudio;

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

     /**
     * Listener for the audio focus request
     */
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // another app has taken the audio focus; audio must stop
                releaseMediaPlayer();
            } else if (
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
            ) {
                // as each word counts, when audio focus is temporary loosen the audio pauses and
                // goes back to the beginning
                pronunciationAudio.pause();
                pronunciationAudio.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // audio focus is back and audio is resumed
                pronunciationAudio.start();
            }
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten));

        // create an ArrayAdapter, useful to handle data in a RecyclerView
        // it puts every element of words into an Android standard View, the
        //  simple_list_item_1
        // the ArrayAdapter is a concrete implementation of the ListAdapter interface
        WordAdapter adapter = new WordAdapter(
                getActivity(), words, R.color.category_numbers
        );

        // get the id of the ListView
        final ListView listView = (ListView) rootView.findViewById(R.id.word_list);

        // attach the ArrayAdapter to the ListView
        listView.setAdapter(adapter);

        // get the audio system service and request the audio focus (deprecated call)
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final Integer requestResult = audioManager.requestAudioFocus(
                audioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
        );

        // set the behaviour when a list item is clicked
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // get the Word at the current position and get the pronunciation audio ID
                        Word currentWord = (Word) listView.getItemAtPosition(position);
                        Integer audioID = currentWord.getPronunciationResourceID();

                        // verbose log the word played
                        Log.v("NumbersActivity", "Current word" + currentWord);

                        // if no audio, display a Toast and exit
                        if (audioID == null) {
                            Toast.makeText(
                                    getActivity(),
                                    "No pronunciation to play",
                                    Toast.LENGTH_SHORT
                            ).show();
                            return;
                        }

                        // check the result of the audio focus request and proceed if audio focus is gained
                        if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                            // the audio focus is gained and audio can be played

                            // before playing any audio, release other possibly playing MediaPlayers
                            releaseMediaPlayer();

                            // instantiate the MediaPlayer object and play the pronunciation
                            pronunciationAudio = MediaPlayer.create(
                                    getActivity(), audioID
                            );
                            pronunciationAudio.start();

                            // release resources when the audio has finished playing
                            pronunciationAudio.setOnCompletionListener(mCompletionListener);
                        }
                    }
                }
        );

        return rootView;
    }


    @Override
    public void onStop() {
        // Release audio resources when the app enters the Pause mode (app not visible anymore)
        releaseMediaPlayer();
        super.onStop();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (pronunciationAudio != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            pronunciationAudio.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            pronunciationAudio = null;

            // abandon the audio focus at the end of the pronunciation playing
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

}
