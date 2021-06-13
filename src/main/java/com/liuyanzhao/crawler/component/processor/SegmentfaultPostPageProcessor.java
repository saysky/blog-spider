package com.liuyanzhao.crawler.component.processor;

import com.liuyanzhao.crawler.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 文章页面解析
 *
 * @author 言曌
 * @date 2021/6/13 12:58 下午
 */
@Service
public class SegmentfaultPostPageProcessor implements PageProcessor {

    @Autowired
    private Site site;

    /**
     * 页面分析
     * 使用原生api的jsoup
     *
     * @param page
     */
    public void process(Page page) {
        Html html = page.getHtml();

        // 解析列表页面的所有链接
        List<Selectable> nodes = html.css("h2.blog-type-common").nodes();
        if (nodes.size() > 0) {
            List<String> links = html.css("h2.blog-type-common").links().all();
            System.out.println(links);
            page.addTargetRequests(links);
            // 解析下一页的URL
            List<Selectable> pageNode = html.css("ul.pagination > li.next").nodes();
            if (pageNode.size() > 0) {
                String nextPageUrl = pageNode.get(0).links().get();
                page.addTargetRequest(nextPageUrl);
                System.out.println(pageNode);
            }
            // 不向pipeline传递数据
            page.getResultItems().setSkip(true);
        }
        // 解析详情页面
        else {
            // 解析文章标题
            String title = html.css("div.card-body>h1>a", "text").get();
            // 解析文章内容
            String content = html.css("article.article-content").get();
            // 解析文章作者用户名
            String author = html.css("strong.align-self-center", "text").get();
            // 页面URL
            String url = page.getUrl().get();
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setAuthor(author);
            post.setSourceUrl(url);
            // 把数据传递给pipeline，由pipeline保存到磁盘
            page.putField("post", post);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
