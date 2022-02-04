package paddy.tnpwebapp.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
@Configuration
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if(response.isCommitted()) return;
		
		String targetUrl=determineTargetUrl(authentication);
		RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private String determineTargetUrl(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
		Set<String> roles=new HashSet<>();
		for(GrantedAuthority a:authorities) roles.add(a.getAuthority());
		if(roles.contains("ADMIN")) return "/admin";
		if(roles.contains("USER")) return "/";
		return "/login?error=true";
	}

}
