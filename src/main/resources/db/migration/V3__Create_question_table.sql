CREATE TABLE question
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(50),
  description TEXT,
  gmt_create BIGINT,
  gmt_modified BIGINT,
  creator LONG,
  comment_count INT DEFAULT 0,
  like_count INT DEFAULT 0,
  tag VARCHAR(200)
);