INSERT INTO home_base (floor, period, created_at)
SELECT * FROM (
                  SELECT 2 AS floor, 8 AS period, CURRENT_TIMESTAMP(6) AS created_at UNION ALL
                  SELECT 2, 9, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 2, 10, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 2, 11, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 3, 8, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 3, 9, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 3, 10, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 3, 11, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 4, 8, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 4, 9, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 4, 10, CURRENT_TIMESTAMP(6) UNION ALL
                  SELECT 4, 11, CURRENT_TIMESTAMP(6)
              ) AS temp
WHERE NOT EXISTS (
        SELECT 1 FROM home_base WHERE floor = temp.floor AND period = temp.period
    );



INSERT INTO User (id, email, name, grade, class_num, number, profile_image_url, reservation_id, created_at)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')), 'user1@example.com', 'User1', 1, 1, 1, '', NULL, CURRENT_TIMESTAMP(6)
FROM
    dual
WHERE
    NOT EXISTS (
            SELECT * FROM User WHERE email = 'user1@example.com'
        )
UNION ALL
SELECT
    UNHEX(REPLACE(UUID(), '-', '')), 'user2@example.com', 'User2', 2, 2, 2, '', NULL, CURRENT_TIMESTAMP(6)
FROM
    dual
WHERE
    NOT EXISTS (
            SELECT * FROM User WHERE email = 'user2@example.com'
        )
UNION ALL
SELECT
    UNHEX(REPLACE(UUID(), '-', '')), 'user3@example.com', 'User3', 3, 3, 3, '', NULL, CURRENT_TIMESTAMP(6)
FROM
    dual
WHERE
    NOT EXISTS (
            SELECT * FROM User WHERE email = 'user3@example.com'
        );
