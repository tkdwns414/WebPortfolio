package backend.WebPo.db.entity;

import javax.persistence.*;

@Entity
public class PostUrl {

    @Id @GeneratedValue
    @Column(name = "posturl_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="post_id")
    private Post post;

    private String url;
}
