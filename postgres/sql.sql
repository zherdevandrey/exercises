CREATE TABLE PRODUCTS (
                          ID SERIAL PRIMARY KEY,
                          CURRENT_MOOD MOOD,
                          NAME text,
                          ATTRIBUTES json
);

INSERT INTO PRODUCTS (CURRENT_MOOD, NAME, ATTRIBUTES) VALUES
    ('sad', 'Smartphone', '{"brand" : "BrandX", "features": ["touchScreen", "Camera"]}');

SELECT ATTRIBUTES ->> 'brand' as brand FROM PRODUCTS WHERE NAME = 'Smartphone';

UPDATE PRODUCTS
SET ATTRIBUTES = jsonb_set(attributes, '{releaseYear}', '"2023"') WHERE NAME = 'Smartphone';

UPDATE PRODUCTS
SET attributes = attributes || '{"brand": "BrandY", "price" : 123}'
WHERE NAME = 'Smartphone';

SELECT * FROM PRODUCTS
WHERE ATTRIBUTES ? 'brand';

SELECT * FROM PRODUCTS WHERE ATTRIBUTES @> '{"features": ["Camera"]}';

--INDEX FOR JSON FIELD
CREATE INDEX idx_jsonb_brand ON products ((attributes ->> 'brand'));
SELECT ATTRIBUTES ->> 'brand' as brand FROM PRODUCTS WHERE ATTRIBUTES ->>  'brand' = 'BrandY';

CREATE TYPE mood AS ENUM ('sad', 'ok', 'happy');

ALTER  TYPE  mood ADD VALUE 'funny';

--INTERVALS
SELECT NOW() + INTERVAL '1 year 2 month 1 day';

SELECT date_trunc('year', CURRENT_TIMESTAMP);