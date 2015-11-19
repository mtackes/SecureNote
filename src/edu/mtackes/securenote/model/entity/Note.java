package edu.mtackes.securenote.model.entity;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by mtackes on 11/5/15.
 */
public abstract class Note {
    private UUID id;
    private byte[] contentBytes;

    public Note() {}
    public Note(UUID id, byte[] contentBytes) {
        this.id = id;
        this.contentBytes = contentBytes;
    }

    public byte[] getContentBytes() {
        return contentBytes;
    }

    public void setContentBytes(byte[] contentBytes) {
        this.contentBytes = contentBytes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    protected byte[] getIdBytes() {
        // A UUID is the size of two longs
        int uuidByteSize = Long.SIZE * 2 / Byte.SIZE;

        ByteBuffer buffer = ByteBuffer.allocate(uuidByteSize);
        buffer.putLong(id.getMostSignificantBits());
        buffer.putLong(Long.SIZE / Byte.SIZE, id.getLeastSignificantBits());

        return buffer.array();
    }
}
