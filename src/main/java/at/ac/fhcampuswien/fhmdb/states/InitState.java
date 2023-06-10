package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.Comparator;

public class InitState extends State {
    public InitState(MovieListController context) {
        super(context);
    }

    @Override
    public void sortMovies() {
        context.observableMovies.sort(Comparator.comparing(Movie::getTitle));
        context.setState(new AscendingState(context));
    }

    @Override
    public void keepSort() {

    }
}
