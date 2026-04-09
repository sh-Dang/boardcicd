import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Posts.css';

function Posts() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    axios.get('/api/posts')
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
      <h2 className="posts-title">자유 게시판<button>글쓰기</button></h2>

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
            <tr key={post.id}>
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