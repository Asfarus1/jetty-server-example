import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import javax.naming.InitialContext;

/**
 * Created by asfarus on 30.03.2017.
 */
public class Main {


    private static final String RESOURCES_DIR = "./src/main/resources";

    public static void main(String[] args) throws Exception {

        DBService e = new DBService();

//        InitialContext ctx = new InitialContext();
//        ctx.bind("DBService",e);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(RESOURCES_DIR);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignInServlet(e)), "/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(e)), "/signup");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler, new ShutdownHandler("stopserver")});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
