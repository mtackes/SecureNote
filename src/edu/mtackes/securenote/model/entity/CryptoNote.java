package edu.mtackes.securenote.model.entity;

import com.sun.istack.Nullable;
import edu.mtackes.securenote.util.Crypto;

import java.util.UUID;

/**
 * Created by mtackes on 11/5/15.
 */
public class CryptoNote extends Note {

    public CryptoNote(UUID id, byte[] encryptedContent) {
        super(id, encryptedContent);
    }

    @Nullable
    public static CryptoNote encryptNote(Note note, String password) {
        byte[] encryptedContent = Crypto.encrypt(note.getContentBytes(), note.getIdBytes(), password);

        if (encryptedContent == null) {
            return null;
        }

        return new CryptoNote(note.getId(), encryptedContent);
    }
}
