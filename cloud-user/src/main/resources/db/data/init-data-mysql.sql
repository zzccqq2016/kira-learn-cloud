INSERT INTO t_user (id, username, password, status, real_name, email, mobile, position, company, industry, create_at,
                    update_at)
values ('admin', 'admin', '{bcrypt}$2a$10$eeMrQLdbu6HaP.91n40rueeEg5aVjKeeGxm6EG8Xei2fmfDrB7sFy', 1, '管理员', '',
        'admin',
        'kira', '', '', now(), now());
INSERT INTO t_user (id, username, password, status, real_name, email, mobile, position, company, industry, create_at,
                    update_at)
values ('default_user', 'default_user', '{bcrypt}$2a$10$eeMrQLdbu6HaP.91n40rueeEg5aVjKeeGxm6EG8Xei2fmfDrB7sFy', 1,
        '默认用户', '', '13781897597', 'kira', '', '', now(), now());

