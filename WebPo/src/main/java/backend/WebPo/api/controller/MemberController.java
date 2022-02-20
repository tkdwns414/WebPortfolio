package backend.WebPo.api.controller;

import backend.WebPo.api.request.CreateMemberRequest;
import backend.WebPo.api.request.EditMemberRequest;
import backend.WebPo.api.response.MemberFindRes;
import backend.WebPo.api.service.MemberService;
import backend.WebPo.db.entity.Member;
import backend.WebPo.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberFindRes>> members(){
        return new ResponseEntity(memberService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity findMemberByLoginId(@PathVariable String memberId){
        MemberFindRes member = memberService.findByLoginId(memberId);
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addMember(@RequestBody @Valid CreateMemberRequest request){
        Long id = memberService.createMember(request);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping("/{memberId}/edit")
    public ResponseEntity editMember(@PathVariable String memberId, @RequestBody @Valid EditMemberRequest request){
        Long id = memberService.updateMember(memberId, request);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @DeleteMapping("{memberId}/delete")
    public void deleteMember(@PathVariable String memberId){
        memberService.delete(memberId);
    }


}
