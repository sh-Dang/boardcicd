package com.boardcicd.security.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {

    // IP 주소별로 Bucket을 저장하는 Map (동시성 안전한 ConcurrentHashMap 사용)
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {

        // 대역폭 설정: 1분마다 30개의 요청을 허용 (Greedy 방식 = 토큰을 균등하게 채움)
        Bandwidth limit = Bandwidth.builder()
                .capacity(30)               // 버킷 최대 용량: 토큰 30개
                .refillGreedy(30, Duration.ofMinutes(1)) // 1분마다 30개 토큰 재충전
                .build();

        // 위에서 설정한 대역폭 제한으로 새로운 버킷 생성
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // HTTP 요청/응답 타입으로 캐스팅
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 요청자의 IP 주소 추출
        String ip = req.getRemoteAddr();

        // 해당 IP의 버킷이 없으면 새로 생성, 있으면 기존 버킷 반환
        Bucket bucket = cache.computeIfAbsent(ip, k -> createNewBucket());

        // 버킷에서 토큰 1개 소비 시도
        if (bucket.tryConsume(1)) {
            // 토큰 소비 성공 → 요청 정상 통과
            chain.doFilter(request, response);
        } else {
            // 토큰 소비 실패 (버킷이 비어 있음) → 429 Too Many Requests 응답
            res.setStatus(429);
            res.getWriter().write("Too many requests");
        }
    }
}