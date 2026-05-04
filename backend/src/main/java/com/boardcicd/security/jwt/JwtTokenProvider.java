package com.boardcicd.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

// JWT를 관리하는 컴포넌트
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * 서명에 사용할 HMAC-SHA 기반의 SecretKey 객체를 생성하여 반환
     * - secretKey 문자열을 UTF-8 바이트 배열로 변환 후 키 생성
     *
     * @return HMAC-SHA 알고리즘용 Key 객체
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 사용자 이메일(email)을 기반으로 JWT 액세스 토큰을 생성
     * <p>
     * 토큰 구성:
     *  - subject  : 사용자 식별자 (email)
     *  - issuedAt : 토큰 발급 시각
     *  - expiration : 현재 시각 + 설정된 만료 시간
     *  - 서명 알고리즘 : HMAC-SHA (키 길이에 따라 자동 결정)
     *
     * @param email 토큰에 담을 사용자 이메일
     * @return 서명된 JWT 문자열
     */
    public String createToken(String email, Long memberId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(email)
                .claim("memberId", memberId)  //
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * JWT 토큰을 파싱하여 사용자명(username)을 추출
     * - 서명 검증을 통해 토큰 위변조 여부도 함께 확인
     * - 토큰이 유효하지 않으면 예외 발생 (호출부에서 처리 필요)
     *
     * @param token 파싱할 JWT 문자열
     * @return 토큰의 subject 클레임에 저장된 사용자명
     */
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * JWT 토큰의 유효성을 검증
     * 검증 항목:
     *  - 서명 일치 여부
     *  - 토큰 만료 여부
     *  - 토큰 형식(포맷) 정상 여부
     *
     * @param token 검증할 JWT 문자열
     * @return 유효한 토큰이면 true, 그 외 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 유저의 PK를 조회하기 위한 메서드
    public Long getMemberId(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("memberId", Long.class);  // 클레임에서 꺼내기
    }
}