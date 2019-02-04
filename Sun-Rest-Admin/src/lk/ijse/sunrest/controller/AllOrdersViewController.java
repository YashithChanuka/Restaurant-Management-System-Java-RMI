package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.OrderDetailService;

import java.rmi.server.UnicastRemoteObject;

public class AllOrdersViewController implements Observer {

    @FXML
    private AnchorPane rootAllOrdersView;

    @FXML
    private JFXButton btnReports;

    @FXML
    private TableView<OrderDetailDTO> tblAllOrders;

    private OrderDetailService orderDetailService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this, 0);
        orderDetailService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERDETAIL);
        loadTable();
        orderDetailService.register(this);
    }

    private void loadTable() throws Exception {
        tblAllOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblAllOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("chefName"));
        tblAllOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblAllOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblAllOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderedQty"));
        tblAllOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        tblAllOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("date"));

        tblAllOrders.setItems(FXCollections.observableArrayList(orderDetailService.getAllFinishedOrderDetails()));

    }

    @FXML
    void getAllOrdersReports(ActionEvent event) throws Exception {

    }

    @Override
    public void update(String message) throws Exception {
        loadTable();
    }
}
