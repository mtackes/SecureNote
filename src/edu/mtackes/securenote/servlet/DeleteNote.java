package edu.mtackes.securenote.servlet;

import edu.mtackes.securenote.model.entity.CryptoNote;
import edu.mtackes.securenote.model.entity.Note;
import edu.mtackes.securenote.model.entity.PlaintextNote;
import edu.mtackes.securenote.persistence.NoteDAO;
import edu.mtackes.securenote.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by mtackes on 12/19/15.
 */
@WebServlet(name = "DeleteNote", urlPatterns = {"/delete/*"})
public class DeleteNote extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("notePassword");
        UUID pathUuid = RoutingUtils.getUuidInRequestPath(request);

        // If the password field was not in the request at all
        if (password == null) {
            // Include the pre-deletion page
            request.getRequestDispatcher("/WEB-INF/jsp/deleteNote.jsp").include(request, response);
            return;
        }

        if (password.isEmpty()) {
            RoutingUtils.dispatchIncludeError("Password cannot be left blank.", 400, request, response);
            return;
        }

        if (pathUuid == null) {
            RoutingUtils.dispatchIncludeError("Malformed or missing note ID in request.", 400, request, response);
            return;
        }

        NoteDAO noteDAO = new NoteDAO();
        CryptoNote cryptoNote = noteDAO.getNoteByUuid(pathUuid);

        PlaintextNote decryptedNote = null;

        if (cryptoNote == null || (decryptedNote = PlaintextNote.decryptNote(cryptoNote, password)) == null) {
            // No note found or bad password - don't delete.
            RoutingUtils.dispatchIncludeError("Deletion failed. Either the note doesn't exist or the password was incorrect.", 400, request, response);
            return;
        }

        // Failure conditions have been checked, go ahead and delete
        noteDAO.deleteNote(cryptoNote);

        request.setAttribute("noteUuid", pathUuid);
        request.getRequestDispatcher("/WEB-INF/jsp/deleteNoteSuccess.jsp").include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID pathUuid = RoutingUtils.getUuidInRequestPath(request);
        String getNoteLink;

        if (pathUuid != null) {
            getNoteLink = "<a href='/note/" + pathUuid.toString() + "'>get your note</a>";
        } else {
            getNoteLink = "<a href='/getNote'>get a note</a>";
        }

        RoutingUtils.dispatchIncludeError("Invalid request. You must " + getNoteLink + " before you can delete it.", 400, request, response);
    }
}
