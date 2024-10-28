const express = require('express');
const mysql = require('mysql2');
const app = express();
const PORT = process.env.PORT || 5500;
const cors = require('cors');

app.use(express.json());
app.use(cors());

app.listen(PORT, ()=>{
  console.log(`Server running on port ${PORT}`);
});

// MySQL 연결 설정
const db = mysql.createConnection({
  host: 'localhost',
  user: 'node_react_board', // MySQL 사용자 이름
  password: '1234', // MySQL 비밀번호
  database: 'board_db' // 위에서 만든 데이터베이스 이름
});

db.connect((err) => {
  if (err) {
    console.error('MySQL 연결 실패:', err);
  } else {
    console.log('MySQL 연결 성공');
  }
});

app.get('/api', (req, res) => {
    res.send('Hello from the server!');
  });
  
  app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
  });

// 게시물 목록 가져오기
app.get('/api/posts', (req, res) => {
  db.query('SELECT * FROM posts', (err, results) => {
    if (err) throw err;
    res.json(results);
  });
});

// 게시물 작성하기
app.post('/api/posts', (req, res) => {
  const { title, content } = req.body;
  const query = 'INSERT INTO posts (title, content) VALUES (?, ?)';
  db.query(query, [title, content], (err, result) => {
    if (err) throw err;
    res.json({ id: result.insertId, title, content });
  });
});

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
