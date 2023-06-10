package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public abstract class State {
    protected MovieListController context;

    public State(MovieListController context) {
        this.context = context;
    }

    public abstract void sortMovies();
    public abstract void keepSort(); //damit sortierung bestehen bleibt beim filtern
}
