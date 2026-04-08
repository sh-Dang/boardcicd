import { useState } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './pages/members/Login';
import Signup from './pages/members/Signup';
import Posts from './pages/posts/Posts';
import Post from './pages/posts/Post';
import Header from './components/Header';


function App() {
  return (
    <>
      {/* 라우터 시작 : URL에 따라 페이지 컴포넌트를 렌더링 */}
      <BrowserRouter>
        {/* 모든 페이지에서 공통으로 사용하는 헤더 */}
        <Header />

        <Routes>
          {/* 기본 경로 → 로그인 페이지 */}
          <Route path="/" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          
          {/* 게시판 목록 페이지 */}
          <Route path="/posts" element={<Posts />} />

          {/* 게시글(post로 대체) */}
          <Route path="/posts/:id" element={<Post />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App