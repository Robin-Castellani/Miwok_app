package com.example.android.miwok;

/**
 * The {@link Word} contains the Miwok word and the translated word in
 * another language 8set as default)
 */
public class Word {

    /* Private Miwok and Default translation words*/
    private String mMiwokTranslation;
    private String mDefaultTranslation;

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
}
