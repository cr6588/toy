package com.cr6588.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

/**
 * create in 2022年04月03日
 * @category TODO
 * @author chenyi
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using=ToStringSerializer.class) //https://blog.csdn.net/qq_35448165/article/details/117266149
    private Long id;
}
