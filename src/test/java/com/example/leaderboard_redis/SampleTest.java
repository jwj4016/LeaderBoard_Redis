package com.example.leaderboard_redis;

import com.example.leaderboard_redis.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class SampleTest {
    @Autowired
    private RankingService rankingService;

    @Test
    void performance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000);
            list.add(score);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Collections.sort(list);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Test
    void insertScore() {
        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000);
            String userId = "user" + i;
            rankingService.setUserScore(userId, score);
        }
    }

    @Test
    void getRanks() {
        rankingService.getTopRank(1);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long userRank = rankingService.getUserRanking("user100");
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds() + ", rank : " + userRank);

        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        List<String> topRankers = rankingService.getTopRank(10);
        stopWatch2.stop();
        System.out.println(stopWatch2.getTotalTimeSeconds() + ", rankers : " + topRankers);


    }
}
