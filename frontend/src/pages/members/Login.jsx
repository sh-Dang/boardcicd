import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Login.css';

function Login({ setIsLogin }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  // 이미 로그인된 사용자는 로그인 페이지 접근 차단
  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if (token) {
      navigate('/posts', { replace: true });
    }
  }, [navigate]);

  // 비동기 요청 함수
  const handleSubmit = async(e) => { 
    e.preventDefault(); // 기본 동작(페이지 새로고침) 
    
    try {
      console.log('로그인시도 유저 정보:', { email, password });

      const response = await axios.post('/api/auth/login', {
        email,
        password
      });
      console.log('로그인 완료, 반환받은 정보:', response.data); // 백엔드에서는 토큰만 보내줌

      const { accessToken } = response.data;

      localStorage.setItem('accessToken', accessToken);

      console.log('accessToken 저장 완료');
      setIsLogin(true); // 로그아웃 버튼을 바로 렌더링하기위한 플래그
      navigate('/posts'); // 로그인 성공시 게시글이 있는 페이지로
    } catch (error) {
      console.error('로그인 실패:', error);
    };

  };

  return (
    <div className="login-container">
      <h2 className="login-title">Login</h2>

      <form onSubmit={handleSubmit}>
        <div className="login-form-group">
          <label htmlFor="username">Email</label>
          <input
            className="login-input"
            type="text"
            id="username"
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