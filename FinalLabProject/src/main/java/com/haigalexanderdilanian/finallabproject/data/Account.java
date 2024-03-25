package com.haigalexanderdilanian.finallabproject.data;
import java.math.BigDecimal;
/**
 *
 * @author Haig-Alexander Dilanian
 */
public class Account { // Basic information as instance variables.
    private BigDecimal startingBalance;
    private BigDecimal currentBalance;
    private BigDecimal monthTotalDeposits;
    private int numberOfDeposits;
    private BigDecimal monthTotalWithdrawals;
    private int numberOfWithdrawals;
    private BigDecimal annualInterestRate;
    private BigDecimal monthInterestAmount;
    private BigDecimal monthServiceCharge;


    public Account(){ // Constructor method.
        this.startingBalance = BigDecimal.ZERO;
        this.currentBalance = BigDecimal.ZERO;
        this.monthTotalDeposits = BigDecimal.ZERO;
        this.numberOfDeposits = 0;
        this.monthTotalWithdrawals = BigDecimal.ZERO;
        this.numberOfWithdrawals = 0;
        this.annualInterestRate = BigDecimal.ZERO;
        this.monthInterestAmount = BigDecimal.ZERO;
        this.monthServiceCharge = BigDecimal.ZERO;
    }

    // Setters & Getters for every instance variables
    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }
    
    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }
    
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
    
    public void setMonthTotalDeposits(BigDecimal monthTotalDeposits) {
        this.monthTotalDeposits = monthTotalDeposits;
    }

    public BigDecimal getMonthTotalDeposits() {
        return monthTotalDeposits;
    }

    public void setNumberOfDeposits(int numberOfDeposits) {
        this.numberOfDeposits = numberOfDeposits;
    }
    
    public int getNumberOfDeposits() {
        return numberOfDeposits;
    }
    
    public void setMonthTotalWithdrawals(BigDecimal monthTotalWithdrawals) {
        this.monthTotalWithdrawals = monthTotalWithdrawals;
    }

    public BigDecimal getMonthTotalWithdrawals() {
        return monthTotalWithdrawals;
    }

    public void setNumberOfWithdrawals(int numberOfWithdrawals) {
        this.numberOfWithdrawals = numberOfWithdrawals;
    }
        
    public int getNumberOfWithdrawals() {
        return numberOfWithdrawals;
    }

    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    
    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setMonthInterestAmount(BigDecimal monthInterestAmount) {
        this.monthInterestAmount = monthInterestAmount;
    }
    
    public BigDecimal getMonthInterestAmount() {
        return monthInterestAmount;
    }

    public void setMonthServiceCharge(BigDecimal monthServiceCharge) {
        this.monthServiceCharge = monthServiceCharge;
    }
    
    public BigDecimal getMonthServiceCharge() {
        return monthServiceCharge;
    }

    @Override
    public String toString() { // toString method for the savings account and chequing account reports.
        return "Starting Balance = $" + startingBalance + ", Current Balance = $" + currentBalance + ", Total of Deposits this Month = $" + monthTotalDeposits + ", Number of Deposits = " + numberOfDeposits + ", Total of Withdrawals this Month = $" + monthTotalWithdrawals + ", Number of Withdrawals = " + numberOfWithdrawals + ", Annual Interest Rate = " + annualInterestRate + ", This Month's Interest Amount = $" + monthInterestAmount + ", This Month's Service Charge = $" + monthServiceCharge;
    }
    
}

