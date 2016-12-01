package org.vaadin.embedded;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * Vaadin component that allows embedding external Vaadin applications into a Vaadin application.
 * Keep in mind that you have to configure CORS if the applications are hosted in different containers.
 * Also, if different servers are used, you'll need to configure different session cookie names for each server.
 *
 * @author Alejandro Duarte.
 */
@JavaScript("vaadin-ui-component.js")
public class VaadinUIComponent extends AbstractJavaScriptComponent {

    private String url;

    public VaadinUIComponent(String url) {
        this.url = url;
    }

    @Override
    public void attach() {
        super.attach();
        callFunction("init", url);
    }

    public String getUrl() {
        return url;
    }

}
