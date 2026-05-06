// JWT payload 디코딩 후 만료 여부 확인
export function isTokenValid(token) {
  if (!token || token === "undefined" || token === "null") return false;

  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const now = Math.floor(Date.now() / 1000); // 현재 시각 (초 단위)
    return payload.exp > now;
  } catch (e) {
    // 디코딩 실패 = 잘못된 토큰
    return false;
  }
}