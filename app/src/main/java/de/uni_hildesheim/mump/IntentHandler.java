package de.uni_hildesheim.mump;

import android.content.Intent;

public interface IntentHandler {
    /**
     * @param intent Das empfangene NFC-Intent
     * @return true, wenn der Handler das Intent verarbeitet hat
     */
    boolean handleIntent(Intent intent);
}