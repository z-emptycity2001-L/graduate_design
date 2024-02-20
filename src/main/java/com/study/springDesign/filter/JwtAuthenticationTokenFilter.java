package com.study.springDesign.filter;

import com.study.springDesign.Util.JwtTokenUtil;
import com.study.springDesign.pojo.Login;
import com.study.springDesign.pojo.User;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        获取token
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            String username;
            //        解析token
            try {
                Claims claims = jwtTokenUtil.getClaimsFromToken(token);
                username = claims.getSubject();
            } catch (Exception e) {
                throw new RuntimeException("无效token");
            }
            if(username!=null){
//                User user = (User) redisUtil.get("login" + userId);
                User user = (User) redisTemplate.opsForValue().get("login" + username);
                // TODO 获取权限信息封装到Authentication中
//                如果token有效
                Login login = new Login(user);
                if (jwtTokenUtil.validateToken(token, login)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        }
//        放行
        filterChain.doFilter(request, response);

    }
}
