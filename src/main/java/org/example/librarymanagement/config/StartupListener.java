package org.example.librarymanagement.config;



import org.example.librarymanagement.dao.BorrowDAO;
import org.example.librarymanagement.dao.DocumentDAO;
import org.example.librarymanagement.dao.UserDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("documentDAO", new DocumentDAO());
        sce.getServletContext().setAttribute("userDAO", new UserDAO());
        sce.getServletContext().setAttribute("borrowDAO", new BorrowDAO());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Nettoyage si nécessaire
    }
}

