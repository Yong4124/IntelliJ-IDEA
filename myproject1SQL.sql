create	database myproject1;

use myproject1;

select * from user;
select * from comment;
select * from post;
DESC comment;

DELETE FROM comment WHERE post_id IS NULL;
ALTER TABLE comment MODIFY post_id bigint NOT NULL;

SELECT * FROM post WHERE id = 5;

DELETE FROM post;

ALTER TABLE comment DROP FOREIGN KEY `FKs1slvnkuemjsq2kj4h3vhx7i1`;


ALTER TABLE comment
  ADD CONSTRAINT fk_comment_post
  FOREIGN KEY (post_id) REFERENCES post(id)
  ON DELETE CASCADE;

SHOW CREATE TABLE comment;
SHOW CREATE TABLE post_image_paths;

