package org.vaadin.embedded;

import org.vaadin.elements.Element;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Import;
import org.vaadin.elements.Tag;

/**
 * @author Alejandro Duarte.
 */
@Tag("vaadin-ui")
@Import("VAADIN/bower_components/vaadin-ui/vaadin-ui.html")
public interface VaadinUIElement extends Element {

    static VaadinUIElement create(String url, String id, String theme, String version, String widgetset) {
        VaadinUIElement element = Elements.create(VaadinUIElement.class);
        element.setUrl(url);
        element.setId(id);
        element.setTheme(theme);
        element.setVersion(version);
        element.setWidgetset(widgetset);
        return element;
    }

    void setUrl(String url);

    void setId(String id);

    void setTheme(String theme);

    void setVersion(String version);

    void setWidgetset(String widgetset);

}
