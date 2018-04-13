package com.example.eurekaclient.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * UserInfoDto
 */
public class UserInfoDto {
    /* 编号 */
    private long id;
    /* 用户名 */
    private String username;
    /* 密码 */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
