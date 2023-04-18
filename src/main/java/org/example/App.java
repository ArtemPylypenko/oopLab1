package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.controllers.helloServlet;
import org.example.controllers.test;
import org.example.model.user.UserDao;

//http://localhost:8088/login
public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new helloServlet("static-content")), "/login/*");
        handler.addServlet(new ServletHolder(
                new test("static-content", GlobalSQLConnection.get(),
                        new UserDao(GlobalSQLConnection.get()))), "/test/*");

        server.setHandler(handler);
        server.start();
    }
}