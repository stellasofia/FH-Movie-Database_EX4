package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.Comparator;

public class DescendingState extends State {
    public DescendingState(MovieListController context) {
        super(context);
    }

    @Override
    public void sortMovies() {
        context.observableMovies.sort(Comparator.comparing(Movie::getTitle));
        context.setState(new AscendingState(context));
    }

    public void keepSort() {
        context.observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        context.setState(new DescendingState(context));
    }

}