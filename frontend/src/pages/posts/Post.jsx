import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, data } from 'react-router-dom';
import axios from 'axios';
import './Post.css';
import API_BASE_URL from '../../config/api';

// 한 개의 게시글
function Post() {
    const { id } = useParams(); // /posts/:id 에서 id 추출
    const [post, setPost] = useState(null);
    const navigate = useNavigate();
    const token = localStorage.getItem("accessToken"); // 인증에 사용할 토큰 추출

    const handleDelete = () => {
        if (!window.confirm('정말 삭제하시겠습니까?')) return;

        axios.delete(`${API_BASE_URL}/api/posts/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(() => {
            alert('삭제 완료');
            navigate('/posts'); // 목록으로 이동
        })
        .catch((err) => {
            console.error('삭제 실패:', err);
        });
    };

    useEffect(()=>{
        axios.get(`${API_BASE_URL}/api/posts/${id}`)
        .then((res) => {
            console.log(res.data);
            setPost(res.data);
        })
        .catch((err) => {
                console.error('게시글 조회 실패:', err);
        })
    }, [id]); // Id바뀔떄만 실행



    // 로딩중일때 표시할 화면
    if (!post) return <div>로딩중...</div>;

    return(
        <div className='post-container'>
            <div className='post-card'>
                
                <h1 className='post-title'>{post.data.title}</h1>

                <div className='post-meta'>
                    <span>작성자: {post.data.username || '익명'}</span>
                    <span>작성일: {post.data.createdAt || '날짜 없음'}</span>
                </div>

                <hr />

                <div className='post-content'>
                    {post.data.content}
                </div>

            </div>

        {/* 버튼 영역 분리 */}
        <div className='post-actions'>
            <button
                className='edit-btn'
                onClick={() => navigate(`/posts/${id}/edit`)}
            >
                게시글 수정
            </button>

            <button
                className='delete-btn'
                onClick={handleDelete}
            >
                게시글 삭제
            </button>
        </div>
        </div>
        
    )
}

export default Post;