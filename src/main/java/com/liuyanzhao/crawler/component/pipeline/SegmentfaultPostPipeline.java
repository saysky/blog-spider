package com.liuyanzhao.crawler.component.pipeline;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.http.HtmlUtil;
import com.liuyanzhao.crawler.dao.PostDao;
import com.liuyanzhao.crawler.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * 采集segmentfault的文章
 *
 * @author 言曌
 * @date 2021/6/13 1:59 下午
 */
@Component
public class SegmentfaultPostPipeline implements Pipeline {

    @Autowired
    private PostDao postDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void process(ResultItems resultItems, Task task) {
        Post post = resultItems.get("post");
        // 摘要，截取200个字符
        String summaryText = HtmlUtil.cleanHtmlTag(post.getContent());
        if (summaryText.length() > 200) {
            summaryText = summaryText.substring(0, 200) + "...";
        }
        post.setSummary(summaryText);
        post.setCreateTime(new Date());
        post.setSourceSite("segmentfault");
        // 把数据存储到数据库
        // 判断URL是否已经入库，如果不存在再入库
        List<Post> postList = postDao.findBySourceUrl(post.getSourceUrl());
        if (ListUtils.isEmpty(postList)) {
            postDao.save(post);
        }
    }
}
