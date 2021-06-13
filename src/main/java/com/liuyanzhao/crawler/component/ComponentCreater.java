package com.liuyanzhao.crawler.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * 一些公共的bean
 *
 * @author 言曌
 * @date 2021/6/13 2:06 下午
 */
@Configuration
public class ComponentCreater {


    @Bean
    public Scheduler createScheduler() {
        QueueScheduler scheduler = new QueueScheduler();
        // 设置布隆过滤器
        scheduler.setDuplicateRemover(new BloomFilterDuplicateRemover(10000000));
        return scheduler;
    }

    @Bean
    public Site site() {
        return Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    }


}
