import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Board.css';

function Board() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    axios.get('/api/posts')
      .then((response) => {
        setPosts(response.data);
      })
      .catch((error) => {
        console.error('게시글 조회 실패:', error);
      });
  }, []);

  return (
    <div className="board-container">
      <h2 className="board-title">Board</h2>

      <Link to="/new-post">
        <button className="board-btn">New Post</button>
      </Link>

      <table className="board-table">
        <thead>
          <tr>
            <th>게시번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
        </thead>

        <tbody>
          {posts.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td>{post.title}</td>
              <td>{post.title}</td>
              <td>{post.author}</td>
              <td>{post.createdAt}</td>
              <td>{post.viewCount}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Board;