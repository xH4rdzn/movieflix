CREATE TABLE movie_category
(
    category_id BIGINT NOT NULL,
    movie_id    BIGINT NOT NULL
);

ALTER TABLE movie_category
    ADD CONSTRAINT fk_movcat_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE movie_category
    ADD CONSTRAINT fk_movcat_on_movie FOREIGN KEY (movie_id) REFERENCES movie(id);