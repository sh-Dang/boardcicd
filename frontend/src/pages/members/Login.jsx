import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Login.css';
import API_BASE_URL from '../../config/api';

function Login({ setIsLogin }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  // 이미 로그인된 사용자는 로그인 페이지 접근 차단
  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken');

    if (accessToken && accessToken !== 'undefined' && accessToken !== 'null') {
      setIsLogin(true);
      navigate('/posts', { replace: true });
    }
  }, [navigate, setIsLogin]);

  // 비동기 요청 함수
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log('로그인시도 유저 정보:', { email, password });

      const response = await axios.post(`${API_BASE_URL}/api/auth/login`, {
        email,
        password
      });

      const accessToken = response?.data?.data?.accessToken;

      if (!accessToken || accessToken === 'undefined' || accessToken === 'null') {
        console.error('유효하지 않은 accessToken:', response.data);
        alert('로그인 실패: 토큰이 올바르지 않습니다.');
        return;
      }

      localStorage.setItem('accessToken', accessToken);
      console.log('로그인 완료, 반환받은 정보:', accessToken);

      setIsLogin(true);
      navigate('/posts', { replace: true });
    } catch (error) {
      console.error('로그인 실패:', error.response);
      console.error('로그인 실패:', error.response.data);
      // 백엔드 GlobalExceptionHandler에서 보낸 메시지 추출
      // 만약 백엔드 응답이 있다면 (error.response) 그 안의 message를 사용하고, 없으면 기본 메시지를 보여줍니다.
      const errorMessage = error.response?.data?.message || '로그인에 실패했습니다.';
      console.log(errorMessage);
      
      alert(errorMessage);
    }
  };

  return (
    <div className="login-container">
      <h2 className="login-title">Login</h2>

      <form onSubmit={handleSubmit}>
        <div className="login-form-group">
          <label htmlFor="email">Email</label>
          <input
            className="login-input"
            type="text"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className="login-form-group">
          <label htmlFor="password">Password</label>
          <input
            className="login-input"
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <button className="login-button" type="submit">
          Login
        </button>
      </form>

      <Link to="/signup" className="signup-button">회원가입</Link>
    </div>
  );
}

export default Login;