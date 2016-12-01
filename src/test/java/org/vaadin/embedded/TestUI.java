package org.vaadin.embedded;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.eclipse.jetty.webapp.WebAppContext;
import org.vaadin.jetty.VaadinJettyServer;

/**
 * @author Alejandro Duarte.
 */
@Theme(ValoTheme.THEME_NAME)
public class TestUI extends UI {

    public static void main(String[] args) throws Exception {
        VaadinJettyServer server = new VaadinJettyServer(8080, TestUI.class);
        WebAppContext handler = (WebAppContext) server.getHandler();
        handler.addEventListener(new CorsSessionListener());
        server.start();
    }

    @Override
    protected void init(VaadinRequest request) {
        VaadinUIComponent ui1 = new VaadinUIComponent("http://localhost:9001");
        ui1.setSizeFull();
        VaadinUIComponent ui2 = new VaadinUIComponent("http://localhost:9002");

        Button button1 = new Button("Local", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                e.getButton().setCaption("It works!");
            }
        });
        VerticalLayout mainLayout = new VerticalLayout(button1, ui1, ui2);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(ui1, 1);
        setContent(mainLayout);
    }

}
