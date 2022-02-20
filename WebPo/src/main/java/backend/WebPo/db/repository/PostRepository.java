package backend.WebPo.db.repository;

import backend.WebPo.db.entity.Member;
import backend.WebPo.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByMember(Member member);
}
