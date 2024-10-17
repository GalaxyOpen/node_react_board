const express = require('express')
const mysql = requre('mysql2')
const app = express();
const PORT = 5000;

app.use(express.json()); 