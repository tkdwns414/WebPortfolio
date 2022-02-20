package backend.WebPo.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    String message;
    Integer statusCode = null;

    public static BaseResponse of(Integer statusCode, String message){
        BaseResponse res = new BaseResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
