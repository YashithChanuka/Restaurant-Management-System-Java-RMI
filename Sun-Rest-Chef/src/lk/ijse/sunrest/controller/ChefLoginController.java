package lk.ijse.sunrest.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.sunrest.proxy.ProxyHandeler;
import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.ChefService;

public class ChefLoginController {

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    private ChefService chefService;

    public void initialize() throws Exception {
        chefService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CHEF);
    }

    @FXML
    void getAdminPanel(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/sunrest/view/ChefPanel.fxml"));
            Parent parent = loader.load();
            String userName = txtUserName.getText();
            String password = txtPassword.getText();
            String chefID = chefService.getChefIDFor(userName, password);
            ChefPanelController chefPanel = loader.getController();
            chefPanel.setChef(chefID);
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please fill the fields or check again before login...",ButtonType.OK);
            alert.showAndWait();
        }
    }

}
