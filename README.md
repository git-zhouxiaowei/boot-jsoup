# boot-jsoup
Springboot + jsoup + mail 超简单应用，定时获取某网页通知列表数据，新通知邮件发送通知。

邮件发送使用了万能工具箱Hutool：https://gitee.com/loolly/hutool 

爬取数据涉及法律风险，对爬取的网站，务必查看其robots.txt文件，位置在：域名/robots.txt

本示例仅为学习使用！

修改发件邮箱配置信息：

-src

--resources

---config
    
 mail.setting
 
  发件人
 
 from = xxx@xxx
 
  密码（注意，这不是登陆密码，而是为SMTP服务单独设置的，到邮箱后台获取）
 
 pass = xxxx

