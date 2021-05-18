package com.vaadin.swrpgd6.app.security;

//package com.vaadin.tutorial.crm.security;
import
        org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
class CustomRequestCache extends HttpSessionRequestCache {
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse
            response) {
        if (!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }
}
