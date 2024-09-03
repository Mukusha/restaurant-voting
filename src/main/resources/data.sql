INSERT INTO USERS ( EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'User_First', 'User_Last', '$2a$10$mx00qos2N2QrpdtImPQ1Le8hzbJjyC1/UGfKhMD9EzBOFb6Gyh9T2'),
       ('admin@javaops.ru', 'Admin_First', 'Admin_Last', '$2a$10$1nrNgRswVYkCrt3DlY58RulST2jcIkzLcW1.1dKh/tdMlP5bds/SW');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);