package edu.mtackes.securenote.servlet;

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
@WebServlet(name = "EditNote", urlPatterns = {"/edit/*"})
public class EditNote extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoutingUtils.dispatchIncludeError("Note a real error! This is the placeholder edit page!", 200, request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID pathUuid = RoutingUtils.getUuidInRequestPath(request);
        String getNoteLink;

        if (pathUuid != null) {
            getNoteLink = "<a href='/note/" + pathUuid.toString() + "'>get your note</a>";
        } else {
            getNoteLink = "<a href='/getNote'>get a note</a>";
        }

        RoutingUtils.dispatchIncludeError("Invalid request. You must " + getNoteLink + " before you can edit it.", 400, request, response);
    }
}
