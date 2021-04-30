package com.onlinevet.clinic.helper;

import javax.servlet.http.HttpServletRequest;

public class URLHelper {
	private URLHelper() {
	}
	
	
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
