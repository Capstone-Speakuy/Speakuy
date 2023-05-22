// index.js or app.js
const express = require('express');
const menteeRoutes = require('./routes/menteeRoutes');



const app = express();
const port = 3000;

app.use(express.json());

app.use('/mentee', menteeRoutes);

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
