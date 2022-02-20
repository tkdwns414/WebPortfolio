package backend.WebPo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String detail;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostUrl> urls = new ArrayList<>();

    //이미지 나중에 추가하기
}