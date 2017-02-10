# org.apache.cordova.echo
cordova 插件开发

未做ios版本测试，安卓部分参考了其它插件代码修改而来
1.参考资料：
官方：http://cordova.apache.org/docs/en/6.x/guide/hybrid/plugins/index.html 
2.开发工具不要使用vs studio，会有各种乱码问题，如果使用乱码了参考如下解决方法（就是去bom）
http://www.cnblogs.com/Steven-shi/p/5038108.html 
3.未做github发布，未对第三方SDK调用测试。
4.本地安装
cordova plugins add local\org.apache.cordova.echo

调用
/*
success:成功回调函数
error：失败回调函数
ar：参数数组，这个暂时无用
*/
echo.sayHello(sucess, error, ar)

/*
message:消息内容
title：标题
buttonlabel：默认ok
*/
echo.alert(message, completeCallback, title, buttonLabel)
