package com.julius.filter;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.julius.utils.JwtUtils;
import com.julius.utils.SignatureUtils;
import io.jsonwebtoken.Jwts;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ReflectionUtils;
import sun.reflect.misc.ReflectUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 从请求中获取用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 创建认证令牌
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            // 通过认证管理器进行认证
            return authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            // 处理认证失败的情况
            try {
                unsuccessfulAuthentication(request, response, e);
            } catch (Exception ex) {
                //
            }
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 处理认证成功的情况
        UserDetails userDetails = userDetailsService.loadUserByUsername(authResult.getName());
        //上一步没有抛出异常说明认证成功，我们向用户颁发jwt令牌

        // 使用私钥创建JWT

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(userDetails);
        } catch (Exception e) {

        }
        String token = JwtUtils.getToken(map);

        // 将 JWT 设置到响应头中
        response.addHeader("Authorization", "Bearer " + token);

        // 父类的默认实现将继续过滤器链
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)  throws IOException, ServletException {
        // 处理认证失败的情况
        // 返回认证失败的响应，可以根据需要自定义响应内容
        // ...

        // 父类的默认实现将继续过滤器链
        super.unsuccessfulAuthentication(request, response, failed);
    }


}
