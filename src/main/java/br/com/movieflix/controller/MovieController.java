package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping
  public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest request) {
    Movie savedMovie = movieService.createMovie(MovieMapper.toMovie(request));

    return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(savedMovie));
  }


  @GetMapping
  public ResponseEntity<List<MovieResponse>> getAllMovies() {


    return ResponseEntity.ok(movieService.listAll().stream()
        .map(MovieMapper::toMovieResponse)
        .toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id) {
    return movieService.getMovie(id)
        .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody MovieRequest request) {

    return movieService.updateMovie(id, MovieMapper.toMovie(request))
        .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
        .orElse(ResponseEntity.notFound().build());

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    Optional<Movie> optMovie = movieService.getMovie(id);
    if (optMovie.isPresent()) {
      movieService.deleteMovie(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<MovieResponse>> searchMovie(@RequestParam Long category) {
    var movies = movieService.getMovieByCategory(category).stream()
        .map(MovieMapper::toMovieResponse)
        .toList();

    return ResponseEntity.ok(movies);
  }

}
