package org.vaadin.embedded;

import com.thetransactioncompany.cors.CORSFilter;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.eclipse.jetty.webapp.WebAppContext;
import org.vaadin.jetty.VaadinJettyServer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author Alejandro Duarte.
 */
@Theme(ValoTheme.THEME_NAME)
public class ExternalUI1 extends UI {

    public static void main(String[] args) throws Exception {
        VaadinJettyServer server = new VaadinJettyServer(9001, ExternalUI1.class);
        WebAppContext handler = (WebAppContext) server.getHandler();
        handler.getSessionHandler().getSessionManager().getSessionCookieConfig().setName(ExternalUI1.class.getSimpleName());
        handler.addFilter(CORSFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        server.start();
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Button("External UI 1", e -> e.getButton().setCaption("It works!")));
    }

}
