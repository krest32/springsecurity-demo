(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-0d3a"],{N4Yp:function(e,t,r){"use strict";var l=r("t3Un");t.a={addNewRole:function(e){return Object(l.a)({url:"/acl/role/addNewRole",method:"post",data:e})},deleteRoleById:function(e){return Object(l.a)({url:"/acl/role/deleteRoleById/"+e,method:"delete"})},updateRoleById:function(e){return Object(l.a)({url:"/acl/role/updateRoleById",method:"post",data:e})},getRoleById:function(e){return Object(l.a)({url:"/acl/role/getRoleById/"+e,method:"get"})},getUserRoleList:function(){return Object(l.a)({url:"/acl/user-role/getAll",method:"get"})},getUserList:function(){return Object(l.a)({url:"/acl/user/getAllAdminUser",method:"post"})},deleteUser:function(e){return Object(l.a)({url:"/acl/user/delete/"+e,method:"delete"})},getAdminUserById:function(e){return Object(l.a)({url:"/acl/user/getAdminUserById/"+e,method:"post"})},getRoleList:function(){return Object(l.a)({url:"/acl/role/getAll",method:"get"})},updateAdminUserById:function(e){return Object(l.a)({url:"/acl/user/updateAdminUserById",method:"post",data:e})},deleteUserRole:function(e){return Object(l.a)({url:"/acl/user-role/delete/"+e,method:"delete"})},saveAdminUser:function(e){return Object(l.a)({url:"/acl/user/saveAdminUser",method:"post",data:e})},save:function(e){return Object(l.a)({url:"/acl/user/register",method:"post",data:e})},delete:function(e){return Object(l.a)({url:"/employee/tbl/delete/"+e,method:"delete"})}}},Skdx:function(e,t,r){"use strict";r.r(t);var l=r("QbLZ"),a=r.n(l),o=r("N4Yp"),s={roleName:"",remark:"",roleCode:""},n={data:function(){return{role:s,saveBtnDisabled:!1,validateRules:{roleName:[{required:!0,trigger:"blur",message:"用户名必须输入"}]}}},watch:{$route:function(e,t){console.log("路由变化......"),console.log(e),console.log(t),this.init()}},created:function(){console.log("form created ......"),this.init()},methods:{init:function(){if(this.$route.params&&this.$route.params.id){var e=this.$route.params.id;this.fetchDataById(e)}else this.role=a()({},s)},saveOrUpdate:function(){var e=this;this.$refs.user.validate(function(t){t&&(e.saveBtnDisabled=!0,e.role.id?e.updateData():e.saveData())})},saveData:function(){var e=this;o.a.addNewRole(this.role).then(function(t){t.success&&(e.$message({type:"success",message:t.message}),e.$router.push({path:"/auth/rolelist"}))})},updateData:function(){var e=this;o.a.updateRoleById(this.role).then(function(t){t.success&&(e.$message({type:"success",message:t.message}),e.$router.push({path:"/auth/rolelist"}))})},fetchDataById:function(e){var t=this;o.a.getRoleById(e).then(function(e){t.role=e.data.role,console.log(e.data)})}}},u=r("KHd+"),d=Object(u.a)(n,function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-form",{ref:"user",attrs:{model:e.role,rules:e.validateRules,"label-width":"120px"}},[r("el-form-item",{attrs:{label:"用户名",prop:"username"}},[r("el-input",{model:{value:e.role.roleName,callback:function(t){e.$set(e.role,"roleName",t)},expression:"role.roleName"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"角色编码"}},[r("el-input",{model:{value:e.role.roleCode,callback:function(t){e.$set(e.role,"roleCode",t)},expression:"role.roleCode"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"备注"}},[r("el-input",{model:{value:e.role.remark,callback:function(t){e.$set(e.role,"remark",t)},expression:"role.remark"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{disabled:e.saveBtnDisabled,type:"primary"},on:{click:e.saveOrUpdate}},[e._v("保存")])],1)],1)],1)},[],!1,null,null,null);d.options.__file="addRole.vue";t.default=d.exports}}]);