package org.dynasite.page;

import fi.iki.elonen.NanoHTTPD;

import java.net.URI;
import java.util.Map;

/**
 * Basic implementable representation of a webpage or other web content
 * which is served to a client upon request, usually at specific urls.
 * Simply put, a Page returns the content (e.g. html page or js file)
 * given to client web browsers.
 */
public abstract class Page {

    public static final Page PAGE_NOT_FOUND = new Page(null) {

        @Override
        public NanoHTTPD.Response getPageResponse(Map<String, String> headers, NanoHTTPD.IHTTPSession session) {
            return NanoHTTPD.newFixedLengthResponse(
                    NanoHTTPD.Response.Status.NOT_FOUND, NanoHTTPD.MIME_HTML, "<h1>Page Not Found!</h1>"
            );
        }
    };

    private final URI uri;

    protected Page(URI uri) {
        this.uri = uri;
    }

    public abstract NanoHTTPD.Response getPageResponse(Map<String, String> headers, NanoHTTPD.IHTTPSession session);

    public URI getUri() {
        return uri;
    }
}