import React from "react";
import "./Header.css";
import {Link} from "react-router-dom";

function Header(){
    return(
    <header className="header">
      <Link to="/posts">
        <h1 className="logo">MyBoard</h1>
      </Link>

      <nav className="nav">
        <a href="/posts">게시판</a>
        <a href="/">로그인</a>
        <a href="/signup">회원가입</a>
      </nav>
    </header>
    );
}

export default Header;