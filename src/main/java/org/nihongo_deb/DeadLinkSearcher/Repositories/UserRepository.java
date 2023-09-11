package org.nihongo_deb.DeadLinkSearcher.Repositories;

import org.nihongo_deb.DeadLinkSearcher.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String Email);
    boolean existsByTelegramId(Long telegramId);
}
