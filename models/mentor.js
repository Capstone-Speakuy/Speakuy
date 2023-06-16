const fetch = require('node-fetch');
const connection = require('../config/db');

const Mentor = {};

Mentor.getRecommendations = async (userId, userText, callback) => {
  if (userText == null) {
    throw new Error("Mentee belum mengisi survey");
  }
  try {
    const response = await fetch("https://bismillahbanget-vvtmzrrdvq-ue.a.run.app//get-mentors", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        user_id: userId,
        user_text: userText,
      }),
    });
    const result = await response.json();
    callback(null, result);
  } catch (error) {
    console.log(error);
    callback(error, null);
  }
};

Mentor.assignToMentee = (id, mentor_id, callback) => {
  const query = `INSERT INTO mentor_mentee (mentee_id, mentor_id) VALUES (?, ?)`;
  connection.query(
    query,
    [id, mentor_id],
    (err, results) => {
      if (err) throw err;
      callback();
    }
  )
}

Mentor.getAllMyMentor = (userId, callback) => {
  const query = "SELECT ms.id, ms.name, ms.description FROM mentor_mentee mm JOIN mentors ms ON ms.id = mm.mentor_id JOIN mentee mt ON mt.id = mm.mentee_id WHERE mm.mentee_id = ?";
  connection.query(
    query,
    [userId],
    callback
  )
}

module.exports = Mentor;
