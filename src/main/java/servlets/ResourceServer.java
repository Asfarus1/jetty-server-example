package servlets;

import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Matveev.AV1 on 16.05.2017.
 */
public class ResourceServer extends HttpServlet{
    private TestResource tr;

    public ResourceServer(TestResource tr) {
        this.tr = tr;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathToResource = req.getParameter("path");
        if (pathToResource != null){
            try {
                TestResource t = (TestResource) ReadXMLFileSAX.readXML(pathToResource);
                tr.setAge(t.getAge());
                tr.setName(t.getName());
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            }catch (Exception ex){
                resp.getWriter().write(ex.getMessage());
            }
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
