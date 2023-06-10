package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import javafx.scene.chart.PieChart;
import at.ac.fhcampuswien.fhmdb.interfaces.ObservableWatchlist;
import at.ac.fhcampuswien.fhmdb.interfaces.ObserverWatchlist;

import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements ObservableWatchlist {

    private static WatchlistRepository instance;
    private Dao<WatchlistMovieEntity, Long> dao;
    private List<ObserverWatchlist> observers;

    // The constructor of WatchlistRepository is made private to prevent direct instantiation.
    private WatchlistRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
            this.observers = new ArrayList<>();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    // A private static instance variable instance is declared to hold the singleton instance of WatchlistRepository.
    // The getInstance() method is added to provide access to the singleton instance.
    // It follows the double-checked locking pattern to ensure thread safety and lazy initialization.
    public static WatchlistRepository getInstance() throws DataBaseException {
        if (instance == null) {
            synchronized (WatchlistRepository.class) {
                if (instance == null) {
                    instance = new WatchlistRepository();
                }
            }
        }
        return instance;
    }

    public List<WatchlistMovieEntity> readWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }
    public void addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                dao.create(movie);
                notifyObservers("Movie added to watchlist");
            } else { notifyObservers("Movie already in  watchlist"); }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public void removeFromWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            dao.delete(movie);
            notifyObservers("Movie removed from watchlist");
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    public boolean isOnWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            notifyObservers("Movie already in  watchlist");
            return dao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DataBaseException("Error while checking if movie is on watchlist");
        }
    }

    @Override
    public void addObserver(ObserverWatchlist observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverWatchlist observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (ObserverWatchlist observer : observers) {
            observer.update(message);
        }
    }
}
