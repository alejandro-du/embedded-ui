package org.vaadin.embedded;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

import java.util.UUID;

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

    private String appId;

    public VaadinUIComponent(String url) {
        this.url = url;
    }

    @Override
    public void attach() {
        super.attach();
        appId = UUID.randomUUID().toString();
        callFunction("init", url, appId);
    }

    @Override
    public void detach() {
        super.detach();
        // TODO: The following is not currently available in the framework, but necessary to fix #1:
        // com.vaadin.ui.JavaScript.eval("window.vaadin.removeApp('" + appId + "');");
    }

    public String getUrl() {
        return url;
    }

    public String getAppId() {
        return appId;
    }

}
