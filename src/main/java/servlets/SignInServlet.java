package servlets;

import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asfarus on 30.03.2017.
 */
public class SignInServlet extends HttpServlet {

    DBService e;

    public SignInServlet(DBService e) {
        this.e = e;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");


        if (login.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().printf("Authorized: %s",login);
        }else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().printf("Authorized: %s", login);
        }
    }
}
