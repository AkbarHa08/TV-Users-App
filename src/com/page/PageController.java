package com.page;

import com.dao.DaoImpl;
import com.model.TvUsers;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PageController implements Initializable {
    
    Integer selectionid = 0;

    @FXML
    private TextField idTF;
    @FXML
    private TextField fullnameTF;
    @FXML
    private TextField mobileTF;
    @FXML
    private TextField loginTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField addressTF;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<TvUsers> tableView;
    @FXML
    private TableColumn<TvUsers, Integer> idColumn;
    @FXML
    private TableColumn<TvUsers, String> fullnameColumn;
    @FXML
    private TableColumn<TvUsers, String> mobileColumn;
    @FXML
    private TableColumn<TvUsers, String> loginColumn;
    @FXML
    private TableColumn<TvUsers, String> passwordColumn;
    @FXML
    private TableColumn<TvUsers, String> addressColumn;
    @FXML
    private Label pageWarningLbl;
    
    DaoImpl dao = new DaoImpl();
    @FXML
    private Button clearBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadColumn();
        loadRows();
    }     
    
    public void refresh() {
        tableView.getItems().clear();
        tableView.getItems().addAll(dao.getAllUsers());
    }

    @FXML
    private void searchBtnOnAction(ActionEvent event) {
        String id = idTF.getText().trim();
        String fullname = fullnameTF.getText().trim();
        String mobile = mobileTF.getText().trim();
        String login = loginTF.getText().trim();
        String password = passwordTF.getText().trim();
        String address = addressTF.getText().trim();
        
        List resultList = dao.searchUsers(id, fullname, mobile, login, password, address);
        tableView.getItems().clear();
        tableView.getItems().addAll(resultList);
    }

    @FXML
    private void addBtnOnAction(ActionEvent event) {
        String fullname = fullnameTF.getText().trim();
        String mobile = mobileTF.getText().trim();
        String login = loginTF.getText().trim();
        String password = passwordTF.getText().trim();
        String address = addressTF.getText().trim();
        if (!fullname.equalsIgnoreCase("")&&!mobile.equalsIgnoreCase("")&&!login.equalsIgnoreCase("")
                &&!password.equalsIgnoreCase("")&&!address.equalsIgnoreCase("")) {
            try {
                TvUsers tvusers = new TvUsers();
                tvusers.setFullname(fullnameTF.getText());
                tvusers.setMobile(mobileTF.getText());
                tvusers.setLogin(loginTF.getText());
                tvusers.setPassword(passwordTF.getText());
                tvusers.setAddress(addressTF.getText());
                if (dao.addUser(tvusers)) {
                    pageWarningLbl.setText("User successfully added!");
                    refresh();
                } else {
                    pageWarningLbl.setText("User not added!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pageWarningLbl.setText("Please, fill in empty fields!");
        }
    }

    @FXML
    private void editBtnOnAction(ActionEvent event) {
        pageWarningLbl.setText("");
        String fullname = fullnameTF.getText().trim();
        String mobile = mobileTF.getText().trim();
        String login = loginTF.getText().trim();
        String password = passwordTF.getText().trim();
        String address = addressTF.getText().trim();
        if (!fullname.equalsIgnoreCase("")||!mobile.equalsIgnoreCase("")||!login.equalsIgnoreCase("")
                ||!password.equalsIgnoreCase("")||!address.equalsIgnoreCase("")) {
            try {
                TvUsers tvusers = new TvUsers();
                tvusers.setId(Integer.parseInt(idTF.getText()));
                tvusers.setFullname(fullname);
                tvusers.setMobile(mobile);
                tvusers.setLogin(login);
                tvusers.setPassword(password);
                tvusers.setAddress(address);
                if (dao.editUser(tvusers)) {
                    pageWarningLbl.setText("User successfully edited!");
                    refresh();
                } else {
                    pageWarningLbl.setText("User not edited!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pageWarningLbl.setText("Please, fill in empty fields!");
        }
    }

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
        boolean isDeleted = dao.deleteUser(selectionid);
        if (isDeleted) {
            refresh();
            pageWarningLbl.setText("User successfully deleted!");
        } else {
            pageWarningLbl.setText("User not deleted!");
        }
    }

    private void loadColumn() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadRows() {
        tableView.getItems().addAll(dao.getAllUsers());
    }

    @FXML
    private void tableViewOnMousePressed(MouseEvent event) {
        TvUsers tvusers = tableView.getSelectionModel().getSelectedItem();
        idTF.setText(String.valueOf(tvusers.getId()));
        fullnameTF.setText(tvusers.getFullname());
        mobileTF.setText(tvusers.getMobile());
        loginTF.setText(tvusers.getLogin());
        passwordTF.setText(tvusers.getPassword());
        addressTF.setText(tvusers.getAddress());
        selectionid = tvusers.getId();
    }

    @FXML
    private void clearBtnOnAction(ActionEvent event) {
        pageWarningLbl.setText("");
        idTF.setText("");
        fullnameTF.setText("");
        mobileTF.setText("");
        loginTF.setText("");
        passwordTF.setText("");
        addressTF.setText("");
    }
}