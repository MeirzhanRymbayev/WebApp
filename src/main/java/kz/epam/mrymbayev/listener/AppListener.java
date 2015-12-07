package kz.epam.mrymbayev.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import org.flywaydb.core.Flyway;

@WebListener()
public class AppListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

        public ConnectionPool connectionPool;

    public AppListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        // TODO check why invisible FlyWay field
        //FlyWay flyWay = new FlyWay();
        connectionPool = ConnectionPool.getInstance();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        connectionPool = null;

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
