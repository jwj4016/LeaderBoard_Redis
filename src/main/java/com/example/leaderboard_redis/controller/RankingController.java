package com.example.leaderboard_redis.controller;

import com.example.leaderboard_redis.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankingController {
    private final RankingService rankingService;

    @PostMapping("/score/{userId}/{score}")
    public Boolean setScore(@PathVariable("userId") String userId, @PathVariable("score") int score) {
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/score/{userId}")
    public Long getUserRank(@PathVariable("userId") String userId) {
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/score/top")
    public List<String> getTopRanks() {
        return rankingService.getTopRank(3);
    }
}
