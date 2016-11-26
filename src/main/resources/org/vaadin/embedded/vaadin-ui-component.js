window.org_vaadin_embedded_VaadinUIComponent = function() {
    var connector = this;

    connector.init = function(url, theme, version, widgetset) {
        var id = "ui-" + connector.getConnectorId();
        connector.getElement().innerHTML = '<style>.' + theme + '.v-app {height:auto;background:transparent;}</style><div id="' + id + '" class="v-app ' + theme + '"></div>';
        vaadin.initApplication(id,{
            "theme": theme,
            "versionInfo": {
                "vaadinVersion": version
            },
            "widgetset": widgetset,
            "comErrMsg": {
                "caption": "Communication problem",
                "message": "Take note of any unsaved data, and <u>click here</u> or press ESC to continue.",
                "url": null
            },
            "authErrMsg": {
                "caption": "Authentication problem",
                "message": "Take note of any unsaved data, and <u>click here</u> or press ESC to continue.",
                "url": null
            },
            "sessExpMsg": {
                "caption": "Session Expired",
                "message": "Take note of any unsaved data, and <u>click here</u> or press ESC key to continue.",
                "url": null
            },
            "vaadinDir": url + "/VAADIN/",
            "debug": false,
            "standalone": true,
            "heartbeatInterval": 300,
            "serviceUrl": url,
            "browserDetailsUrl": url
        });
    }
}
