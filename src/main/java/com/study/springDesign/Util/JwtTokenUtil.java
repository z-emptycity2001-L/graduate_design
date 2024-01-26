package com.study.springDesign.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    public  final String CLAIM_KEY_USERNAME="sub";
    public  final String CLAIM_KEY_CREATED="created";


    @Value("${token.secret}")
    private String secret;
//    @Value("${token.expiration}")
    private Long expiration=1000*60*60*24L;

    /*
     * 根据用户信息生成token
     * */
    public  String generateToken(UserDetails userDetails){
//        创建荷载
        Map<String,Object> claims= new HashMap<>();

        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
//        token创建时间
        claims.put(CLAIM_KEY_CREATED,new Date());

        //    根据荷载生成 JWT Token
        return generateToken(claims);
    }
    //    重载 generateToken 方法，生成token
    private  String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)//填入荷载
                .setExpiration(new Date(System.currentTimeMillis()+expiration*1000))//设置生效时间
                .signWith(SignatureAlgorithm.HS512,secret)//设置密钥
                .compact();
    }



    /*
     * 通过 token 获取登陆用户名
     * */
    public  String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username=claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }

    /*
     * 通过 token 获取 荷载
     * */
    public  Claims getClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 判断token是否有效
     **/
    public  Boolean validateToken(String token, UserDetails userDetails){
        String name = getUserNameFromToken(token);

//        1、获取荷载
        Claims claims = getClaimsFromToken(token);
//        2、通过荷载拿到token设置时的时间
        Date setExpirationTime = claims.getExpiration();

//        3、如果传入的用户名和token里的用户名一致并且没有过期   返回true
        return name.equals(userDetails.getUsername())&&!setExpirationTime.before(new Date());
    }

    /*
     * 刷新token
     * */
    public String refresh(String token){
        Claims claims = getClaimsFromToken(token);
//        重新生成token
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /*
     判断 token 是否可刷新
     */
    public Boolean canRefresh(String token){
        Date expiration = getClaimsFromToken(token).getExpiration();
        return !expiration.before(new Date());
    }

}
