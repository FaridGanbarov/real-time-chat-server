package practice.realtimechatserver.repository.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.realtimechatserver.model.user.UserEmail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<UserEmail, Long> {
    @Transactional
    void deleteByEmail(String email);

    Optional<UserEmail> findByEmail(String email);

    List<UserEmail> findAllByVerifiedFalseAndCreatedAtBefore(LocalDateTime cutoffTime);
}
