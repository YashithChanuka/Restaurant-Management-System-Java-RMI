package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.OrderDetailService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminMainMenuController implements Observer {

    @FXML
    private AnchorPane rootAdminMainMenu;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private TableView<OrderDetailDTO> tblTodayOrders;

    private OrderDetailService orderDetailService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this, 0);
        orderDetailService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERDETAIL);
        setDate();
        loadTable();
        txtDate.setEditable(false);
        orderDetailService.register(this);
    }

    private void setDate() {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        txtDate.setText(dateFormat.format(date));
    }

    private void loadTable() throws Exception {

        String date = txtDate.getText();

        tblTodayOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblTodayOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblTodayOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("chefName"));
        tblTodayOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblTodayOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderedQty"));
        tblTodayOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblTodayOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        tblTodayOrders.setItems(FXCollections.observableArrayList(orderDetailService.getAllTodayOrderDetails(date)));

    }

    @FXML
    void getSelectedRow(MouseEvent event) {

    }

    public void getSelectedRow(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/sunrest/view/MessageBox.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    @Override
    public void update(String message) throws Exception {
        loadTable();
    }
}
