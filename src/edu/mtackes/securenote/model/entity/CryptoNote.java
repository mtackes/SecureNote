package edu.mtackes.securenote.model.entity;

import com.sun.istack.Nullable;
import edu.mtackes.securenote.util.Crypto;

import java.util.UUID;

/**
 * Created by mtackes on 11/5/15.
 */
public class CryptoNote extends Note {

    public CryptoNote() { super(); }
    public CryptoNote(UUID uuid, byte[] encryptedContent) {
        super(uuid, encryptedContent);
    }

    @Nullable
    public static CryptoNote encryptNote(Note note, String password) {
        byte[] encryptedContent = Crypto.encrypt(note.getContentBytes(), note.getUUIDBytes(), password);

        if (encryptedContent == null) {
            return null;
        }

        return new CryptoNote(note.getUuid(), encryptedContent);
    }

    @Override
    public String toString() {
        return "## Encrypted Note " + getUuid().toString() + " ##";
    }
}
