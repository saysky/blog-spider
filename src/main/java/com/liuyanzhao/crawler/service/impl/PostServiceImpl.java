package com.liuyanzhao.crawler.service.impl;

import com.liuyanzhao.crawler.dao.PostDao;
import com.liuyanzhao.crawler.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author θ¨ζ
 * @date 2021/6/13 12:22 δΈε
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;


}
