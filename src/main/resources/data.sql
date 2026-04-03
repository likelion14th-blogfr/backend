insert into users (login_id, nickname, password) values ('user1', '전수아', '1234');
insert into users (login_id, nickname, password) values ('user2', '유지오', '1234');

insert into article (id, title, content, created_at, like_count, comment_count, user_id)
values (1, '첫 게시글', '안녕하세요. 첫 게시글입니다.', CURRENT_TIMESTAMP, 0, 1, 1);

insert into comment (id, content, created_at, article_id, user_id)
values (1, 'wow', CURRENT_TIMESTAMP, 1, 1);