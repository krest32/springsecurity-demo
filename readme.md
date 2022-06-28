# Spring Security

## 流程

> #  登陆部分
>
> 1. 执行 UsernamePasswordAuthenticationFilter 过滤器中的 attemptAuthentication（尝试登录），获取登陆的信息
> 2. 重写UserDetilService 从数据库获取数据，然后放入到SerurityUser当中
> 3. 然后执行密码验证的规则，对比登录密码与数据库加密的密码
> 4. `successfulAuthentication` 登录成功后，生成token放入到Reponse中，同时获取用户的角色列表，然后存放到Redis中
>
> # 权限验证部分
>
> 1. `BasicAuthenticationFilter` 中 doFilterInternal 执行这个方法，获取权限列表
> 2. 从请求中获取到token，然后从token中获取用户id，然后从redis中得到对应的permission列表
> 3. 将permissionList作为权限列表，用于权限的调用

