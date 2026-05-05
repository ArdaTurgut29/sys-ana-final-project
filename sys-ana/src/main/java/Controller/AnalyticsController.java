package Controller;

import DAO.MovieDAO;
import DAO.UserDAO;
import Model.Movie;
import Model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsController {

    private final MovieDAO movieDAO;
    private final UserDAO userDAO;

    public AnalyticsController() {
        this.movieDAO = new MovieDAO();
        this.userDAO = new UserDAO();
    }

    public List<Movie> getMostWatchedMovies() {
        List<Movie> all = movieDAO.getAllMovies();
        List<Movie> watched = new ArrayList<>();
        for (Movie m : all) {
            if (m.isWatched()) {
                watched.add(m);
            }
        }
        return watched;
    }

    public Map<Movie, Double> getAverageRatings() {
        List<Movie> all = movieDAO.getAllMovies();
        Map<Movie, Double> ratings = new HashMap<>();
        for (Movie m : all) {
            if (m.getRating() > 0) {
                ratings.put(m, (double) m.getRating());
            }
        }
        return ratings;
    }

    public Map<User, Integer> getWatchProgressPerUser() {
        List<User> users = userDAO.getAllUsers();
        List<Movie> allMovies = movieDAO.getAllMovies();
        Map<User, Integer> progress = new HashMap<>();

        int watchedCount = 0;
        for (Movie m : allMovies) {
            if (m.isWatched()) watchedCount++;
        }

        for (User u : users) {
            progress.put(u, watchedCount);
        }
        return progress;
    }

    public List<Movie> getTopRatedMovies(int limit) {
        List<Movie> all = movieDAO.getAllMovies();
        all.sort((a, b) -> Integer.compare(b.getRating(), a.getRating()));
        return all.subList(0, Math.min(limit, all.size()));
    }
}