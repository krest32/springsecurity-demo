(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1fc6"],{"2tZo":function(e,t,n){"use strict";var l=n("t3Un");t.a={listEmployee:function(){return Object(l.a)({url:"/employee/tbl/listEmployee",method:"get"})},get:function(e){return Object(l.a)({url:"/employee/tbl/get/"+e,method:"get"})},add:function(e){return Object(l.a)({url:"/employee/tbl/add",method:"post",data:e})},update:function(e){return Object(l.a)({url:"/employee/tbl/update",method:"post",data:e})},delete:function(e){return Object(l.a)({url:"/employee/tbl/delete/"+e,method:"delete"})},createStatistics:function(e){return Object(l.a)({url:"/employee/statistics-daily/createDailyCount/"+e,method:"get"})},showChart:function(e){return Object(l.a)({url:"/employee/statistics-daily/show-chart/"+e.begin+"/"+e.end+"/"+e.type,method:"get"})}}},"7fOA":function(e,t,n){},S86f:function(e,t,n){"use strict";var l=n("7fOA");n.n(l).a},cydS:function(e,t,n){"use strict";n.r(t);var l=n("2tZo"),o={data:function(){return{employList:[],tbl:{}}},created:function(){this.getAllEmployee()},methods:{getAllEmployee:function(){var e=this;l.a.listEmployee().then(function(t){e.employList=t.data.list,console.log(e.employList)})},deleteTbl:function(e){var t=this;this.$confirm("此操作将永久删除该记录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){return l.a.delete(e)}).then(function(){t.getAllEmployee(),t.$message({type:"success",message:"删除成功!"})}).catch(function(e){"cancel"===e?t.$message({type:"info",message:"已取消删除"}):t.$message({type:"error",message:"删除失败，该标签有文章正在使用，请先修改文章"})})},getTbl:function(e){this.$router.push({path:"/edit/"+e})}}},i=(n("S86f"),n("KHd+")),a=Object(i.a)(o,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("h1",[e._v("雇员信息列表")]),e._v(" "),n("el-table",{attrs:{data:e.employList,"element-loading-text":"拼命加载中",border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{label:"序号",width:"70",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.$index+1)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{prop:"name",label:"名字",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"singin",label:"登录次数",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"date",label:"日期",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{label:"操作",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-edit"},on:{click:function(n){e.getTbl(t.row.id)}}},[e._v("修改")]),e._v(" "),n("el-button",{attrs:{type:"danger",size:"mini",icon:"el-icon-delete"},on:{click:function(n){e.deleteTbl(t.row.id)}}},[e._v("删除")])]}}])})],1)],1)},[],!1,null,null,null);a.options.__file="list.vue";t.default=a.exports}}]);