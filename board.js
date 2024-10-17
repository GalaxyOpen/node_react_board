// Board.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Board() {
  const [posts, setPosts] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  // 게시물 목록 가져오기
  useEffect(() => {
    axios.get('/api/posts').then(response => setPosts(response.data));
  }, []);

  // 게시물 작성하기
  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('/api/posts', { title, content }).then(response => {
      setPosts([...posts, response.data]);
      setTitle('');
      setContent('');
    });
  };

  return (
    <div>
      <h1>게시판</h1>
      <form onSubmit={handleSubmit}>
        <input 
          type="text" 
          value={title} 
          onChange={(e) => setTitle(e.target.value)} 
          placeholder="제목" 
          required 
        />
        <textarea 
          value={content} 
          onChange={(e) => setContent(e.target.value)} 
          placeholder="내용" 
          required 
        />
        <button type="submit">작성하기</button>
      </form>
      <h2>게시물 목록</h2>
      <ul>
        {posts.map(post => (
          <li key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.content}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Board;
