package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.*;

import java.awt.event.MouseEvent;
import java.rmi.server.UnicastRemoteObject;

public class ChefPanelController implements Observer {

    @FXML
    private AnchorPane rootChefPanel;

    @FXML
    private TableView<OrderDetailDTO> tblNewOrders;

    @FXML
    private TableView<ItemDTO> tblItems;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtChefID;

    @FXML
    private JFXButton btnGetOrder;

    @FXML
    private JFXButton btnFinished;

    private ItemService itemService;

    private OrderDetailService orderDetailService;

    private ChefService chefService;

    private CustomerService customerService;

    private RegCusOrdersTransService regCusOrdersTransService;

    private NewCustomersOrdersTransService newCustomersOrdersTransService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this,0);
        itemService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ITEM);
        orderDetailService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERDETAIL);
        chefService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CHEF);
        customerService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
        regCusOrdersTransService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.REGCUSORDERS);
        newCustomersOrdersTransService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.NEWCUSTOMERSORDERSTRANS);
        loadItemsTable();
        loadOrdersTable();
        nonEditableText();
        regCusOrdersTransService.register(this);
        orderDetailService.register(this);
        itemService.register(this);
        newCustomersOrdersTransService.register(this);
    }

    public void setChef(String chefID){
        txtChefID.setText(chefID);
    }

    public void setMessage(String chefID, String message){
        if (txtChefID.getText().equals(chefID)){
            System.out.println(message);
        }
    }

    private void nonEditableText(){
        txtChefID.setEditable(false);
        txtOrderID.setEditable(false);
        txtItemCode.setEditable(false);
        txtQty.setEditable(false);
    }

    @FXML
    void finishedOrder(ActionEvent event) throws Exception {
        String orderID = txtOrderID.getText();
        boolean isUpdated = orderDetailService.updateOrderStatusFinished(orderID);
        if (isUpdated){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Order has Finished...!!!",ButtonType.OK);
            alert.showAndWait();
            loadOrdersTable();
            txtOrderID.setText(null);
            txtItemCode.setText(null);
            txtQty.setText(null);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error",ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void getOrder(ActionEvent event) throws Exception {
        String orderID = txtOrderID.getText();
        String chefID = txtChefID.getText();
        boolean isUpdated = orderDetailService.updateOrderStatusKitchen(orderID, chefID);

        if (isUpdated){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Order Got Successfully...!!!",ButtonType.OK);
            alert.showAndWait();
            loadOrdersTable();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error",ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void loadItemsTable() throws Exception {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));

        tblItems.setItems(FXCollections.observableArrayList(itemService.getAllItems()));
    }

    private void loadOrdersTable() throws Exception {
        tblNewOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblNewOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblNewOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderedQty"));
        tblNewOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        tblNewOrders.setItems(FXCollections.observableArrayList(orderDetailService.getAllNewOrderDetails()));
    }

    @FXML
    void getSelectedRow(MouseEvent event) {

    }

    public void getSelectedRow(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        String orderID = tblNewOrders.getSelectionModel().getSelectedItem().getOrderID();
        OrderDetailDTO orderDetailDTO = orderDetailService.searchOrderDetail(orderID);

        txtOrderID.setText(orderDetailDTO.getOrderID());
        txtItemCode.setText(orderDetailDTO.getCustomerName());
        txtQty.setText(orderDetailDTO.getOrderedQty()+"");
    }

    @Override
    public void update(String message) throws Exception {
        loadOrdersTable();
        loadItemsTable();
    }
}
