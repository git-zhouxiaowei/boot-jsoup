package com.zhou.agriculture.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.mail.MailUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 监控预警
 * @Author Zhouxw
 * @Date 2020/9/1 0001 13:12
 **/
@Component
public class MonitorTask {
    private List<String> titleList = new ArrayList<>();
    private static int times = 0;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void notice() throws IOException {
        String collectUrl = "http://nyncj.sjz.gov.cn/col/1585274801317/index.html";
        Document document = Jsoup.parse(new URL(collectUrl), 50000);
        Elements elements = document.getElementsByClass("list_newslist");
        Elements els = elements.get(0).getElementsByAttributeValue("style", "margin-left:6px;margin-right:6px;border-bottom:dashed 1px #959595;height:50px;");
        String currentTime = DateUtil.now();
        if (0 == times) {
            for (Element el : els) {
                Elements aes = el.getElementsByTag("a");
                Element a = aes.get(0);
                String title = a.html();
                // 首次存入内存列表
                titleList.add(title);
            }
            System.out.println("*** 初始通知列表 [" + currentTime + "] ***");
            titleList.forEach((title) -> {
                System.out.println(title);
            });
        } else {
            boolean newFlag = false;
            for (Element el : els) {
                Elements aes = el.getElementsByTag("a");
                Element a = aes.get(0);
                String title = a.html();
                if (!titleList.contains(title)) {
                    // 通知地址
                    String url = a.attr("href");
                    Elements dates = el.getElementsByAttributeValue("style", "float:right;line-height:50px;font-size:14px;color:#959595");
                    Element dateDiv = dates.get(0);
                    // 通知日期
                    String date = dateDiv.html();
                    // 编辑通知内容
                    String content = "通知已更新，更新内容：（" + date + "）" + title + "[地址：" + url + "]";
                    System.out.println("任务[" + currentTime + "] " + content);
                    // 邮件通知
                    MailUtil.send("283922486@qq.com", "市农业新通知", content, false);
                    // 加入缓存
                    titleList.add(title);
                    // 更改新通知打印状态
                    newFlag = true;
                }
            }
            if (!newFlag) {
                System.out.println("任务[" + currentTime + "] 无新通知");
            }
        }
        times++;
    }


}
