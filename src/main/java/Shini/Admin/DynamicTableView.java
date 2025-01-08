package Shini.Admin;

import Shini.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DynamicTableView {

    public static TableView<Map<String, Object>> createDynamicTable(String query) {
        TableView<Map<String, Object>> table = new TableView<>();
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Dynamically create columns
            for (int i = 1; i <= columnCount; i++) {
                final String columnName = metaData.getColumnName(i);
                TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);

                // Wrap the value in a SimpleStringProperty
                column.setCellValueFactory(cellData -> {
                    Object value = cellData.getValue().get(columnName);
                    return new SimpleStringProperty(value == null ? "" : value.toString());
                });

                table.getColumns().add(column);
            }

            // Populate data rows
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                data.add(row);
            }

            table.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return table;
    }

}
