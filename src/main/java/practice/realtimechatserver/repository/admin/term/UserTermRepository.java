package practice.realtimechatserver.repository.admin.term;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.realtimechatserver.model.admin.term.UserTerm;

public interface UserTermRepository extends JpaRepository<UserTerm, Integer> {
}
