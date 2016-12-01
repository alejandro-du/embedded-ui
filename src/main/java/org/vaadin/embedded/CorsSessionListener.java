package org.vaadin.embedded;

import com.vaadin.server.*;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * An {@link HttpSessionListener} that adds a Vaadin {@link CorsBootstrapListener} to activate cross-site
 * Access-Control requests (CORS).
 *
 * @author Alejandro Duarte.
 */
@WebListener
public class CorsSessionListener implements HttpSessionListener {

    private boolean initialized = false;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        if (!initialized) {
            VaadinServletService.getCurrent().addSessionInitListener(new SessionInitListener() {
                @Override
                public void sessionInit(SessionInitEvent event) throws ServiceException {
                    VaadinSession.getCurrent().addBootstrapListener(new CorsBootstrapListener());

                }
            });
            initialized = true;
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }

}
