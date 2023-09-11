package org.nihongo_deb.DeadLinkSearcher.Services;

import lombok.RequiredArgsConstructor;
import org.nihongo_deb.DeadLinkSearcher.Entity.Role;
import org.nihongo_deb.DeadLinkSearcher.Repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    Optional<Role> findRoleByName(String name){
        return this.roleRepository.findByName(name); // TODO: not found exception
    }
}
