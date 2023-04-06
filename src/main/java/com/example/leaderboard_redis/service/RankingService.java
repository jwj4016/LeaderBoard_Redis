package com.example.leaderboard_redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RankingService {
    private final StringRedisTemplate redisTemplate;

    private static final String LEADERBOARD_KEY = "leaderBoard";

    public boolean setUserScore(String userId, int score) {
        ZSetOperations<String, String> zSetOPs = redisTemplate.opsForZSet();
        zSetOPs.add(LEADERBOARD_KEY, userId, score);

        return true;
    }

    public Long getUserRanking(String userId) {
        ZSetOperations<String, String> zSetOPs = redisTemplate.opsForZSet();
        return zSetOPs.reverseRank(LEADERBOARD_KEY, userId);
    }

    public List<String> getTopRank(int limit) {
        ZSetOperations<String, String> zSetOPs = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOPs.reverseRange(LEADERBOARD_KEY, 0, limit - 1);

        return new ArrayList<>(rangeSet);
    }
}
