package com.study.springDesign.detailService;

import com.study.springDesign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名查询用户及其权限列表
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.study.springDesign.pojo.User user = userMapper.selectUserByUsername(username);
        if(user==null){
            throw new  UsernameNotFoundException("用户名或密码错误");
        }
        return new User(
                username,//用户名
                user.getPassword(),//密文密码
                AuthorityUtils.NO_AUTHORITIES //权限类型，NO_AUTHORITIES 表示无权限
        );
    }
}
