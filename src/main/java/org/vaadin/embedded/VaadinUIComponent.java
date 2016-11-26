package org.vaadin.embedded;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * @author Alejandro Duarte.
 */
@JavaScript("vaadin-ui-component.js")
public class VaadinUIComponent extends AbstractJavaScriptComponent {

    private String url;
    private String theme;
    private String version;
    private String widgetset;

    public VaadinUIComponent(String url, String theme, String version, String widgetset) {
        this.url = url;
        this.theme = theme;
        this.version = version;
        this.widgetset = widgetset;
    }

    @Override
    public void attach() {
        super.attach();
        callFunction("init", url, theme, version, widgetset);
    }

}
