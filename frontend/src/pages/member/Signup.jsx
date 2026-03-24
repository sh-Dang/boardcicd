import React, { useState } from 'react';
import axios from 'axios';
import './Signup.css';

function Signup() {
  const [form, setForm] = useState({
    username: '',
    password: '',
    confirmPassword: ''
  });

  const [error, setError] = useState('');

  // 입력값 변경
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: value
    });
  };

  // 회원가입 요청 에외처리
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.username || !form.password) {
      setError('모든 값을 입력하세요.');
      return;
    }

    if (form.password !== form.confirmPassword) {
      setError('비밀번호가 일치하지 않습니다.');
      return;
    }

    try {
      const response = await axios.post('/api/auth/signup', {
        username: form.username,
        password: form.password
      });

      console.log('회원가입 성공:', response.data);
      alert('회원가입 완료! 로그인 해주세요.');

    } catch (err) {
      console.error(err);
      setError('회원가입 실패');
    }
  };

  return (
    <div className="signup-container">
      <h2 className="signup-title">Sign Up</h2>

      <form onSubmit={handleSubmit}>
        <div className="signup-form-group">
          <label>Username</label>
          <input
            className="signup-input"
            type="text"
            name="username"
            value={form.username}
            onChange={handleChange}
          />
        </div>

        <div className="signup-form-group">
          <label>Password</label>
          <input
            className="signup-input"
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
          />
        </div>

        <div className="signup-form-group">
          <label>Confirm Password</label>
          <input
            className="signup-input"
            type="password"
            name="confirmPassword"
            value={form.confirmPassword}
            onChange={handleChange}
          />
        </div>

        {error && <p className="signup-error">{error}</p>}

        <button className="signup-button" type="submit">
          회원가입
        </button>
      </form>
    </div>
  );
}

export default Signup;