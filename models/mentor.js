const fetch = require('node-fetch');

const Mentor = {};

Mentor.getRecommendations = async (userId, userText, callback) => {
  if (userText == null) {
    throw new Error("Mentee belum mengisi survey");
  }
  try {
    const response = await fetch("http://127.0.0.1:5000/get-mentors", {
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

module.exports = Mentor;
