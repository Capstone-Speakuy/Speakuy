// index.js or app.js
const express = require('express');
const menteeRoutes = require('./routes/menteeRoutes');
const mentorRoutes = require('./routes/mentorRoutes');

const app = express();
const PORT = process.env.PORT || 3000

app.use(express.json());

app.use('/mentee', menteeRoutes);
app.use('/mentors', mentorRoutes);
app.get("/", (req, res, next) => {
  res.json("API is online");
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
