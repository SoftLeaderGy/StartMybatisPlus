package com.guo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.mapper.UserMapper;
import com.guo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        // 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);

        userMapper.selectList(Wrapper).forEach(System.out::println);// 和刚学过的map对比一下
    }

    @Test
    void test2() {

        //查询名字等于Tom的
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.eq("name", "Tom");
        User user = userMapper.selectOne(Wrapper);//selectOne 查询一个数据
        System.out.println(user);
    }


    @Test
    void test3() {

        //查询年龄在20～30之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.between("age", 20, 30);// 区间
        Integer count = userMapper.selectCount(wrapper);//查询结果数
        System.out.println(count);

    }


    //模糊查询
    @Test
    void test4() {

        //查询年龄在20～30之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.notLike("name", "e")// name 字段中 不含e的
                .likeRight("email", "t");//likeRight（有通配）相当于  t%

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

    }


    @Test
    void test5() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 假设id在子查询里
        wrapper.inSql("id", "select id from user where id < 3 ");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);

    }


    @Test
    void test6() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 通过id进行排序
        wrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }


    public static void main(String[] args) {

        Object obj= true ? new Integer(1) : new Double(2.0);
        System.out.println(obj.getClass());
    }
}
