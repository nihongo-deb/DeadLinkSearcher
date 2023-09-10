package org.nihongo_deb.DeadLinkSearcher.Configs;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.nihongo_deb.DeadLinkSearcher.Entity.Role;
import org.nihongo_deb.DeadLinkSearcher.Util.JwtTokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsername(token);
            } catch (ExpiredJwtException e){
                System.out.println("время токена истекло");
            } catch (SignatureException e) {
                System.out.println("Токен не валиден");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<Role> roles = jwtTokenUtils.getRoles(token);
            System.out.println(username);
            System.out.println(roles);
            System.out.println(token);

            UsernamePasswordAuthenticationToken springToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles
                            .stream()
                            .map((Role role) -> new SimpleGrantedAuthority(role.toString()))
                            .collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(springToken);
        }

        filterChain.doFilter(request, response);
    }
}
