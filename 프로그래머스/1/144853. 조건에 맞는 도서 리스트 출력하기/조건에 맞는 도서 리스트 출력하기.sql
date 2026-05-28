-- 코드를 입력하세요
SELECT book_id, published_date FROM book
WHERE published_date >= '2021-01-01'
AND published_date < '2022-01-01'
AND category = '인문'
ORDER BY published_date;