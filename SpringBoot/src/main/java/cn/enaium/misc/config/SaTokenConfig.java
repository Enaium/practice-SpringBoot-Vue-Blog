package cn.enaium.misc.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.enaium.misc.mapper.MemberMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Enaium
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    public SaTokenConfig(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "*");
            if ("OPTIONS".equals(request.getMethod())) return;


            SaRouterUtil.match(Collections.singletonList("/**"), Arrays.asList("/login", "/register"), StpUtil::checkLogin);
            SaRouterUtil.match("/delete/**", () -> StpUtil.checkRoleOr("admin"));
        })).addPathPatterns("/**");
    }

    final MemberMapper memberMapper;

    @Bean
    public StpInterface stpInterface() {
        return new StpInterface() {
            @Override
            public List<String> getPermissionList(Object loginId, String loginKey) {
                return Arrays.asList(memberMapper.selectById(Long.parseLong(String.valueOf(loginId))).getPermission().split(","));
            }

            @Override
            public List<String> getRoleList(Object loginId, String loginKey) {
                return Arrays.asList(memberMapper.selectById(Long.parseLong(String.valueOf(loginId))).getRole().split(","));
            }
        };
    }
}
