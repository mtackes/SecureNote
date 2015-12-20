package edu.mtackes.securenote.persistence;

import com.sun.istack.Nullable;
import edu.mtackes.securenote.model.entity.CryptoNote;
import java.util.*;

import edu.mtackes.securenote.model.entity.User;
import org.hibernate.*;

/**
 * Created by mtackes on 12/15/15.
 */
public class NoteDAO {
    public Integer createNote(CryptoNote note) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer noteId = null;

        try {
            tx = session.beginTransaction();
            noteId = (Integer)session.save(note);
            tx.commit();
        } catch (HibernateException hex) {
            if (tx != null) {
                tx.rollback();
            }
            hex.printStackTrace();
        } finally {
            session.close();
        }

        return noteId;
    }

    @Nullable
    public CryptoNote getNoteByUuid(UUID uuid) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        CryptoNote result = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from edu.mtackes.securenote.model.entity.CryptoNote Note where Note.uuid = :uuid");
            query.setParameter("uuid", uuid);
            List<CryptoNote> retrievedNotes = query.list();
            result = retrievedNotes.isEmpty() ? null : retrievedNotes.get(0);
        } catch (HibernateException hex) {
            hex.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<CryptoNote> getAllNotes() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<CryptoNote> notes = new ArrayList<CryptoNote>();

        try {
            tx = session.beginTransaction();
            notes = session.createQuery("from notes").list();
        } catch (HibernateException hex) {
            hex.printStackTrace();
        } finally {
            session.close();
        }

        return notes;
    }

    public List<CryptoNote> getNotesForUserId(int userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<CryptoNote> notes = new ArrayList<CryptoNote>();

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from notes where user_id = :userId");
            query.setInteger("userId", userId);
            notes = query.list();
        } catch (HibernateException hex) {
            hex.printStackTrace();
        } finally {
            session.close();
        }

        return notes;
    }

    public void updateNote(CryptoNote note) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(note);
            tx.commit();
        } catch (HibernateException hex) {
            hex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteNote(int noteId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // TODO: Does this work? From the docs, it looks like it should ...
            // ... or a transient instance with an identifier associated with existing persistent state.
            CryptoNote note = new CryptoNote();
            note.setId(noteId);

            session.delete(note);
            tx.commit();
        } catch (HibernateException hex) {
            if (tx != null) {
                tx.rollback();
            }
            hex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteNote(CryptoNote note) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(note);
            tx.commit();
        } catch (HibernateException hex) {
            if (tx != null) {
                tx.rollback();
            }
            hex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
