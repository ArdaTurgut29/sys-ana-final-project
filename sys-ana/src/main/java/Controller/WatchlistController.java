package Controller;

import DAO.WatchlistDAO;
import Model.Movie;
import Model.User;
import java.util.List;

public class WatchlistController {

    private final WatchlistDAO watchlistDAO;
    private final User currentUser;

    public WatchlistController(User user) {
        this.watchlistDAO = new WatchlistDAO();
        this.currentUser = user;
    }

    public boolean addToWatchlist(int movieId) {
        return watchlistDAO.addToWatchlist(currentUser.getUserId(), movieId);
    }

    public boolean removeFromWatchlist(int movieId) {
        return watchlistDAO.removeFromWatchlist(currentUser.getUserId(), movieId);
    }

    public List<Movie> getMyWatchlist() {
        return watchlistDAO.getWatchlistByUser(currentUser.getUserId());
    }
}
