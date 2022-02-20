package backend.WebPo.api.service;

import backend.WebPo.api.request.CreateMemberRequest;
import backend.WebPo.api.request.EditMemberRequest;
import backend.WebPo.api.response.MemberFindRes;
import backend.WebPo.db.entity.Member;
import backend.WebPo.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public List<MemberFindRes> findAll(){
        List<Member> memberList = memberRepository.findAll();
        List<MemberFindRes> resList = new ArrayList<>();
        for (Member member : memberList) {
            resList.add(MemberFindRes.create(member));
        }
        return resList;
    }

    public MemberFindRes findByLoginId(String memberId){
        Member member = memberRepository.findByLoginId(memberId);
        return MemberFindRes.create(member);
    }

    public Long createMember(CreateMemberRequest request) {
        if (memberRepository.existsByLoginId(request.getLoginId())){
            return -1L;
        }
        Member member = new Member();
        member.setLoginId(request.getLoginId());
        member.setLoginPw(passwordEncoder.encode(request.getLoginPw()));
        member.setName(request.getName());
        member.setAge(request.getAge());
        member.setEmail(request.getEmail());

        memberRepository.save(member);
        return member.getId();
    }

    public Long updateMember(String memberId, EditMemberRequest request) {
        Member member = memberRepository.findByLoginId(memberId);
        member.setLoginPw(passwordEncoder.encode(request.getLoginPw()));
        member.setName(request.getName());
        member.setAge(request.getAge());
        member.setEmail(request.getEmail());

        memberRepository.save(member);
        return member.getId();
    }

    public void delete(String memberId) {
        Member deleteMember = memberRepository.findByLoginId(memberId);
        memberRepository.delete(deleteMember);
    }
}
