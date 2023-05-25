// controller/menteeController.js
const Mentee = require('../models/mentee');
const bcrypt = require('bcrypt');
const jwt = require("jsonwebtoken");

const menteeController = {};

menteeController.getAllMentee = (req, res) => {
  Mentee.getAll((err, mentees) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else {
      res.json(mentees);
    }
  });
};

menteeController.getMenteeById = (req, res) => {
  const id = req.params.id;
  Mentee.getById(id, (err, mentee) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else if (mentee) {
      res.json(mentee);
    } else {
      res.status(404).json({ message: 'Mentee not found' });
    }
  });
};

menteeController.createMentee = (req, res) => {
  const data = req.body;
  Mentee.create(data, (err, result) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else {
      res.json({ message: 'Mentee created successfully' });
    }
  });
};

menteeController.updateMentee = (req, res) => {
  const id = req.params.id;
  const data = req.body;
  Mentee.update(id, data, (err, result) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else if (result.affectedRows === 0) {
      res.status(404).json({ message: 'Mentee not found' });
    } else {
      res.json({ message: 'Mentee updated successfully' });
    }
  });
};

menteeController.deleteMentee = (req, res) => {
  const id = req.params.id;
  Mentee.delete(id, (err, result) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else if (result.affectedRows === 0) {
      res.status(404).json({ message: 'Mentee not found' });
    } else {
      res.json({ message: 'Mentee deleted successfully' });
    }
  });
};

menteeController.loginMentee = (req, res) => {
  const { email, password } = req.body;

  Mentee.getByEmailAndPassword(email, password, (err, data) => {
    if (err) {
      res.status(500).json({ status: 'Error', error: err.message });
    } else if (!data) {
      res.status(401).json({ status: 'Error', message: 'Email atau password salah' });
    } else {
      res.json({ code: 200 ,status: 'OK'});
      //res.json({ status: 'OK', message: 'Login berhasil', data);
    }
  });
};


menteeController.loginMenteeAuth = (req, res) => {
  const { email, password } = req.body;

  Mentee.getByEmailAndPassword(email, password, (err, data) => {
    if (err) {
      res.status(500).json({ status: 'Error', error: err.message });
    } else if (!data) {
      res.status(401).json({ code: 201 ,status: 'Error', message: 'Wrong email or password'});
    } else {
      const token = jwt.sign({ data }, "rahasia", { expiresIn: '5m' });
      res.json({ code: 200 ,status: 'OK', token});
      //res.json({ status: 'OK', message: 'Login berhasil', data, token});
    }
  });
};

menteeController.registerMentee = (req, res) => {
  const { full_name, email, password } = req.body;
  Mentee.checkEmailExists(email, (emailExists) => {
    if (emailExists) {
      res.status(400).json({code: 201 ,status: 'Error', message: 'Email already exits'});
    } else {
      Mentee.addMentee(full_name, email, password, () => {
        res.status(200).json({ code: 201 ,status: 'Created'});
      });
    }
  });
};
//profile
menteeController.me = (req, res) => {
  const token = req.headers.authorization.split(' ')[1];
  const tokenData = jwt.decode(token);
  const id = tokenData.data.id;
  Mentee.megetById(id, (err, mentee) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else if (mentee) {
      res.json(mentee[0]);
    } else {
      res.status(404).json({ code: 201 ,status: 'Error',message: 'Mentee not found' });
    }
  });
};
module.exports = menteeController;
