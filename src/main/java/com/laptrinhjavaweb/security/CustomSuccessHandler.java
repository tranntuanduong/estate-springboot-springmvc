package com.laptrinhjavaweb.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.utils.SecurityUtils;




@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	private String determineTargetUrl(Authentication authentication) {
		String url = "";
		List<String> roles = SecurityUtils.getAuthorities();
		if (isManager(roles)) {
			url = "/admin/home";
		} else if (isStaff(roles)) {
			url = "/admin/home"; 
		}
		return url;
	}
	
	private boolean isManager(List<String> roles) {
		if (roles.contains("MANAGER")) {
			return true;
		}
		return false;
	}
	
	private boolean isStaff(List<String> roles) {
		if (roles.contains("STAFF")) {
			return true;
		}
		return false;
	}
}
