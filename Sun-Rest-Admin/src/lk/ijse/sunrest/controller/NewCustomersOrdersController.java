package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.*;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.resource.IDGenerator;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.CustomerService;
import lk.ijse.sunrest.service.custom.ItemService;
import lk.ijse.sunrest.service.custom.NewCustomersOrdersTransService;
import lk.ijse.sunrest.service.custom.OrderDetailService;

import java.awt.event.MouseEvent;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class NewCustomersOrdersController implements Observer {

    @FXML
    private AnchorPane rootNewCustomers;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerTele;

    @FXML
    private JFXComboBox<ItemDTO> cmbFoodItems;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TableView<OrderDetailDTO> tblRecentOrders;

    private CustomerService customerService;

    private ItemService itemService;

    private NewCustomersOrdersTransService newCustomersOrdersTransService;

    private OrderDetailService orderDetailService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this, 0);
        customerService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
        itemService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ITEM);
        newCustomersOrdersTransService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.NEWCUSTOMERSORDERSTRANS);
        orderDetailService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERDETAIL);
        loadItemsCombo();
        loadTable();
        nonEditableText();
        autoGenerateOrderID();
        autoGenerateCustomerID();
        newCustomersOrdersTransService.register(this);
        orderDetailService.register(this);
    }

    private void clear(){
        txtCustomerName.setText(null);
        txtCustomerAddress.setText(null);
        txtCustomerTele.setText(null);
        txtQty.setText(null);
        txtUnitPrice.setText(null);
        txtTotalAmount.setText(null);
    }

    private void nonEditableText(){
        txtOrderID.setEditable(false);
        txtCustomerID.setEditable(false);
        txtUnitPrice.setEditable(false);
        txtTotalAmount.setEditable(false);
        txtBalance.setEditable(false);
    }

    @FXML
    void backToMainMenuAgain(ActionEvent event) throws Exception {
        customerService.unregister(this);
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/AdminMainMenu.fxml"));
        rootNewCustomers.getChildren().addAll(parent);
    }

    @FXML
    void calculateBalance(ActionEvent event) {
        double totalAmount = Double.parseDouble(txtTotalAmount.getText());
        double paidAmount = Double.parseDouble(txtPaidAmount.getText());
        double balance = (paidAmount - totalAmount);
        txtBalance.setText(balance+"");
    }

    @FXML
    void calculateLoadTableTotal() throws Exception {
        try {
            String itemCode = cmbFoodItems.getSelectionModel().getSelectedItem().getItemCode();
            ItemDTO itemDTO = itemService.searchItem(itemCode);
            double sellingPrice = itemDTO.getSellingPrice();
            txtUnitPrice.setText(itemDTO.getSellingPrice()+"");
            int qty = Integer.parseInt(txtQty.getText());
            double total = sellingPrice*qty;
            txtTotalAmount.setText(total+"");
        }catch (Exception ex){
            txtQty.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please Enter the number of Items...",ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void deleteOrder(ActionEvent event) {

    }

    @FXML
    void saveOrder(ActionEvent event) throws Exception {
        String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String tele = txtCustomerTele.getText();

        String orderID = txtOrderID.getText();
        String itemName = cmbFoodItems.getSelectionModel().getSelectedItem().getItemName();
        List<ItemDTO> itemDTOS = itemService.searchItemFromName(itemName);
        String itemCode = null;
        for (ItemDTO itemDTO : itemDTOS){
            itemCode = itemDTO.getItemCode();
        }
        calculateLoadTableTotal();
        int qty = Integer.parseInt(txtQty.getText());
        double totalAmount = Double.parseDouble(txtTotalAmount.getText());
        String status = "NEW";

        //---------------Validations for new customer's order form
        boolean matchesCustomerName = customerName.matches("^[A-Z][a-z]*[ ][A-Z][a-z]*");
        boolean matchesAddress = address.matches("[A-Za-z:0-9,/ ]*");
        boolean matchesTele = tele.matches("(^0[0-9]{2}-)([0-9]{7})");

        if (matchesCustomerName){
            if (matchesAddress){
                if (matchesTele){

                    CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, address, tele);
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderID, qty, status, totalAmount, new Date(), customerDTO);

                    CommonDTO commonDTO = new CommonDTO(customerDTO, itemCode, orderDetailDTO);

//        ItemDTO itemDTO = itemService.searchItemFromName(itemName);
//        System.out.println(itemDTO);

                    boolean isAdded = newCustomersOrdersTransService.addNewCustomerOrder(commonDTO);
                    if (isAdded){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Added...!!!",ButtonType.OK);
                        alert.showAndWait();
                        autoGenerateOrderID();
                        autoGenerateCustomerID();
                        loadTable();
                        clear();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Not Added...!!!",ButtonType.OK);
                        alert.showAndWait();
                    }

                }else{
                    txtCustomerTele.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR,"This is the Tele format : 077-7894561 ",ButtonType.OK);
                    alert.showAndWait();
                }
            }else {
                txtCustomerAddress.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR,"Please check the chef's address again...",ButtonType.OK);
                alert.showAndWait();
            }
        }else {
            txtCustomerName.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR,"No numbers & special characters here...",ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void autoGenerateOrderID(){

        try {
            String orderID;
            orderID = IDGenerator.getNewID("OrderDetail", "orderID", "ORD");
            txtOrderID.setText(orderID);
        } catch (Exception ex) {
            System.out.println("Not Working...");
        }

    }

    private void autoGenerateCustomerID(){

        try {
            String customerID;
            customerID = IDGenerator.getNewID("Customer", "customerID", "CUS");
            txtCustomerID.setText(customerID);
        } catch (Exception ex) {
            System.out.println("Not Working...");
        }

    }


    @FXML
    void getSelectedOne(MouseEvent event) {

    }

    @FXML
    void updateOrder(ActionEvent event) {

    }

    private void loadItemsCombo() throws Exception {
        List<ItemDTO> allItems = itemService.getAllItems();
        cmbFoodItems.getItems().setAll(allItems);
    }

    private void loadTable() throws Exception {
        tblRecentOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblRecentOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblRecentOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblRecentOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("orderedQty"));
        tblRecentOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblRecentOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        tblRecentOrders.setItems(FXCollections.observableArrayList(orderDetailService.getAllNewOrderDetails()));

    }

    @Override
    public void update(String message) throws Exception {
        loadTable();
    }

}
