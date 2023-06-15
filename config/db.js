const mysql = require('mysql');

const connection = mysql.createConnection({
  host: '34.101.252.113',
  user: 'root',
  password: 'anton12345',
  database: 'speakkuy',
});

connection.connect((err) => {
  if (err) throw err;
  console.log('Connected to MySQL database');
});

module.exports = connection;
