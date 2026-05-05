package Controller;

import DAO.MovieDAO;
import DAO.PersonDAO;
import Model.Movie;
import Model.User;
import java.util.List;
import java.util.Map;

public class MovieController {

    private final MovieDAO movieDAO;
    private final PersonDAO personDAO;

    public MovieController() {
        this.movieDAO = new MovieDAO();
        this.personDAO = new PersonDAO();
    }

    public boolean addMovie(Movie movie) {
        return movieDAO.addMovie(movie);
    }

    public boolean removeMovie(int movieId) {
        return movieDAO.deleteMovie(movieId);
    }

    public boolean editMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    public Movie getMovieById(int movieId) {
        return movieDAO.getMovieById(movieId);
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public List<Movie> getAllMoviesForUser(User user) {
        List<Movie> all = movieDAO.getAllMovies();
        if (user.isParent()) {
            return all;
        }
        // children cannot see restricted movies
        List<Movie> filtered = new java.util.ArrayList<>();
        for (Movie m : all) {
            if (!m.isParentalRestriction()) {
                filtered.add(m);
            }
        }
        return filtered;
    }

    public List<Movie> searchMovies(String keyword, String genre, int year) {
        return movieDAO.searchMovies(keyword, genre, year);
    }

    public boolean setParentalRestriction(int movieId, boolean restricted) {
        return movieDAO.setParentalRestriction(movieId, restricted);
    }

    public boolean moderateComment(int movieId) {
        return movieDAO.deleteComment(movieId);
    }

    public boolean markWatched(int movieId, boolean watched) {
        return movieDAO.markWatched(movieId, watched);
    }

    public boolean rateMovie(int movieId, int rating) {
        if (rating < 1 || rating > 10) {
            return false;
        }
        return movieDAO.rateMovie(movieId, rating);
    }

    public boolean addComment(int movieId, String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        return movieDAO.addComment(movieId, comment.trim());
    }

    public Map<String, Object> getFamilyRatings(int movieId) {
        return movieDAO.getFamilyRatings(movieId);
    }
}
