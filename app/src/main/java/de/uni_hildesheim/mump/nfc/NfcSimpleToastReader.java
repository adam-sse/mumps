package de.uni_hildesheim.mump.nfc;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import de.uni_hildesheim.mump.IntentHandler;

public class NfcSimpleToastReader implements IntentHandler  {

    private final Context context;

    public NfcSimpleToastReader(Context context) { this.context = context; }

    @Override
    public boolean handleIntent(Intent intent) {

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if (tag != null) {
                byte[] tagId = tag.getId();
                StringBuilder sb = new StringBuilder();
                for (byte b : tagId) {
                    sb.append(String.format("%02X", b));
                }
                String tagIdHex = sb.toString();

                Log.d("NFC", "Tag ID: " + tagIdHex);
                Toast.makeText(context, "Tag ID: " + tagIdHex, Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
}
