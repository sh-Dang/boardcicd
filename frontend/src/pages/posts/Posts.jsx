import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Posts.css';
import API_BASE_URL from '../config/api';

// 전체 게시글 불러오는 페이지
function Posts() {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`${API_BASE_URL}/api/posts`)
      .then((response) => {
        console.log(response);
        setPosts(response.data);
      })
      .catch((error) => {
        console.error('게시글 조회 실패:', error);
      });
  }, []);

  return (
    <div className="posts-container">
      <h2 className="posts-title">자유 게시판
        <Link to="/posts/new"> {/* 새로운 글쓰기 페이지로 링크 */}
          <button type="button">글쓰기</button>
        </Link>
      </h2>

      <table className="posts-table">
        <thead>
          <tr>
            <th>게시번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            {/* <th>조회수</th> */}
          </tr>
        </thead>

        <tbody>
          {posts.map((post) => (
            // 게시글에 해당 게시글로 연결하는 버튼설정
            <tr
              key={post.id}
              onClick={() => navigate(`/posts/${post.id}`)} // naviagte의 동작원리 이해하기
              style={{ cursor: 'pointer' }}
            > 
              <td>{post.id}</td>
              <td>{post.title}</td>
              <td>{post.username}</td>
              <td>{post.createdAt}</td>
              {/* <td>{post.updatedAt}</td> */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Posts;