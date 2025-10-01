package com.autohub.auth.repository;

import com.autohub.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository // Marks this interface as a Spring Data repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by their email address. Spring Data JPA automatically creates
     * the query for this method based on its name.
     *
     * @param email the email to search for
     * @return an Optional containing the user if found, or an empty Optional otherwise
     */
    Optional<User> findByEmail(String email);
}