import { useState } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './pages/Login';
import Board from './pages/Board';
import BoardDetail from './pages/BoardDetail';
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

          {/* 게시판 목록 페이지 */}
          <Route path="/board" element={<Board />} />

          {/* 게시글 상세 페이지 (동적 파라미터 :id 사용) */}
          <Route path="/board/:id" element={<BoardDetail />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App