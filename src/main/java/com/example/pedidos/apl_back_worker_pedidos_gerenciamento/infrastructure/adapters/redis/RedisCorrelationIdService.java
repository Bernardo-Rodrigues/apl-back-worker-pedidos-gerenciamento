package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.redis;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.services.CorrelationIdService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisCorrelationIdService implements CorrelationIdService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${ttl.redis}")
    private long ttlInSeconds;

    public RedisCorrelationIdService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isDuplicate(String correlationId) {
        return redisTemplate.hasKey(correlationId);
    }

    public void registerCorrelationId(String correlationId) {
        redisTemplate.opsForValue().set(correlationId, "true", Duration.ofSeconds(ttlInSeconds));
    }
}
