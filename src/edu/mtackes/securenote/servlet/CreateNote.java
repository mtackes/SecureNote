package edu.mtackes.securenote.servlet;

import edu.mtackes.securenote.model.entity.*;
import edu.mtackes.securenote.persistence.NoteDAO;

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
@WebServlet(name = "CreateNote", urlPatterns = {"/newNote"})
public class CreateNote extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("request.getParameterMap() = " + request.getParameterMap());

        String noteBody = request.getParameter("noteBody");
        String notePassword = request.getParameter("notePassword");

        // If either is empty
        if (noteBody.isEmpty() || notePassword.isEmpty()) {
            // Return back to the new note screen.
            request.getRequestDispatcher("/WEB-INF/jsp/newNote.jsp").forward(request, response);
            return;
        }

        Note plainNote = new PlaintextNote(UUID.randomUUID(), noteBody.getBytes());
        CryptoNote cryptoNote = CryptoNote.encryptNote(plainNote, notePassword);

        NoteDAO noteDao = new NoteDAO();
        noteDao.createNote(cryptoNote);

        request.setAttribute("uuid", cryptoNote.getUuid());
        request.getRequestDispatcher("/WEB-INF/jsp/noteCreated.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/newNote.jsp").forward(request, response);
    }
}
