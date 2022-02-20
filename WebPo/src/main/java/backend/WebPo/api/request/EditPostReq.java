package backend.WebPo.api.request;

import backend.WebPo.db.entity.Member;
import backend.WebPo.db.entity.PostUrl;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class EditPostReq {

    @NotEmpty
    private String title;

    @NotEmpty
    private String Detail;

    private List<PostUrl> urls = new ArrayList<>();
}
