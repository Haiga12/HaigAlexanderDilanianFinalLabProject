package com.haigalexanderdilanian.finallabproject.ui;
import com.haigalexanderdilanian.finallabproject.business.ChequingAccount;
import com.haigalexanderdilanian.finallabproject.business.SavingsAccount;
import com.haigalexanderdilanian.finallabproject.data.Account;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField; 
import javafx.scene.control.TextArea;
import java.math.BigDecimal;
/**
 *
 * @author Haig-Alexander Dilanian
 */
public class UserInterface extends Application{//Initializing everything that's needed.
    private final Account savingsBean;
    private final Account chequingBean; 
    private final ChequingAccount chequingAccount;
    private final SavingsAccount savingsAccount;
    
    public UserInterface() { // Constructor method.
        savingsBean = new Account();
        chequingBean = new Account();
        chequingAccount = new ChequingAccount(new BigDecimal("1000"), new BigDecimal("0.05"),chequingBean);
        savingsAccount = new SavingsAccount(new BigDecimal("1000"), new BigDecimal("0.05"), savingsBean);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banking System");

        // Creating Buttons
        Label titleLabel = new Label("Banking System");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Button savingsButton = new Button("Savings Account");
        Button chequingButton = new Button("Chequing Account");
        Button exitButton = new Button("Exit");
        // Event Handlers
        savingsButton.setOnAction(e -> openSavingsAccount(primaryStage));
        chequingButton.setOnAction(e -> openChequingAccount(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());
        // VBox
        VBox menuLayout = new VBox(10);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(titleLabel, savingsButton, chequingButton, exitButton);
        // Menu Layout
        BorderPane root = new BorderPane();
        root.setCenter(menuLayout);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Open The Savings Account
    private void openSavingsAccount(Stage primaryStage) {
        primaryStage.setTitle("Savings Account");

        // Labels and Text Fields
        Label depositLabel = new Label("Enter deposit amount:");
        TextField depositField = new TextField();
        Button depositButton = new Button("Deposit");
        Label withdrawalLabel = new Label("Enter withdrawal amount:");
        Label confirmationLabel = new Label();
        TextField withdrawalField = new TextField();
        Button withdrawalButton = new Button("Withdrawal");
        Button reportButton = new Button("Generate Report");
        TextArea reportTextArea = new TextArea();
        Button backButton = new Button("Back to Menu");

        // VBox layout
        VBox savingsLayout = new VBox(10);
        savingsLayout.setAlignment(Pos.CENTER);
        savingsLayout.getChildren().addAll(depositLabel, depositField, depositButton,
                                            withdrawalLabel, withdrawalField, withdrawalButton,
                                            confirmationLabel, reportButton, reportTextArea, backButton);

        // Event handlers
        depositButton.setOnAction(e -> {
            BigDecimal depositAmount = new BigDecimal(depositField.getText());
            savingsAccount.makeDeposit(depositAmount);
            confirmationLabel.setText("Deposit of $" + depositAmount + " successful.");
        });

        withdrawalButton.setOnAction(e -> {
            BigDecimal withdrawalAmount = new BigDecimal(withdrawalField.getText());
            boolean withdrawalResult = savingsAccount.makeWithdraw(withdrawalAmount);
            if (withdrawalResult) {
                confirmationLabel.setText("Withdrawal of $" + withdrawalAmount + " successful.");
            } else {
                confirmationLabel.setText("Insufficient funds for withdrawal of $" + withdrawalAmount + ".");
            }
        });

        reportButton.setOnAction(e -> {
            String report = savingsAccount.doMonthlyReport().toString();
            reportTextArea.setText(report);
            savingsAccount.monthlyReset();
        });

        backButton.setOnAction(e -> {
            start(primaryStage);
        });

        Scene savingsScene = new Scene(savingsLayout, 800, 600);
        primaryStage.setScene(savingsScene);
    }

    // Open The Chequing Account
    private void openChequingAccount(Stage primaryStage) {
        primaryStage.setTitle("Chequing Account");

        //Labels and Text Fields
        Label depositLabel = new Label("Enter deposit amount:");
        TextField depositField = new TextField();
        Button depositButton = new Button("Deposit");
        Label withdrawalLabel = new Label("Enter withdrawal amount:");
        Label confirmationLabel = new Label();
        TextField withdrawalField = new TextField();
        Button withdrawalButton = new Button("Withdrawal");
        Button reportButton = new Button("Generate Report");
        TextArea reportTextArea = new TextArea();
        Button backButton = new Button("Back to Menu");

        // VBox Layout
        VBox chequingLayout = new VBox(10);
        chequingLayout.setAlignment(Pos.CENTER);
        chequingLayout.getChildren().addAll(depositLabel, depositField, depositButton,
                                            withdrawalLabel, withdrawalField, withdrawalButton,
                                            confirmationLabel, reportButton, reportTextArea, backButton);

        // Event Handlers
        depositButton.setOnAction(e -> {
            BigDecimal depositAmount = new BigDecimal(depositField.getText());
            chequingAccount.makeDeposit(depositAmount);
            confirmationLabel.setText("Deposit of $" + depositAmount + " successful.");
            // Update UI or display message
        });

        withdrawalButton.setOnAction(e -> {
            BigDecimal withdrawalAmount = new BigDecimal(withdrawalField.getText());
            boolean withdrawalResult = chequingAccount.makeWithdraw(withdrawalAmount);
            if (withdrawalResult) {
                confirmationLabel.setText("Withdrawal of $" + withdrawalAmount + " successful.");
            } else {
                confirmationLabel.setText("Insufficient funds for withdrawal of $" + withdrawalAmount + ".");
            }
        });

        reportButton.setOnAction(e -> {
            String report = chequingAccount.doMonthlyReport().toString();
            reportTextArea.setText(report);
            chequingAccount.monthlyReset();
        });

        backButton.setOnAction(e -> {
            start(primaryStage);
        });

        Scene chequingScene = new Scene(chequingLayout, 800, 600);
        primaryStage.setScene(chequingScene);
    }
}