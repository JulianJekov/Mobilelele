package org.softuni.mobilelele.repository;

import org.softuni.mobilelele.model.entity.UserActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserActivationRepository extends JpaRepository<UserActivationCode, Long> {
    @Query("select ac from UserActivationCode ac where ac.created <= now() - 15 minute ")
    List<UserActivationCode> findByCreated();
}
