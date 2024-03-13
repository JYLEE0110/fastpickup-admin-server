package org.fktm.fastpickup.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReviewServiceTests {

    @Autowired(required = false)
    private ReviewService reviewService;
    
}
