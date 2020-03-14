package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private Integer mColorResourceID;

    /**
     * Constructor of the WordAdapter; it uses one of the constructors of the super class
     * (ArrayAdapter) but passes 0 as second parameter as we want to choose which View to
     * use to display the Word objects
     * @param context activity's context
     * @param wordList ArrayList containing elements of Word type (Miwok and Default translation)
     * @param colorResourceID color resource ID to be set as background color
     */
    public WordAdapter(Activity context, ArrayList<Word> wordList, Integer colorResourceID) {
        super(context, 0, wordList);
        mColorResourceID = colorResourceID;
    }

    /**
     * Get the view to be displayed at a specific position into the parent View
     * @param position position of the list on the ArrayAdapter to display
     * @param convertView View which will be populated with the items of the ArrayAdapter at a position
     * @param parent parent View which will be populated by the ArrayAdapter with convertViews
     * @return the populated convertView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // get the View which will be multiplied to contain all items of the ArrayList
        // a single View will contain a single item of the ArrayAdapter
        View listItemView = convertView;
        // check whether there is a convertView among the recycled Views
        // if not, get it and inflate it into its parent View
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        // get the Miwok - Default translation pair position on the ArrayAdapter
        Word currentWord = this.getItem(position);

        // now populate the itemListView with its elements from the Word object
        // first the Miwok translation
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // then the Default translation
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // show with the image, only if it is present in the currentWord instance
        // otherwise put away the ImageView
        ImageView imgImageView = listItemView.findViewById(R.id.image_view);
        if (currentWord.getImageResourceID() == null) {
            imgImageView.setVisibility(View.GONE);
        } else {
            imgImageView.setImageResource(currentWord.getImageResourceID());
        }

        // hide the play button if no audio resource ID is present
        ImageView playButton = listItemView.findViewById(R.id.play_image_view);
        if (currentWord.getPronunciationResourceID() == null) {
            playButton.setVisibility(View.GONE);
        }

        listItemView.setBackgroundResource(mColorResourceID);

        // eventually return the list item View populated with translation from the ArrayList
        // at the current position
        return listItemView;
    }
}
