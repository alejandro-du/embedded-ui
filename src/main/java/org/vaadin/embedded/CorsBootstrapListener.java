package org.vaadin.embedded;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;

/**
 * A Vaadin {@link BootstrapListener} that configures a <i>proxy</i> <code>XMLHttpRequest.send</code> function to activate
 * cross-site Access-Control requests (CORS). You can use this directly or add a {@link CorsSessionListener} instead.
 *
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
