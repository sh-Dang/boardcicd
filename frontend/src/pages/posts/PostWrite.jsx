import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './PostWrite.css';

// 게시글 작성 페이지
function PostWrite() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 공백 유효성 검사
    if (!title.trim() || !content.trim()) {
      alert('제목과 내용을 입력하세요.');
      return;
    }

    try {
      await axios.post('http://158.180.82.183:8888/api/posts', {
        title,
        content,
      });

      alert('게시글이 등록되었습니다.');
      navigate('/posts'); // 목록으로 이동
    } catch (error) {
      console.error('게시글 등록 실패:', error);
      alert('등록 실패');
    }
  };

  return (
    <div className="postwrite-container">
      <h2>게시글 작성</h2>

      <form onSubmit={handleSubmit} className="postwrite-form">
        <div className="form-group">
          <label>제목</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="제목을 입력하세요"
          />
        </div>

        <div className="form-group">
          <label>내용</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="내용을 입력하세요"
            rows={10}
          />
        </div>

        <div className="form-actions">
          <button type="submit">등록</button>
          <button type="button" onClick={() => navigate('/posts')}>
            취소
          </button>
        </div>
      </form>
    </div>
  );
}

export default PostWrite;