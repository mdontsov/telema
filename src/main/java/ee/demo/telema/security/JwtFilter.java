package ee.demo.telema.security;

import ee.demo.telema.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final UserDetailsService userService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    validateAndAuthenticateJwtFrom(request);

    filterChain.doFilter(request, response);
  }

  private void validateAndAuthenticateJwtFrom(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }

    var token = authHeader.substring(7);
    var userName = jwtService.extractUsername(token);

    if (userName == null
        || SecurityContextHolder.getContext()
        .getAuthentication() == null) {
      return;
    }

    var userDetails = userService.loadUserByUsername(userName);
    if (jwtService.validateToken(token, userDetails)) {
      var authToken = jwtService.getAuthenticationToken(token, userDetails);
      authToken.setAuthenticated(true);
    }
  }
}
