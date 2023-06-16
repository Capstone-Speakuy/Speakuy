const fetch = require('node-fetch');
const connection = require('../config/db');

const Mentor = {};

Mentor.getRecommendations = async (userId, userText, callback) => {
  if (userText == null) {
    throw new Error("Mentee belum mengisi survey");
  }
  try {
    const response = await fetch("https://bismillah-vvtmzrrdvq-ue.a.run.app/get-mentors", {
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

module.exports = Mentor;
