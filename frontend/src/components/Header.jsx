import React from "react";
import "./Header.css";

function Header(){
    return(
    <header className="header">
      <h1 className="logo">MyBoard</h1>

      <nav className="nav">
        <a href="/posts">게시판</a>
        <a href="/">로그인</a>
        <a href="/signup">회원가입</a>
      </nav>
    </header>
    );
}

export default Header;