import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams, Link } from 'react-router-dom';
import './PostEdit.css';

function PostEdit(){
    const naviagte = useNavigate();
    const {id} = useParams(); // 변수명은 App.jsx에서 매핑한다.
    const [post, setPost] = useState({
        title : '',
        content : '',
    });

    // 선택된 게시글 조회
    useEffect(() => {
        axios.get(`http://158.180.82.183:8888/api/posts/${id}`)
        .then((response) => {
            setPost({
            title: response.data.title,
            content: response.data.content,
            });
        })
        .catch((error) => {
            console.error('게시글 조회 실패:', error);
            alert('게시글 정보를 불러오지 못했습니다.');
        });
    }, [id]);

    // input 변경 처리
    const handleChange = (e) => {
        const { name, value } = e.target;

        setPost((prev) => ({
        ...prev,
        [name]: value,
        }));
    };

    // 게시글 수정 요청
    const handleSubmit = (e) => {
        e.preventDefault();

        axios.patch(`http://158.180.82.183:8888/api/posts/${id}`, post)
        .then(()=>{
            alert('게시글이 수정됐습니다.');
            naviagte(`/posts/${id}`);
        });
    }

    return(
    <div className="post-edit-container">
      <h1>게시글 수정</h1>

      <form onSubmit={handleSubmit} className="post-edit-form">
        <input
          type="text"
          name="title"
          value={post.title}
          onChange={handleChange}
          placeholder="제목을 입력하세요"
        />

        <textarea
          name="content"
          value={post.content}
          onChange={handleChange}
          placeholder="내용을 입력하세요"
          rows="10"
        />

        <button type="submit">수정 완료</button>
        <Link to={`/posts/${id}`}> {/* 수정을 취소 */}
            <button type="button">수정 취소</button>
        </Link>
      </form>
    </div>
    )
}

export default PostEdit;