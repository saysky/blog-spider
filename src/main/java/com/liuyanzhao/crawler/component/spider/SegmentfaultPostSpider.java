package com.liuyanzhao.crawler.component.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingAwareRunnable;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @author 言曌
 * @date 2021/6/13 2:02 下午
 */
@Component
public class SegmentfaultPostSpider {

    @Autowired
    private PageProcessor pageProcessor;

    @Autowired
    private Pipeline pipeline;

    @Autowired
    private Scheduler scheduler;

    public static final String START_URL = "https://segmentfault.com/t/java/blogs";

    public void doCrawler() {
        Spider.create(pageProcessor)
                // 设置自定义的pipeline
                .addPipeline(pipeline)
                // 配置scheduler，指定使用布隆过滤器
                .setScheduler(scheduler)
                // 设置起始URL
                .addUrl(START_URL)
                // 启动爬虫
                .start();
    }
}
