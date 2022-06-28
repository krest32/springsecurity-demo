package com.krest.acl.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/20 14:29
 * @description:
 */
@Data
public class UserInfoVo {
    String username;
    String avatar="https://duxin2010.oss-cn-beijing.aliyuncs.com/26a8b62088334ba5b4d6ab48635dad53file.png";
    String nickname;
    List<String> roles=new ArrayList<String>();
}
