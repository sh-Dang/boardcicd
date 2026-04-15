import { useState } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './pages/members/Login';
import Signup from './pages/members/Signup';
import Posts from './pages/posts/Posts';
import Post from './pages/posts/Post';
import PostWrite from './pages/posts/PostWrite';
import Header from './components/Header';
import PostEdit from './pages/posts/PostEdit';



function App() {
  const [isLogin, setIsLogin] = useState(() => {
  const token = localStorage.getItem('accessToken');
    // 토큰이 null이나 undefined가 아니고, 실제 값이 들어있을 때만 true
    return !!token && token !== "undefined" && token !== "null"
  });

  return (
    <>
      {/* 라우터 시작 : URL에 따라 페이지 컴포넌트를 렌더링 */}
      <BrowserRouter>
        {/* 모든 페이지에서 공통으로 사용하는 헤더 */}
        {/* 로그인 시 라우팅 상태반영을 위해 isLogin 플래그 관리 */}
        <Header isLogin={isLogin} setIsLogin={setIsLogin} />

        <Routes>
          {/* 기본 경로 → 로그인 페이지 */}
          <Route path="/login" element={<Login setIsLogin={setIsLogin} />}  />
          <Route path="/signup" element={<Signup />} />
          
          {/* 게시판 목록 페이지 */}
          <Route path="/posts" element={<Posts />} />

          {/* 게시글(post로 대체) */}
          <Route path="/posts/:id" element={<Post />} />

          {/* 게시판 글쓰기 페이지 */}
          <Route path="/posts/new" element={<PostWrite />} />

          {/* 게시판 편집 페이지 */}
          <Route path="/posts/:id/edit" element={<PostEdit />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App