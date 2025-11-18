package br.com.movieflix.service;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, StreamingService streamingService) {
      this.movieRepository = movieRepository;
      this.categoryService = categoryService;
      this.streamingService = streamingService;
    }


  public Movie createMovie(Movie movie) {

      movie.setCategories(findCategories(movie.getCategories()));

      movie.setStreamings(findStreaming(movie.getStreamings()));

      return movieRepository.save(movie);
  }

  public List<Movie> listAll() {
      return movieRepository.findAll();
  }

  public Optional<Movie> getMovie(Long id) {
       return movieRepository.findById(id);
  }

  public Optional<Movie> updateMovie(Long movieId, Movie updatedMovie) {
    Optional<Movie> optMovie = movieRepository.findById(movieId);
    if (optMovie.isPresent()) {
      Movie movie = optMovie.get();

      movie.getCategories().clear();
      movie.getStreamings().clear();

      movie.setTitle(updatedMovie.getTitle());
      movie.setDescription(updatedMovie.getDescription());
      movie.setRating(updatedMovie.getRating());
      movie.setReleaseDate(updatedMovie.getReleaseDate());
      movie.getCategories().addAll(findCategories(updatedMovie.getCategories()));
      movie.getStreamings().addAll(findStreaming(updatedMovie.getStreamings()));

      movieRepository.save(movie);

      return Optional.of(movie);
    }

    return Optional.empty();
  }

  public List<Movie> getMovieByCategory(Long categoryId) {
      return movieRepository.findByCategories(List.of(Category.builder().id(categoryId).build()));
  }

  private List<Category> findCategories(List<Category> categories) {
    List<Category> categoryFound = new ArrayList<>();
    for (Category category : categories) {
      categoryService.findById(category.getId())
          .ifPresent(categoryFound::add);
    }
    return categoryFound;
  }

  private List<Streaming> findStreaming(List<Streaming> streamings) {
      List<Streaming> streamingFound = new ArrayList<>();
      for (Streaming streaming : streamings) {
        streamingService.findById(streaming.getId())
            .ifPresent(streamingFound::add);
      }

      return streamingFound;
  }


  public void deleteMovie(Long id) {
      movieRepository.deleteById(id);
    }
}

