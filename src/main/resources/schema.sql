INSERT INTO home_base (floor, period, created_at)
VALUES (2, 8, CURRENT_TIMESTAMP(6)), (2, 9, CURRENT_TIMESTAMP(6)), (2, 10, CURRENT_TIMESTAMP(6)), (2, 11, CURRENT_TIMESTAMP(6)),
       (3, 8, CURRENT_TIMESTAMP(6)), (3, 9, CURRENT_TIMESTAMP(6)), (3, 10, CURRENT_TIMESTAMP(6)), (3, 11, CURRENT_TIMESTAMP(6)),
       (4, 8, CURRENT_TIMESTAMP(6)), (4, 9, CURRENT_TIMESTAMP(6)), (4, 10, CURRENT_TIMESTAMP(6)), (4, 11, CURRENT_TIMESTAMP(6));

INSERT INTO User (id, email, name, grade, class_num, number, profile_image_url, reservation_id, created_at)
VALUES
    (UNHEX(REPLACE(UUID(), '-', '')), 'user1@example.com', 'User1', 1, 1, 1, '', NULL, CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), 'user2@example.com', 'User2', 2, 2, 2, '', NULL, CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE(UUID(), '-', '')), 'user3@example.com', 'User3', 3, 3, 3, '', NULL, CURRENT_TIMESTAMP(6));

