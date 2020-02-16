package com.example.android.miwok;

/**
 * The {@link Word} contains the Miwok word and the translated word in
 * another language (set as default)
 */
public class Word {

    /* Private Miwok and Default translation words and image resource ID associated*/
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private Integer mImageResourceID;

    /**
     * Public constructor to set Miwok and Default translation words
     * @param miwokTranslation word in Miwok language
     * @param defaultTranslation word in user default language
     */
    public Word(String miwokTranslation, String defaultTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    /**
     * Public constructor to set Miwok and Default translation and image resource ID
     * @param miwokTranslation word in Miwok language
     * @param defaultTranslation word in user default language
     * @param imageResourceID Integer of the image resource ID associated with the translation
     */
    public Word(String miwokTranslation, String defaultTranslation, Integer imageResourceID) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceID = imageResourceID;
    }

    /**
     * Get the Miwok word
     * @return Miwok word as a string
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Get the word in user's default language
     * @return Default language word as a string
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the resource ID of the image associated with a translation
     * @return Integer with the target image resource ID
     */
    public Integer getImageResourceID() {
        return mImageResourceID;
    }
}
