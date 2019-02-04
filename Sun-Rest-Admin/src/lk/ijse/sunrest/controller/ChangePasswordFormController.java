package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.AdminService;

public class ChangePasswordFormController {

    @FXML
    private AnchorPane rootAdminDetails;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtCurrentPassword;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private JFXButton btnSaveAdminDetails;

    private AdminService adminService;

    public void initialize() throws Exception {
        adminService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ADMIN);
        txtUserName.setEditable(false);
        txtCurrentPassword.setEditable(false);
    }

    @FXML
    void saveAdminDetails(ActionEvent event) throws Exception {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (newPassword.equals(confirmPassword)){
            boolean isUpdated = adminService.updatePassword(newPassword);
            if (isUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Password Updated...!!!",ButtonType.OK);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Not Updated...!!!",ButtonType.OK);
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "New Password and Confirm password are not matched...!!!",ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void setCurrentDetails(String userName, String password){
        txtUserName.setText(userName);
        txtCurrentPassword.setText(password);
    }

}
