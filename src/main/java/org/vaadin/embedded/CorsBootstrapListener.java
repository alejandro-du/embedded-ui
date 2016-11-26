package org.vaadin.embedded;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;

/**
 * @author Alejandro Duarte.
 */
public class CorsBootstrapListener implements BootstrapListener {

    @Override
    public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
    }

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        response.getDocument().head().append(getHeadFragmentHtml());
    }

    protected String getHeadFragmentHtml() {
        return "<script>" +
                "XMLHttpRequest.prototype._realSend = XMLHttpRequest.prototype.send;" +
                "var sendProxy = function(data) {this.withCredentials = true;this._realSend(data);};" +
                "XMLHttpRequest.prototype.send = sendProxy;" +
                "</script>";
    }

}
