package com.liuyanzhao.crawler.entity;

import com.liuyanzhao.crawler.util.SnowFlakeUtil;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @author 言曌
 * @date 2021/6/13 12:12 下午
 */
@Table(name = "t_post")
@Entity
@Data
public class Post implements Serializable {

    /**
     * ID，雪花算法生成ID，也可以直接用自增id
     */
    @Id
    @Column(name = "id")
    private String id = SnowFlakeUtil.nextId().toString();

    /**
     * 作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;

    /**
     * 摘要
     */
    @Column(name = "summary")
    private String summary;

    /**
     * 来源站点
     */
    @Column(name = "source_site")
    private String sourceSite;

    /**
     * 来源URL
     */
    @Column(name = "source_url")
    private String sourceUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
