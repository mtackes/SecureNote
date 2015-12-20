package edu.mtackes.securenote.model.entity;

import com.sun.istack.Nullable;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by mtackes on 11/5/15.
 */
public abstract class Note {
    private int id;
    private UUID uuid;
    private byte[] contentBytes;
    private String title;
    private User owner;

    public Note() {}

    public Note(UUID uuid, byte[] contentBytes) {
        this.uuid = uuid;
        this.contentBytes = contentBytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public byte[] getContentBytes() {
        return contentBytes;
    }

    public void setContentBytes(byte[] contentBytes) {
        this.contentBytes = contentBytes;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    protected byte[] getUUIDBytes() {
        // A UUID is the size of two longs
        int uuidByteSize = Long.SIZE * 2 / Byte.SIZE;

        ByteBuffer buffer = ByteBuffer.allocate(uuidByteSize);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(Long.SIZE / Byte.SIZE, uuid.getLeastSignificantBits());

        return buffer.array();
    }

    @Override
    public String toString() {
        return "Note";
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
