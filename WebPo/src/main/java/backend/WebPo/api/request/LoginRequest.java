package backend.WebPo.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String loginPw;
}
