package de.uni_hildesheim.mump.nfc;

import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import de.uni_hildesheim.mump.IntentHandler;

public class NfcTextTagHandler  implements IntentHandler {

    private final Context context;

    public NfcTextTagHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean handleIntent(Intent intent) {
        if (!NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            return false;
        }

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
            return false;
        }

        Ndef ndef = Ndef.get(tag);
        if (ndef == null) {
            return false;
        }

        try {
            ndef.connect();

            String message = new String(ndef.getNdefMessage().getRecords()[0].getPayload());
            Toast.makeText(context, "Text auf Tag: " + message, Toast.LENGTH_SHORT).show();

            //NdefMessage message = createTextMessage("/course/1/event/2");
            //ndef.writeNdefMessage(message);
            //Toast.makeText(context, "Text auf Tag geschrieben.", Toast.LENGTH_SHORT).show();

            ndef.close();
            return true;
        } catch (Exception e) {
            Toast.makeText(context, "Fehler beim Schreiben: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private NdefMessage createTextMessage(String content) {
        byte[] textBytes = content.getBytes();
        byte[] langBytes = "en".getBytes();

        byte[] payload = new byte[1 + langBytes.length + textBytes.length];
        payload[0] = (byte) langBytes.length;
        System.arraycopy(langBytes, 0, payload, 1, langBytes.length);
        System.arraycopy(textBytes, 0, payload, 1 + langBytes.length, textBytes.length);

        NdefRecord record = new NdefRecord(
                NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT,
                new byte[0],
                payload
        );

        return new NdefMessage(new NdefRecord[]{ record });
    }
}
