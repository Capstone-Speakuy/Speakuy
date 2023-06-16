const Mentee = require("../models/mentee");
const jwt = require("jsonwebtoken");
const Mentor = require("../models/mentor");

const mentorController = {};

mentorController.getAll = (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const tokenData = jwt.decode(token);
  const id = tokenData.data.id;
  Mentee.megetById(id, (err, mentees) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else if (mentees) {
      const mentee = mentees[0];
      Mentor.getRecommendations(
        mentee["id"],
        mentee["description"],
        (error, data) => {
          if (error) {
            res.status(500).json({ error: err });
          } else if (data) {
            res.status(200).json({
              code: 200,
              status: "OK",
              data: data,
            });
          }
        }
      );
    } else {
      res.status(404).json({
        code: 201,
        status: "Error",
        message: "Mentee not found",
      });
    }
  });
};

mentorController.selectMentor = (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const tokenData = jwt.decode(token);
  const id = tokenData.data.id;
  const { mentor_id } = req.body;
  Mentor.assignToMentee(id, mentor_id, () => {
    res.status(200).json({ code: 200, status: "OK" });
  });
};

mentorController.getAllMyMentor = (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const tokenData = jwt.decode(token);
  const id = tokenData.data.id;
  Mentor.getAllMyMentor(id, (err, mentors) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else {
      res.json({
        code: 200,
        status: 'OK',
        data: mentors
      });
    }
  });
};

module.exports = mentorController;
