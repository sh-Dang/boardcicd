import React from "react";
import "./Header.css";
import {Link, useNavigate} from "react-router-dom";

function Header({ isLogin, setIsLogin }){
    const accessToken = localStorage.getItem('accessToken');
    const navigate = useNavigate();
    const handleLogout = () => {
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