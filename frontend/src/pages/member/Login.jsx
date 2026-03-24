import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Login.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  // 비동기 요청 함수
  const handleSubmit = async(e) => { 
    
    e.preventDefault(); // 기본 동작(페이지 새로고침) 방지
    console.log('로그인시도 유저 정보:', { username, password }); // 로그인 시도 시 최초 출력

    const response = await axios.post('/api/auth/login', { // 백엔드 API 비동기 방식으로 요청보냄 vite.config에서 프록시 설정으로 코드 단순화
      username,
      password
    });

    console.log('로그인 완료, 반환받은 정보: ', response.data);
    
  };

  return (
    <div className="login-container">
      <h2 className="login-title">Login</h2>

      <form onSubmit={handleSubmit}>
        <div className="login-form-group">
          <label htmlFor="username">Username</label>
          <input
            className="login-input"
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
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