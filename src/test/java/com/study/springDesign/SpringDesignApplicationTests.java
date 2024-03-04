package com.study.springDesign;

import com.study.springDesign.Util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;


@SpringBootTest
class SpringDesignApplicationTests {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test() {
        Map<String,Integer> map=new HashMap<>();
        Stack<int[]> stack=new Stack<>();
        stack.push(new int[]{1,2});

    }
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a, b)->{
            if(a[0]==b[0])return b[1]-a[1];
            return a[0]-b[0];
        });
        LinkedList<int[]> que=new LinkedList<>();
        for(int[] person:people){
            que.add(person[1],person);
        }
        return que.toArray(new int[people.length][]);
    }
}
