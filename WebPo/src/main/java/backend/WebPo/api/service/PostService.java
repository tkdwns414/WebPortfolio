package backend.WebPo.api.service;

import backend.WebPo.api.request.CreatePostReq;
import backend.WebPo.api.request.EditPostReq;
import backend.WebPo.api.response.MemberFindRes;
import backend.WebPo.api.response.PostFindRes;
import backend.WebPo.db.entity.Member;
import backend.WebPo.db.entity.Post;
import backend.WebPo.db.repository.MemberRepository;
import backend.WebPo.db.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public List<PostFindRes> findAll(){
        List<Post> postList = postRepository.findAll();
        List<PostFindRes> resList = new ArrayList<>();
        for (Post post : postList) {
            resList.add(PostFindRes.create(post));
        }
        return resList;
    }

    public PostFindRes findById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostFindRes.create(post);
    }

    public List<PostFindRes> findByMember(String memberId){
        Member member = memberRepository.findByLoginId(memberId);
        List<Post> postList = postRepository.findByMember(member);
        List<PostFindRes> resList = new ArrayList<>();
        for (Post post : postList) {
            resList.add(PostFindRes.create(post));
        }
        return resList;
    }

    public Long createPost(CreatePostReq request, Member member) {
        Post post = new Post();
        post.setMember(member);
        post.setTitle(request.getTitle());
        post.setDetail(request.getDetail());
        post.setUrls(request.getUrls());
        
        postRepository.save(post);
        
        return post.getId();
    }

    public Long editPost(Long id, EditPostReq request, Member loginMember) {
        Post post = postRepository.findById(id).get();
        if (post.getMember() != loginMember){
            return -1L;
        }
        post.setTitle(request.getTitle());
        post.setDetail(request.getDetail());
        post.setUrls(request.getUrls());
        
        return post.getId();
    }

    public void delete(Long postId) {
        Post deletePost = postRepository.findById(postId).get();
        postRepository.delete(deletePost);
    }
}
