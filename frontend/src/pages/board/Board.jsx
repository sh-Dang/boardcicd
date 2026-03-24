import React from 'react';
import { Link } from 'react-router-dom';
import './Board.css';

const mockPosts = [
  { id: 1, title: 'First post', author: 'Admin', createdAt: '2024-01-01' },
  { id: 2, title: 'Second post', author: 'User1', createdAt: '2024-01-02' },
  { id: 3, title: 'Third post', author: 'User2', createdAt: '2024-01-03' },
];

function Board() {
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
          {mockPosts.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td>{post.title}</td>
              <td>{post.author}</td>
              <td>{post.createdAt}</td>
              <td>0</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Board;