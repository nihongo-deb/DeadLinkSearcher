package org.nihongo_deb.DeadLinkSearcher.Controller;

import lombok.RequiredArgsConstructor;
import org.nihongo_deb.DeadLinkSearcher.DTO.JwtRequest;
import org.nihongo_deb.DeadLinkSearcher.DTO.JwtResponse;
import org.nihongo_deb.DeadLinkSearcher.Exceptions.AppError;
import org.nihongo_deb.DeadLinkSearcher.Services.UserService;
import org.nihongo_deb.DeadLinkSearcher.Util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        System.out.println("good 1");

        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(authRequest.getUsername());
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Пользоватеь не найден"), HttpStatus.UNAUTHORIZED);
        }
        System.out.println("good 2");

        String token = jwtTokenUtils.generateToken(userDetails);
        System.out.println("good 3");
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
