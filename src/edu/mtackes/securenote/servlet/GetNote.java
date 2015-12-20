package edu.mtackes.securenote.servlet;

import edu.mtackes.securenote.model.entity.*;
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
 * Created by mtackes on 12/15/15.
 */
@WebServlet(name = "GetNote", urlPatterns = {"/getNote", "/note", "/note/*"})
public class GetNote extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID pathUuid = RoutingUtils.getUuidInRequestPath(request);
        String postUuidString = request.getParameter("noteUUID");

        // Deal with UUID validation
        if (pathUuid == null) {
            // No UUID in path
            if (postUuidString != null && !postUuidString.isEmpty()) {
                // Try constructing UUID from post data
                UUID noteUuid;

                try {
                    noteUuid = UUID.fromString(postUuidString);
                    // Good UUID, redirect to url
                    response.sendRedirect("/note/" + noteUuid.toString());
                    return;
                } catch (Exception e) {
                    // Don't need to do anything here, really
                    noteUuid = null;
                }
            }
            // Wasn't a UUID in path, no valid UUID in post
            RoutingUtils.dispatchIncludeError("Invalid note ID in request.", 400, request, response);
            return;
        }

        // From here down, path with UUID should be guaranteed

        String postPassword = request.getParameter("notePassword");

        // If password is empty
        if (postPassword.isEmpty()) {
            RoutingUtils.dispatchIncludeError("Password cannot be empty.", 400, request, response);
            return;
        }

        NoteDAO noteDao = new NoteDAO();
        CryptoNote cryptoNote = noteDao.getNoteByUuid(pathUuid);

        PlaintextNote plaintextNote = cryptoNote == null ? null : PlaintextNote.decryptNote(cryptoNote, postPassword);

        if (cryptoNote == null || plaintextNote == null) {
            // No note or bad password
            RoutingUtils.dispatchIncludeError("Bad password or no note found with ID <code>" + pathUuid.toString() + "</code>!", 400, request, response);
            return;
      }

        request.setAttribute("note", plaintextNote);
        request.getRequestDispatcher("/WEB-INF/jsp/showNote.jsp").include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!RoutingUtils.requestHasPathParam(request)) {
            // Absolutely no url path param
            request.getRequestDispatcher("/WEB-INF/jsp/getNote.jsp").include(request, response);
            return;
        }

        UUID pathUuid = RoutingUtils.getUuidInRequestPath(request);

        if (pathUuid == null) {
            // No UUID could be made from path
            RoutingUtils.dispatchIncludeError("Invalid note ID in url.", 400, request, response);
            return;
        }

        // Path contained valid UUID
        request.setAttribute("uuid", pathUuid);
        request.getRequestDispatcher("/WEB-INF/jsp/getNoteWithUuid.jsp").include(request, response);
    }
}
