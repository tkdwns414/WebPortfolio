package backend.WebPo.api.response;

import backend.WebPo.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFindRes {

    private Long id;

    private String loginId;

    private String loginPw;

    private String name;

    private Integer age;

    private String email;

    public static MemberFindRes create(Member member){
        MemberFindRes res =  new MemberFindRes();
        res.setId(member.getId());
        res.setLoginId(member.getLoginId());
        res.setLoginPw(member.getLoginPw());
        res.setName(member.getName());
        res.setAge(member.getAge());
        res.setEmail(member.getEmail());

        return res;
    }
}
