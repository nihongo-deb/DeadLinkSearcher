package org.nihongo_deb.DeadLinkSearcher.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.nihongo_deb.DeadLinkSearcher.Entity.Role;
import org.nihongo_deb.DeadLinkSearcher.Entity.User;
import org.nihongo_deb.DeadLinkSearcher.Repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(user.getRole()).stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user){
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
}
