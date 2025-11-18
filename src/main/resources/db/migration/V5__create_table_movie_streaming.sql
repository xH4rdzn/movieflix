CREATE TABLE movie_streaming
(
    movie_id     BIGINT NOT NULL,
    streaming_id BIGINT NOT NULL
);

ALTER TABLE movie_streaming
    ADD CONSTRAINT fk_movstr_on_movie FOREIGN KEY (movie_id) REFERENCES movie(id);

ALTER TABLE movie_streaming
    ADD CONSTRAINT fk_movstr_on_streaming FOREIGN KEY (streaming_id) REFERENCES streaming(id);