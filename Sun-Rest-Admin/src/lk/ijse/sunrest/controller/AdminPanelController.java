package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.OrderDetailService;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPanelController implements Observer {

    @FXML
    private AnchorPane rootAdminPanel;

    @FXML
    private JFXButton btnChefMgt;

    @FXML
    private JFXButton btnFoodItemsMgt;

    @FXML
    private JFXButton btnNewCustomersOrders;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnMainMenu;

    @FXML
    private JFXButton btnRegularCustomersOrders;

    @FXML
    private JFXButton btnViewAllOrders;

    @FXML
    private AnchorPane rootMainAdmin;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private TableView<OrderDetailDTO> tblTodayOrders;

    private OrderDetailService orderDetailService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this,0);
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

    @FXML
    void getChefPane(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/ChefManagement.fxml"));
        rootMainAdmin.getChildren().addAll(parent);
    }

    @FXML
    void getFoodItemsPane(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/FoodItemsManagement.fxml"));
        rootMainAdmin.getChildren().addAll(parent);
    }

    @FXML
    void getLogOut(ActionEvent event) throws Exception {

    }

    @FXML
    void getMainMenu(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/AdminMainMenu.fxml"));
        rootMainAdmin.getChildren().addAll(parent);
    }

    @FXML
    void getNewCustomersOrders(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/NewCustomersOrders.fxml"));
        rootMainAdmin.getChildren().addAll(parent);
    }

    @FXML
    void getRegularCustomersOrders(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/RegularCustomersOrders.fxml"));
        rootMainAdmin.getChildren().addAll(parent);
    }

    @FXML
    void getViewAllOrders(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/AllOrdersView.fxml"));
        rootMainAdmin.getChildren().addAll(parent);
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

    @Override
    public void update(String message) throws Exception {
        loadTable();
    }
}
