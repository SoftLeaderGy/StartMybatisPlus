package com.guo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.mapper.UserMapper;
import com.guo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BaseMapper，所有的方法都来自于自己的父类，我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //  查询全部用户
        //参数是一个Wapper， 条件构造器
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入功能
    @Test
    public void testInsert() {

        User user = new User();
        user.setName("洋");
        user.setAge(29);
        user.setEmail("826044698@qq.com");


        int result = userMapper.insert(user);// 帮我们自动生成了 id

        System.out.println(result);// 受影响的行数
        System.out.println(user); // 发现id会自动回填
    }

    //更新操作测试
    @Test
    public void testUpdata() {
        User user = new User();

        // 通过条件自动拼接动态sql
        user.setName("郭洋");
        user.setId(1L);

        //注意：虽然方法名字叫 updateById，但是 参数 是一个对象！
        userMapper.updateById(user);
    }


    //测试乐观锁 成功

    @Test
    public void OptimisticLocker() {
        // 1、查询用户信息
        User user = userMapper.selectById(1L);

        // 2、修改用户信息
        user.setName("guoyang");
        user.setEmail("1765567867@qq.com");

        // 3、执行更新操作
        userMapper.updateById(user);


    }


    //测试乐观锁 失败  多线程下

    @Test
    public void OptimisticLocker2() {

        //线程1
        User user = userMapper.selectById(1L);
        user.setName("guoyang111");
        user.setEmail("1765567867@qq.com");

        //模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("guoyang222");
        user2.setEmail("1765567867@qq.com");
        userMapper.updateById(user2);

        //
        userMapper.updateById(user);//  如果没有乐观锁 就会覆盖插队线程的值
    }


    //测试查询
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);

        System.out.println(user);
    }


    // 测试批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));

        users.forEach(System.out::println);
    }

    @Test
    //按条件查询 之一 使用 map   （where 条件查询）
    public void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();

        //自定义查询
        map.put("name", "guoyang222");
        map.put("age", 3);

        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }


    //测试分页查询
    @Test
    public void testPage() {
        //参数一： 当前第几页
        //参数二： 页面显示几条数据

        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }


    //测试删除
    @Test
    public void testDeleteById() {

        userMapper.deleteById(2L);
    }


    // 通过id批量删除
    @Test
    public void testDeleteBatchById() {

        userMapper.deleteBatchIds(Arrays.asList(1310564048345018370L, 1310754142725124098L));
    }

    //通过 map 条件删除
    @Test
    public void testDeleteMapById() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "洋");
        userMapper.deleteByMap(map);
    }

}

