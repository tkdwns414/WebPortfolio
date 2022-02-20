package backend.WebPo.db.repository;

import backend.WebPo.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
