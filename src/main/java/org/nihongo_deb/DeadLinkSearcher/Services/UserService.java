package org.nihongo_deb.DeadLinkSearcher.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nihongo_deb.DeadLinkSearcher.DTO.RegistrationUserDto;
import org.nihongo_deb.DeadLinkSearcher.Entity.Role;
import org.nihongo_deb.DeadLinkSearcher.Entity.User;
import org.nihongo_deb.DeadLinkSearcher.Exceptions.EmailAlreadyExistsException;
import org.nihongo_deb.DeadLinkSearcher.Exceptions.PasswordIsNotEqualsConfirmedPasswordException;
import org.nihongo_deb.DeadLinkSearcher.Exceptions.TelegramIdAlreadyExistsException;
import org.nihongo_deb.DeadLinkSearcher.Exceptions.UsernameAlreadyExistsException;
import org.nihongo_deb.DeadLinkSearcher.Repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

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
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user){
        user.setRoles(List.of(roleService.findRoleByName(Role.RoleType.ROLE_USER.name().toString()).get()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    @Transactional
    public void createNewUser(RegistrationUserDto userDto){
        if (!userDto.getPassword().equals(userDto.getConfirmedPassword()))
            throw new PasswordIsNotEqualsConfirmedPasswordException("Пароли не совпадают");

        if (userRepository.existsByUsername(userDto.getUsername()))
            throw new UsernameAlreadyExistsException(String.format("Пользователь с именем '%s' уже существует", userDto.getUsername()));

        if (userRepository.existsByEmail(userDto.getEmail()))
            throw new EmailAlreadyExistsException(String.format("Пользователь с электронной почтой '%s' уже существует", userDto.getEmail()));

        if (userRepository.existsByTelegramId(userDto.getTelegramId()))
            throw new TelegramIdAlreadyExistsException(String.format("Пользователь с Telegram-ID '%s' уже существует", userDto.getTelegramId()));

        User user = modelMapper.map(userDto, User.class);
        createNewUser(user);
    }
}
