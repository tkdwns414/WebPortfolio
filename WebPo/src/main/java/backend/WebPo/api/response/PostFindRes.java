package backend.WebPo.api.response;

import backend.WebPo.db.entity.Member;
import backend.WebPo.db.entity.Post;
import backend.WebPo.db.entity.PostUrl;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostFindRes {

    private Long id;

    private Member member;

    private String title;

    private String detail;

    private List<PostUrl> urls = new ArrayList<>();

    public static PostFindRes create(Post post) {
        PostFindRes res = new PostFindRes();
        res.setId(post.getId());
        res.setMember(post.getMember());
        res.setTitle(post.getTitle());
        res.setDetail(post.getDetail());
        res.setUrls(post.getUrls());

        return res;
    }
}
