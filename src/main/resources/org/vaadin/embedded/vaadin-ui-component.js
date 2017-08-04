window.org_vaadin_embedded_VaadinUIComponent = function() {
    var element = this.getElement();
    this.init = function(url, appId) {
        httpGet(url, function(responseText) {
            var embedded = document.createElement("html");
            embedded.innerHTML = replaceStrings(responseText, url, appId);
            // TODO: add head elements
            var body = embedded.getElementsByTagName("body")[0];
            element.innerHTML = body.innerHTML;
            runScripts(element);
            window.addHook()
        });
    }
}

window.httpGet = function(url, callback) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200)
            callback(request.responseText);
    }
    request.open("get", url, true);
    request.send(null);
}

window.replaceStrings = function(html, url, appId) {
    var regEx = /(vaadin\.initApplication\(\")(.*)(\".*)/g;
    var match = regEx.exec(html);
    var o = match[2];
    var n = appId;

    while(html.includes(o)) {
        html = html.replace(o, n);
    }

    regEx = /(\"vaadinDir\")(.*\")(.*)(\".*)/g;
    match = regEx.exec(html);
    o = match[1] + match[2] + match[3] + match[4];
    n = match[1] + match[2] + url + "/" + match[3] + match[4];
    html = html.replace(o, n);

    regEx = /(\"serviceUrl\")(.*\")(.*)(\".*)/g;
    match = regEx.exec(html);
    o = match[1] + match[2] + match[3] + match[4];
    n = match[1] + match[2] + (match[3].startsWith("/") ? url + match[3] : url) + match[4];
    html = html.replace(o, n);

    regEx = /(\"standalone\":)( *)(true)(.*)/g;
    match = regEx.exec(html);
    o = match[1] + match[2] + match[3] + match[4];
    n = match[1] + match[2] + "false" + match[4] + (match[4].endsWith(",") ? "" : ",") +
    "\"browserDetailsUrl\": \"" + url + "\"" + (match[4].endsWith(",") ? "," : "");
    html = html.replace(o, n);

    return html;
}

window.runScripts = function(element) {
    function nodeName(elem, name) {
        return elem.nodeName && elem.nodeName.toUpperCase() === name.toUpperCase();
    };

    function evalScript(elem) {
        var data = (elem.text || elem.textContent || elem.innerHTML || "" ),
            head = document.getElementsByTagName("head")[0] || document.documentElement,
            script = document.createElement("script");

        script.type = "text/javascript";
        try {
            script.appendChild(document.createTextNode(data));
        } catch(e) {
            script.text = data; // IE
        }

        head.insertBefore(script, head.firstChild);
        head.removeChild(script);
    };

    var scripts = [], script, children_nodes = element.childNodes, child, i;

    for (i = 0; children_nodes[i]; i++) {
        child = children_nodes[i];
        if (nodeName(child, "script" ) && (!child.type || child.type.toLowerCase() === "text/javascript")) {
            scripts.push(child);
        }
    }
    for (i = 0; scripts[i]; i++) {
        script = scripts[i];
        if (script.parentNode) {
            script.parentNode.removeChild(script);
        }
        evalScript(scripts[i]);
    }
}

window.addHook = function() {
    var hook = function() {
        window.vaadin.forceLayout();
    }

    if (!window.vaadin.postRequestHooks) {
        window.vaadin.postRequestHooks = [hook];
    } else {
        window.vaadin.postRequestHooks.push(hook);
    }
}
