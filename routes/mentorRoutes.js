const express = require('express');
const cors = require('../middleware/cors');
const router = express.Router();
const authallmentee = require('../middleware/authallmentee');
const mentorController = require('../controller/mentorController');

router.use(cors);
//get all recommended mentors
router.get('/', authallmentee, mentorController.getAll);
//select all recommended mentors
router.post('/select', authallmentee, mentorController.selectMentor);
//select all recommended mentors
router.get('/my-mentors', authallmentee, mentorController.getAllMyMentor);

module.exports = router;