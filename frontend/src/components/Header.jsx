import React from "react";
import "./Header.css";
import {useEffect} from 'react';
import {Link, useNavigate} from "react-router-dom";
import { isTokenValid } from "../utils/auth";

function Header({ isLogin, setIsLogin }){
    const accessToken = localStorage.getItem('accessToken');
    const navigate = useNavigate();
    // 렌더링 시점에 토큰 재검증
    // (탭을 오래 열어뒀다가 돌아온 경우 등 대응)
    useEffect(() => {
      const token = localStorage.getItem('accessToken');
      if (isLogin && !isTokenValid(token)) {
        localStorage.removeItem('accessToken');
        setIsLogin(false);
        navigate('/login', { replace: true });
      }
    }, [isLogin]);

    const handleLogout = () => {
      if (!confirm("정말 로그아웃 하시겠습니까?")) return;

      localStorage.removeItem('accessToken');
      setIsLogin(false);

      navigate('/login', { replace: true });
    };

    return(
    <header className="header">
      <Link to="/posts">
        <h1 className="logo">MyBoard</h1>
      </Link>

      <nav className="nav">
        {isLogin ? <button onClick={handleLogout}>로그아웃</button> : <Link to="/login">로그인</Link>}
      </nav>
    </header>
    );
}

export default Header;