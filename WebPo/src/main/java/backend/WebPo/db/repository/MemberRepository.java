package backend.WebPo.db.repository;

import backend.WebPo.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByLoginId(String loginId);
    public boolean existsByLoginId(String loginId);
}
