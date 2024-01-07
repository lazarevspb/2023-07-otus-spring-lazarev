package ru.lazarev.springcourse.library.config;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.lazarev.springcourse.library.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                log.debug("The token is expired");
                //                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "{\"message\": \"JWT is
                //                expired\"");
                //                return;
            }


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username, null,
                                                            jwtTokenUtil.getRoles(jwt).stream()
                                                                .map(
                                                                    SimpleGrantedAuthority::new)
                                                                .collect(
                                                                    Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(token);
            }

            filterChain.doFilter(request, response);
        } else {
            UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("guest", null, Collections.singleton(
                    new SimpleGrantedAuthority("ROLE_GUEST"))
                );
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);
        }
    }
}
