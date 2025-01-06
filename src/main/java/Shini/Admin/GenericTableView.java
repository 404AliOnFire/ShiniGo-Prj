package Shini.Admin;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class GenericTableView<T> extends TableView {

    // Create a Generic Table View
    public <T, S> TableView<T> createTableView(ObservableList<T> data) {

        setItems((ObservableList<T>) data);

        if (data.isEmpty()) {
            System.out.println("empty");
            return this;
        }

        T sample = data.get(0);
        getColumns().clear();

        // Create a TableColumn for each field
        for (var field : sample.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Allow access to private fields

            TableColumn<S, ?> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));

            getColumns().add(column);

        }

        setStyle("-fx-size: 30");
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setEditable(true);

        return this;
    }
}