package controllers.listener;

import controllers.LoginServlet;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by ivans on 20/04/2017.
 */
public class MySessionListener implements HttpSessionListener {

    private static final Logger logger = Logger.getLogger(MySessionListener.class);


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug("Session_id = " + httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
