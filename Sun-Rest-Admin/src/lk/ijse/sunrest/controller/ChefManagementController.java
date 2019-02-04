package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.resource.IDGenerator;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.ChefService;

import java.awt.event.MouseEvent;
import java.rmi.server.UnicastRemoteObject;

public class ChefManagementController implements Observer {

    @FXML
    private AnchorPane rootChef;

    @FXML
    private JFXButton btnReports;

    @FXML
    private JFXTextField txtChefName;

    @FXML
    private JFXTextField txtChefAddress;

    @FXML
    private JFXTextField txtChefTele;

    @FXML
    private JFXTextField txtChefEmail;

    @FXML
    private JFXRadioButton rdBtnMale;

    @FXML
    private JFXRadioButton rdBtnFemale;

    @FXML
    private TextField txtChefUserName;

    @FXML
    private TextField txtChefPassword;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<ChefDTO> tblChefs;

    @FXML
    private JFXTextField txtChefID;

    @FXML
    private TextField txtSearchChef;

    @FXML
    private JFXButton btnBack;

    private ChefService chefService;

    public void initialize() throws Exception {
        UnicastRemoteObject.exportObject(this,0);
        chefService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CHEF);
        radioButtonController();
        loadTable();
        autoGenerateChefID();
        txtChefID.setEditable(false);
    }

    private void radioButtonController(){
        ToggleGroup toggle = new ToggleGroup();
        rdBtnMale.setToggleGroup(toggle);
        rdBtnFemale.setToggleGroup(toggle);
    }

    private void clear(){
        txtChefName.setText(null);
        txtChefAddress.setText(null);
        txtChefTele.setText(null);
        txtChefEmail.setText(null);
        txtChefUserName.setText(null);
        txtChefPassword.setText(null);
        rdBtnMale.setSelected(false);
        rdBtnFemale.setSelected(false);
    }

    @FXML
    void backToMainMenu(ActionEvent event) throws Exception {
        chefService.unregister(this);
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sunrest/view/AdminMainMenu.fxml"));
        rootChef.getChildren().addAll(parent);
    }

    @FXML
    void deleteChef(ActionEvent event) throws Exception {
        String chefID = txtChefID.getText();
        String chefName = txtChefName.getText();
        String address = txtChefAddress.getText();
        String gender = null;
        if (rdBtnMale.isSelected()){
            gender = "Male";
        }else if (rdBtnFemale.isSelected()){
            gender = "Female";
        }
        String tele = txtChefTele.getText();
        String email = txtChefEmail.getText();
        String userName = txtChefUserName.getText();
        String password = txtChefPassword.getText();

        ChefDTO chefDTO = new ChefDTO(chefID, chefName, address, gender, tele, email, userName, password);
        boolean isDeleted = chefService.deleteChef(chefDTO);
        if(isDeleted){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted...!!!");
            alert.showAndWait();
            autoGenerateChefID();
            loadTable();
            clear();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Not Deleted...!!!");
            alert.showAndWait();
        }
    }

    @FXML
    void getChefsReport(ActionEvent event) {

    }

    @FXML
    void saveChef(ActionEvent event) throws Exception {
        String chefID = txtChefID.getText();
        String chefName = txtChefName.getText();
        String address = txtChefAddress.getText();
        String gender = null;
        if (rdBtnMale.isSelected()){
            gender = "Male";
        }else if (rdBtnFemale.isSelected()){
            gender = "Female";
        }
        String tele = txtChefTele.getText();
        String email = txtChefEmail.getText();
        String userName = txtChefUserName.getText();
        String password = txtChefPassword.getText();

        //-----Validations to chef form text fields----------------------------------------
        boolean matchesChefName = chefName.matches("^[A-Z][a-z]*[ ][A-Z][a-z]*");
        boolean matchesAddress = address.matches("[A-Za-z:0-9,/ ]*");
        boolean matchesTele = tele.matches("(^0[0-9]{2}-)([0-9]{7})");
        boolean matchesPassword = password.matches("[A-Za-z0-9@&!#$^*%]{6}");
        //----------------------------

        if (matchesChefName){
            if (matchesAddress){
                if (matchesTele){
                    if (matchesPassword){

                        ChefDTO chefDTO = new ChefDTO(chefID, chefName, address, gender, tele, email, userName, password);
                        boolean isAdded = chefService.addChef(chefDTO);
                        if(isAdded){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Added...!!!");
                            alert.showAndWait();
                            autoGenerateChefID();
                            loadTable();
                            clear();
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Not Added...!!!");
                            alert.showAndWait();
                        }

                    }else{
                        txtChefPassword.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Password must have 6 characters only...",ButtonType.OK);
                        alert.showAndWait();
                    }
                }else{
                    txtChefTele.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR,"This is the Tele format : 077-7894561 ",ButtonType.OK);
                    alert.showAndWait();
                }
            }else {
                txtChefAddress.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR,"Please check the chef's address again...",ButtonType.OK);
                alert.showAndWait();
            }
        }else {
            txtChefName.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR,"No numbers & special characters here...",ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void searchChef(ActionEvent event) throws Exception {
        String chefID = txtSearchChef.getText();

        ChefDTO chefDTO = chefService.searchChef(chefID);//Search a chef from db according to chef id

        txtChefID.setText(chefDTO.getChefID());
        txtChefName.setText(chefDTO.getChefName());
        txtChefAddress.setText(chefDTO.getAddress());
        txtChefTele.setText(chefDTO.getTele());
        txtChefEmail.setText(chefDTO.getEmail());
        txtChefUserName.setText(chefDTO.getUserName());
        txtChefPassword.setText(chefDTO.getPassword());
        if (chefDTO.getGender().equals("Male")){
            rdBtnMale.setSelected(true);
        }else if (chefDTO.getGender().equals("Female")){
            rdBtnFemale.setSelected(true);
        }

    }

    @FXML
    void updateChef(ActionEvent event) throws Exception {
        String chefID = txtChefID.getText();
        String chefName = txtChefName.getText();
        String address = txtChefAddress.getText();
        String gender = null;
        if (rdBtnMale.isSelected()){
            gender = "Male";
        }else if (rdBtnFemale.isSelected()){
            gender = "Female";
        }
        String tele = txtChefTele.getText();
        String email = txtChefEmail.getText();
        String userName = txtChefUserName.getText();
        String password = txtChefPassword.getText();

        ChefDTO chefDTO = new ChefDTO(chefID, chefName, address, gender, tele, email, userName, password);
        boolean isUpdated = chefService.updateChef(chefDTO);
        if(isUpdated){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Updated...!!!");
            alert.showAndWait();
            loadTable();
            clear();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Not Updated...!!!");
            alert.showAndWait();
        }
    }

    private void loadTable() throws Exception {
        tblChefs.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("chefName"));
        tblChefs.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblChefs.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblChefs.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tele"));
        tblChefs.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("email"));

        tblChefs.setItems(FXCollections.observableArrayList(chefService.getAllChefs()));//set details to the table

    }

    private void autoGenerateChefID(){

        try {
            String chefID;
            chefID = IDGenerator.getNewID("Chef", "chefID", "CH");
            txtChefID.setText(chefID);
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

        String chefID = tblChefs.getSelectionModel().getSelectedItem().getChefID();

        ChefDTO chefDTO = chefService.searchChef(chefID);//Get all chef details from db according to chef id

        txtChefID.setText(chefDTO.getChefID());
        txtChefName.setText(chefDTO.getChefName());
        txtChefAddress.setText(chefDTO.getAddress());
        txtChefTele.setText(chefDTO.getTele());
        txtChefEmail.setText(chefDTO.getEmail());
        txtChefUserName.setText(chefDTO.getUserName());
        txtChefPassword.setText(chefDTO.getPassword());
        if (chefDTO.getGender().equals("Male")){
            rdBtnMale.setSelected(true);
        }else if (chefDTO.getGender().equals("Female")){
            rdBtnFemale.setSelected(true);
        }

    }
}
