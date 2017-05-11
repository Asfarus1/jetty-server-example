package servlets;

import dbService.DBException;
import dbService.DBService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asfarus on 30.03.2017.
 */
public class SignUpServlet extends HttpServlet {
    DBService e;
    public SignUpServlet(DBService e) {
        this.e = e;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        try {
            e.addUser(login);
            resp.getWriter().printf("Registered: %s", login);
        } catch (DBException e1) {
            e1.printStackTrace(resp.getWriter());
        }
    }
}
