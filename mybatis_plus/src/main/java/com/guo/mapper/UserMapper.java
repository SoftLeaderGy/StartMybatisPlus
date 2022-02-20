package com.guo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.pojo.User;
import org.springframework.stereotype.Repository;

//在对应的Mapper上面继承基本的类 BaseMapper
@Repository //表示这个类是持久层的（表示他是个mapper或者是dao层的）
public interface UserMapper extends BaseMapper<User> {

    //所有的crud操作已经完成了
    //不需要像以前一样配置一堆文件

}
