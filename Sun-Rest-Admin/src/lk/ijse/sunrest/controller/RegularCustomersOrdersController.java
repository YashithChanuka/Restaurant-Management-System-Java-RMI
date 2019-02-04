package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.dto.RegCusCommonDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.resource.IDGenerator;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.CustomerService;
import lk.ijse.sunrest.service.custom.ItemService;
import lk.ijse.sunrest.service.custom.OrderDetailService;
import lk.ijse.sunrest.service.custom.RegCusOrdersTransService;

import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class RegularCustomersOrdersController implements Observer {

    @FXML
    private AnchorPane rootRegularCustomersOrders;

    @FXML
    private JFXButton btnBack;

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
    private JFXComboBox<CustomerDTO> cmbCustomers;

    @FXML
    private TableView<OrderDetailDTO> tblRecentOrders;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtPaidAmount;

    private CustomerService customerService;

    private ItemService itemService;

    private RegCusOrdersTransService regCusOrdersTransService;

    private OrderDetailService orderDetailService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this,0);
        customerService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
        itemService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ITEM);
        orderDetailService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERDETAIL);
        regCusOrdersTransService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.REGCUSORDERS);
        loadCustomerCombo();
        loadItemCombo();
        loadTable();
        nonEditableText();
        autoGenerateOrderID();
        regCusOrdersTransService.register(this);
        orderDetailService.register(this);
    }

    private void clear(){
        txtQty.setText(null);
        txtUnitPrice.setText(null);
        txtTotalAmount.setText(null);
    }

    private void nonEditableText(){
        txtOrderID.setEditable(false);
        txtUnitPrice.setEditable(false);
        txtTotalAmount.setEditable(false);
        txtBalance.setEditable(false);
    }

    @FXML
    void backToMainMenuAgain(ActionEvent event) {

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
        String orderID = txtOrderID.getText();
        calculateLoadTableTotal();
        int qty = Integer.parseInt(txtQty.getText());
        double totalAmount = Double.parseDouble(txtTotalAmount.getText());
        String itemName = cmbFoodItems.getSelectionModel().getSelectedItem().getItemName();
        List<ItemDTO> itemDTOS = itemService.searchItemFromName(itemName);
        String customerName = cmbCustomers.getSelectionModel().getSelectedItem().getCustomerName();
        List<CustomerDTO> customerDTOS = customerService.searchCustomerFromName(customerName);
        String itemCode = null;
        String customerID = null;
        for (ItemDTO itemDTO : itemDTOS){
            itemCode = itemDTO.getItemCode();
        }
        for (CustomerDTO customerDTO : customerDTOS){
            customerID = customerDTO.getCustomerID();
        }
        String status = "NEW";

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderID, qty, status, totalAmount, new Date());

        RegCusCommonDTO regCusCommonDTO = new RegCusCommonDTO(customerID, itemCode, orderDetailDTO);

        boolean isAdded = regCusOrdersTransService.addRegCusOrder(regCusCommonDTO);
        if (isAdded){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Added...!!!",ButtonType.OK);
            alert.showAndWait();
            autoGenerateOrderID();
            loadTable();
            clear();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Not Added...!!!",ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void updateOrder(ActionEvent event) {

    }

    private void loadCustomerCombo() throws Exception {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        cmbCustomers.getItems().setAll(allCustomers);
    }

    private void loadItemCombo() throws Exception {
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

    private void autoGenerateOrderID(){

        try {
            String orderID;
            orderID = IDGenerator.getNewID("OrderDetail", "orderID", "ORD");
            txtOrderID.setText(orderID);
        } catch (Exception ex) {
            System.out.println("Not Working...");
        }

    }


    @Override
    public void update(String message) throws Exception {
        loadTable();
    }
}
