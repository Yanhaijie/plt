var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/sharesms', function(req, res, next) {
  res.render('sharesms', { title: 'supermoneyshop' });
});

module.exports = router;
