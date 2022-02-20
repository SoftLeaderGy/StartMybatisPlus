package com.guo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j   //日志
@Component   // 将类放在springboot容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("statr insert fill...");

        // 里边的三个参数：
        // (java.lang.String fieldName,  需要填充的字段名
        // java.lang.Object fieldVal,   填充的字段值
        // org.apache.ibatis.reflection.MetaObject metaObject)   给那个数据处理

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    // 更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");

        this.setFieldValByName("updateTime", new Date(), metaObject);

    }
}
