package org.vaadin.embedded;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
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

}
