package com.liuyanzhao.crawler.controller;

import com.liuyanzhao.crawler.component.spider.SegmentfaultPostSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章抓取控制器
 *
 * @author 言曌
 * @date 2021/6/13 2:34 下午
 */
@RestController
public class PostCrawlerController {

    @Autowired
    private SegmentfaultPostSpider spider;

    /**
     * segmentfault文章抓取
     *
     * @return
     */
    @GetMapping("/segmentfaultPostCrawler")
    public String segmentfaultPostCrawler() {
        spider.doCrawler();
        return "start ok!";
    }
}
