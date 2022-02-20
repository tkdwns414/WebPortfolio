package backend.WebPo.api.controller;

import backend.WebPo.api.argumentResolver.Login;
import backend.WebPo.api.request.CreatePostReq;
import backend.WebPo.api.request.EditPostReq;
import backend.WebPo.api.response.BaseResponse;
import backend.WebPo.api.response.PostFindRes;
import backend.WebPo.api.service.MemberService;
import backend.WebPo.api.service.PostService;
import backend.WebPo.db.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    @GetMapping
    public ResponseEntity posts(){
        return new ResponseEntity(postService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid CreatePostReq request, @Login Member loginMember){
        if (loginMember.getLoginId() == null) {
            return ResponseEntity.status(401).body(BaseResponse.of(401, "No Login Information"));
        }
        Long id = postService.createPost(request, loginMember);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPostById(@PathVariable Long postId){
        PostFindRes post = postService.findById(postId);
        return new ResponseEntity(post, HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity getPostByMember(@PathVariable String memberId){
        return new ResponseEntity(postService.findByMember(memberId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/edit")
    public ResponseEntity editPost(@PathVariable Long postId, @RequestBody @Valid EditPostReq request, @Login Member loginMember){
        Long id = postService.editPost(postId, request, loginMember);
        if (id == -1L){
            return ResponseEntity.status(401).body(BaseResponse.of(401, "Invalid Member"));
        }
        return new ResponseEntity(id,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity deletePost(@PathVariable Long postId){
        postService.delete(postId);
        return ResponseEntity.status(200).body(BaseResponse.of(200,"Post Delete Success"));
    }

}
