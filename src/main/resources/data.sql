insert into users (login_id, nickname, password) values ('user1', '김보연', '1111');
insert into users (login_id, nickname, password) values ('user2', '김용현', '2222');
insert into users (login_id, nickname, password) values ('user3', '김태상', '3333');
insert into users (login_id, nickname, password) values ('user4', '박효정', '4444');
insert into users (login_id, nickname, password) values ('user5', '정예지', '5555');
insert into users (login_id, nickname, password) values ('user6', '김서영', '6666');
insert into users (login_id, nickname, password) values ('user7', '문금미', '7777');
insert into users (login_id, nickname, password) values ('user8', '이예나', '8888');

insert into article (title, content, created_at, like_count, comment_count, user_id)
values ( '첫 게시글', '안녕하세요. 첫 게시글입니다.', CURRENT_TIMESTAMP, 0, 1, 8);

insert into comment (content, created_at, article_id, user_id)
values ('wow', CURRENT_TIMESTAMP, 1, 7);