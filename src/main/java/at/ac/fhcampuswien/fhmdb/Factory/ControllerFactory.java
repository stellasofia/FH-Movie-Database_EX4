package at.ac.fhcampuswien.fhmdb.Factory;

import at.ac.fhcampuswien.fhmdb.controllers.MainController;
import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.controllers.WatchlistController;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {
     private static ControllerFactory instance = null;
     private MainController mainController = null;
     private WatchlistController watchlistController = null;
     private MovieListController movieListController = null;

     @Override
     public Object call(Class<?> aClass) {
          try {
               if (aClass == WatchlistController.class) {
                    if (watchlistController == null) {
                         watchlistController = (WatchlistController) aClass.getDeclaredConstructor().newInstance();
                    }
                    return watchlistController;
               } else if (aClass == MainController.class) {
                    if (mainController == null) {
                         mainController = (MainController) aClass.getDeclaredConstructor().newInstance();
                    }
                    return mainController;
               } else if (aClass == MovieListController.class) {
                    if (movieListController == null) {
                         movieListController = (MovieListController) aClass.getDeclaredConstructor().newInstance();
                    }
                    return movieListController;
               }
          } catch (Exception e){
               e.printStackTrace();
          }
          return null;
     }
}
