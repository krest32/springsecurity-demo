package com.krest.mysecurity.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用来封装登录的对象信息
 */
@Data
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String password;

	private String token;

}



