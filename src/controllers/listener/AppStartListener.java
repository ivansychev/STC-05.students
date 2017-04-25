package controllers.listener;

import org.apache.log4j.PropertyConfigurator;
import services.EmailSender;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by ivans on 20/04/2017.
 */
public class AppStartListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader().getResource("log4j.properties"));
        EmailSender sender = new EmailSender("rag-nar@mail.ru","awdx19916");
        sender.sendMail("rag-nar@mail.ru","magnusc@yandex.ru","SUP SCRUB?");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
