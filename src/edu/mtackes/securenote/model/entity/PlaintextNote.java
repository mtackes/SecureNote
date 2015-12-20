package edu.mtackes.securenote.model.entity;

import com.sun.istack.Nullable;
import edu.mtackes.securenote.util.Crypto;

import java.util.UUID;

/**
 * Created by mtackes on 11/5/15.
 */
public class PlaintextNote extends Note {

    public PlaintextNote() { super(); }
    public PlaintextNote(UUID uuid, byte[] content) {
        super(uuid, content);
    }

    @Nullable
    public static PlaintextNote decryptNote(Note note, String password) {
        byte[] decryptedContent = Crypto.decrypt(note.getContentBytes(), note.getUUIDBytes(), password);

        if (decryptedContent== null) {
            return null;
        }

        return new PlaintextNote(note.getUuid(), decryptedContent);
    }

    public String getContentString() {
        return new String(getContentBytes());
    }

    public void setContentString(String contentString) {
        setContentBytes(contentString.getBytes());
    }

    @Override
    public String toString() {
        return getContentString();
    }
}
