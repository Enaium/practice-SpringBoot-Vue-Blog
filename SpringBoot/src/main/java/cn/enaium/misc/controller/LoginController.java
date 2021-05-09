package cn.enaium.misc.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.enaium.misc.common.dto.MemberDTO;
import cn.enaium.misc.common.Result;
import cn.enaium.misc.common.dto.MemberRegisterDTO;
import cn.enaium.misc.entity.MemberEntity;
import cn.enaium.misc.mapper.MemberMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Enaium
 */
@RestController
public class LoginController {

    public final MemberMapper memberMapper;

    public LoginController(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody MemberDTO memberDTO) {
        var memberEntityQueryWrapper = new QueryWrapper<MemberEntity>();
        memberEntityQueryWrapper.eq("username", memberDTO.getUsername());
        memberEntityQueryWrapper.eq("password", memberDTO.getPassword());
        var memberEntity = memberMapper.selectOne(memberEntityQueryWrapper);
        if (memberEntity != null) {
            StpUtil.setLoginId(memberEntity.getId());
            return new Result<>(200, "登录成功", StpUtil.getTokenInfo().getTokenValue());
        }
        return new Result<>(400, "登录失败", null);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        var memberEntityQueryWrapper = new QueryWrapper<MemberEntity>();
        memberEntityQueryWrapper.eq("username", memberRegisterDTO.getUsername());
        var memberEntity = memberMapper.selectOne(memberEntityQueryWrapper);
        if (memberEntity == null) {
            memberMapper.insert(new MemberEntity(1L, memberRegisterDTO.getUsername(), memberRegisterDTO.getPassword(), memberRegisterDTO.getName(), "member", "member*"));
            return new Result<>(200, "注册成功", null);
        }
        return new Result<>(400, "注册失败", null);
    }


    @GetMapping("/api")
    public Result<String> api() {
        return new Result<>(200, "success", null);
    }
}
