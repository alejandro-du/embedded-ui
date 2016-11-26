package org.vaadin.embedded;

import org.vaadin.elements.AbstractElementComponent;
import org.vaadin.elements.ElementIntegration;
import org.vaadin.elements.Root;

/**
 * @author Alejandro Duarte.
 */
public class VaadinUIComponent extends AbstractElementComponent {

    public VaadinUIComponent(String url, String theme, String version, String widgetset) {
        Root root = ElementIntegration.getRoot(this);
        root.appendChild(VaadinUIElement.create(url, "ui-" + hashCode(), theme, version, widgetset));
    }

}
