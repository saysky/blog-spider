package com.liuyanzhao.crawler.dao;

import com.liuyanzhao.crawler.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章数据访问层
 *
 * @author 言曌
 * @date 2021/6/13 12:21 下午
 */
@Repository
public interface PostDao extends JpaRepository<Post, String> {

    /**
     * 根据来源URL查询
     *
     * @param sourceUrl
     * @return
     */
    List<Post> findBySourceUrl(String sourceUrl);
}
