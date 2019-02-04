package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sunrest.dto.AdminDTO;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.AdminService;

public class AdminLogin {

    @FXML
    private AnchorPane rootAdminLogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblChangePassword;

    private AdminService adminService;

    public void initialize() throws Exception {
        adminService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ADMIN);
    }

    @FXML
    void getAdminPanel(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/sunrest/view/AdminPanel.fxml"));
        Parent parent = loader.load();

        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        AdminDTO adminDTO = adminService.searchAdmin("A001");
        String userName1 = adminDTO.getUserName();
        String password1 = adminDTO.getPassword();

        if (userName.equals(userName1) && password.equals(password1)){
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please Check your UserName and Password again...!!!",ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void changePasswordPane(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/sunrest/view/changePasswordForm.fxml"));
        Parent parent = loader.load();
        AdminDTO adminDTO = adminService.searchAdmin("A001");
        String userName = adminDTO.getUserName();
        String password = adminDTO.getPassword();
        ChangePasswordFormController controller = loader.getController();
        controller.setCurrentDetails(userName, password);
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
