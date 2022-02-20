package backend.WebPo.api.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EditMemberRequest {

    @NotEmpty
    private String loginPw;

    @NotEmpty
    private String name;

    private Integer age;

    @Email
    private String email;
}
