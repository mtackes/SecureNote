package edu.mtackes.securenote.util;

import com.sun.istack.Nullable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by mtackes on 12/19/15.
 */
public class RoutingUtils {
    public static boolean requestHasPathParam(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.isEmpty() || pathInfo.substring(1).isEmpty()) {
            return false;
        }

        return true;
    }

    @Nullable
    public static UUID getUuidInRequestPath(HttpServletRequest request) {
//        System.out.println("Path results:");
//        System.out.println(request.getPathInfo());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getServletPath());
//        System.out.println(request.getContextPath());
//        System.out.println(request.getPathTranslated());
//        System.out.println(request.getQueryString());
//        System.out.println(request.getRequestURL().toString());
//        System.out.println(request.getLocalName());
//        System.out.println(request.getServerName());
//        System.out.println();
        String urlParam = request.getPathInfo();
        if (urlParam == null || urlParam.isEmpty()) {
            return null;
        }

        // Remove the leading slash (guaranteed there by spec)
        urlParam = urlParam.substring(1);

        if (urlParam.endsWith("/")) {
            urlParam = urlParam.substring(0, urlParam.length() - 1);
        }

        UUID urlUuid;
        try {
            urlUuid = UUID.fromString(urlParam);
        } catch (Exception e) {
            urlUuid = null;
        }


        return urlUuid;
    }

    public static void dispatchIncludeError(String message, int statusCode, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        response.setStatus(statusCode);
        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").include(request, response);
    }
}
