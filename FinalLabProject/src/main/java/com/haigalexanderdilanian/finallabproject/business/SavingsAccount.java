package com.haigalexanderdilanian.finallabproject.business;
import com.haigalexanderdilanian.finallabproject.data.Account;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author Haig-Alexander Dilanian
 */
public class SavingsAccount {
    private final Account savingsBean;

    public SavingsAccount(BigDecimal balance, BigDecimal annualInterestRate, Account savingsBean ) { // Constructor Method.
        this.savingsBean = savingsBean;
        savingsBean.setStartingBalance(balance);
        savingsBean.setCurrentBalance(balance);
        savingsBean.setAnnualInterestRate(annualInterestRate.add(new BigDecimal("0.05")));        
    }
    
    public void makeDeposit(BigDecimal deposit ) { // Method that permits you to deposit an amount of money in the Savings Account.
        savingsBean.setCurrentBalance(savingsBean.getCurrentBalance().add(deposit));
        savingsBean.setMonthTotalDeposits(savingsBean.getMonthTotalDeposits().add(deposit));
        savingsBean.setNumberOfDeposits(savingsBean.getNumberOfDeposits() + 1);
    }
    
    public boolean makeWithdraw(BigDecimal withdrawal) { // Method that permits you to withdraw an amount of money in the Savings Account.
        if (savingsBean.getCurrentBalance().compareTo(withdrawal) >= 0) {
            BigDecimal serviceCharge = BigDecimal.ZERO;
            if (savingsBean.getNumberOfWithdrawals() >= 4) {
                serviceCharge = new BigDecimal("1.00");
            }
            BigDecimal newBalance = savingsBean.getCurrentBalance().subtract(withdrawal).subtract(serviceCharge);
            if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                savingsBean.setCurrentBalance(newBalance);
                savingsBean.setMonthTotalWithdrawals(savingsBean.getMonthTotalWithdrawals().add(withdrawal));
                savingsBean.setNumberOfWithdrawals(savingsBean.getNumberOfWithdrawals() + 1);
                savingsBean.setMonthServiceCharge(savingsBean.getMonthServiceCharge().add(serviceCharge));
                return true;
            }
        }
        return false;
    }

    public void calculateInterest() { // Method that calculates monthly interest for the Savings Account.
        BigDecimal monthlyInterestRate = savingsBean.getAnnualInterestRate().divide(new BigDecimal("12"), RoundingMode.HALF_UP);
        BigDecimal monthlyInterest = savingsBean.getCurrentBalance().multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
        savingsBean.setMonthInterestAmount(monthlyInterest);
        savingsBean.setCurrentBalance(savingsBean.getCurrentBalance().add(monthlyInterest));
    }
    
    public Account doMonthlyReport() { // Method that writes the monthly report for the Savings Account.
        calculateInterest();
        return savingsBean;
    }
    
    public void monthlyReset() { // Method that resets all account info for the month and updates the current balance as the starting balance.
        savingsBean.setStartingBalance(savingsBean.getCurrentBalance());
        savingsBean.setMonthTotalDeposits(BigDecimal.ZERO);
        savingsBean.setNumberOfDeposits(0);
        savingsBean.setMonthTotalWithdrawals(BigDecimal.ZERO);
        savingsBean.setNumberOfWithdrawals(0);
        savingsBean.setMonthInterestAmount(BigDecimal.ZERO);
        savingsBean.setMonthServiceCharge(BigDecimal.ZERO);
    }
}