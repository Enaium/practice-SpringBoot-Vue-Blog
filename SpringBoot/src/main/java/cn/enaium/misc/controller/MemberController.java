package cn.enaium.misc.controller;

import cn.enaium.misc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Enaium
 */
@RequestMapping("/member")
@RestController
public class MemberController {
    public Result<String> post() {
        return new Result<>(200, "post", null);
    }
}
