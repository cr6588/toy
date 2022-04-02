package com.cr6588.entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cr6588.annotation.IgnoreParseToMap;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * create in 2022年03月30日
 * @category TODO
 * @author chenyi
 */
@Data
@Slf4j
@TableName("user")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String name;
    private String password;

    /**
     * 获取查询map
     * 会忽略带有{@link IgnoreParseToMap}注解的属性以及静态属性
     * @param fields 要忽略的属性名称
     * @return
     */
    public Map<String, Object> getParamMap(String... fields) {
        Set<String> fieldSet = new HashSet<>();
        if (fields != null && fields.length != 0) {
            for (String f : fields) {
                fieldSet.add(f);
            }
        }
        Map<String, Object> m = new HashMap<>();
        Class<?> clazz = this.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            for (Field f : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) {
                    // 跳过静态属性
                    continue;
                }
                Annotation a = f.getAnnotation(IgnoreParseToMap.class);
                if(a != null) {
                    continue;
                }
                String fieldName = f.getName();
                if (fieldSet.contains(fieldName)) {
                    continue;
                }
                Object val = null;
                try {
                    f.setAccessible(true);
                    val = f.get(this);
                } catch (Exception e) {
                    log.error(this.toString() + "_" + e.getMessage(), e);
                    continue;
                }
                if (val == null) {
                    continue;
                }
                if (val instanceof String && !StringUtils.hasText((String) val)) {
                    continue;
                }
                m.put(fieldName, val);
            }
        }
        return m;
    }
}
