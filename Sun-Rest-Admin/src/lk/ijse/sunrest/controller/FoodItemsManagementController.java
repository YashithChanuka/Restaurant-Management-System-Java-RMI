package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.resource.IDGenerator;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.ItemService;

import java.awt.event.MouseEvent;
import java.rmi.server.UnicastRemoteObject;

public class FoodItemsManagementController implements Observer {

    @FXML
    private AnchorPane rootFoodItemMgt;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private TextArea txtItemDescription;

    @FXML
    private JFXTextField txtSellingPrice;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<ItemDTO> tblAllFoodItems;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private TextField txtSearchItem;

    @FXML
    private JFXButton btnBack;

    private ItemService itemService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this,0);
        itemService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ITEM);
        loadTable();
        autoGenerateItemCode();
        txtItemCode.setEditable(false);
        itemService.register(this);
    }

    private void clear(){
        txtItemName.setText(null);
        txtItemDescription.setText(null);
        txtSellingPrice.setText(null);
    }

    @FXML
    void backToMainMenu(ActionEvent event) throws Exception {
        itemService.unregister(this);
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/AdminMainMenu.fxml"));
        rootFoodItemMgt.getChildren().addAll(parent);
    }

    @FXML
    void deleteItem(ActionEvent event) throws Exception {
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String description = txtItemDescription.getText();
        double sellingPrice = Double.parseDouble(txtSellingPrice.getText());

        ItemDTO itemDTO = new ItemDTO(itemCode, itemName, description, sellingPrice);
        boolean isDeleted = itemService.deleteItem(itemDTO);
        if (isDeleted){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted...!!!",ButtonType.OK);
            alert.showAndWait();
            autoGenerateItemCode();
            loadTable();
            clear();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Not Deleted...!!!",ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void saveItem(ActionEvent event) throws Exception {
        try {
            String itemCode = txtItemCode.getText();
            String itemName = txtItemName.getText();
            String description = txtItemDescription.getText();
            double sellingPrice = Double.parseDouble(txtSellingPrice.getText());

            boolean matchesItemName = itemName.matches("[A-Za-z ]*");//Validations

            if (matchesItemName){

                    ItemDTO itemDTO = new ItemDTO(itemCode, itemName, description, sellingPrice);
                    boolean isAdded = itemService.addItem(itemDTO);
                    if (isAdded){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Added...!!!",ButtonType.OK);
                        alert.showAndWait();
                        autoGenerateItemCode();
                        loadTable();
                        clear();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Not Added...!!!",ButtonType.OK);
                        alert.showAndWait();
                    }
            }else{
                txtItemName.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR, "No special characters & Numbers here...");
                alert.showAndWait();
            }

        }catch (NumberFormatException ex){
            txtSellingPrice.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR,"You can't have letters...");
            alert.showAndWait();
        }
    }

    @FXML
    void searchItem(ActionEvent event) throws Exception {
        String itemCode = txtSearchItem.getText();
        ItemDTO itemDTO = itemService.searchItem(itemCode);

        txtItemCode.setText(itemDTO.getItemCode());
        txtItemName.setText(itemDTO.getItemName());
        txtItemDescription.setText(itemDTO.getDescription());
        txtSellingPrice.setText(itemDTO.getSellingPrice()+"");
    }

    @FXML
    void updateItem(ActionEvent event) throws Exception {
        try {
            String itemCode = txtItemCode.getText();
            String itemName = txtItemName.getText();
            String description = txtItemDescription.getText();
            double sellingPrice = Double.parseDouble(txtSellingPrice.getText());

            boolean matchesItemName = itemName.matches("[A-Za-z ]*");

            if (matchesItemName){
                ItemDTO itemDTO = new ItemDTO(itemCode, itemName, description, sellingPrice);
                boolean isUpdated = itemService.updateItem(itemDTO);
                if (isUpdated){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Updated...!!!",ButtonType.OK);
                    alert.showAndWait();
                    loadTable();
                    clear();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Not Updated...!!!",ButtonType.OK);
                    alert.showAndWait();
                }
            }else{
                txtItemName.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR, "No characters & Numbers here...");
                alert.showAndWait();
            }
        }catch (NumberFormatException ex){
            txtSellingPrice.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR,"You can't have letters...");
            alert.showAndWait();
        }
    }

    private void loadTable() throws Exception {
        tblAllFoodItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblAllFoodItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblAllFoodItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblAllFoodItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        tblAllFoodItems.setItems(FXCollections.observableArrayList(itemService.getAllItems()));

    }

    private void autoGenerateItemCode(){

        try {
            String itemCode;
            itemCode = IDGenerator.getNewID("Item", "itemCode", "ITM");
            txtItemCode.setText(itemCode);
        } catch (Exception ex) {
            System.out.println("Not Working...");
        }

    }


    @FXML
    void getSelectedRow(MouseEvent event) {

    }

    @Override
    public void update(String message) throws Exception {

    }

    public void getSelectedRow(javafx.scene.input.MouseEvent mouseEvent) throws Exception {

        String itemCode = tblAllFoodItems.getSelectionModel().getSelectedItem().getItemCode();

        ItemDTO itemDTO = itemService.searchItem(itemCode);

        txtItemCode.setText(itemDTO.getItemCode());
        txtItemName.setText(itemDTO.getItemName());
        txtItemDescription.setText(itemDTO.getDescription());
        txtSellingPrice.setText(itemDTO.getSellingPrice()+"");

    }
}
