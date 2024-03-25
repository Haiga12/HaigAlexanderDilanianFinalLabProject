package com.haigalexanderdilanian.finallabproject.business;
import com.haigalexanderdilanian.finallabproject.data.Account;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author Haig-Alexander Dilanian
 */
public class ChequingAccount {
    private final Account chequingBean;
    
    public ChequingAccount(BigDecimal balance, BigDecimal annualInterestRate, Account chequingBean ) { // Constructor Method.
        this.chequingBean = chequingBean;
        chequingBean.setStartingBalance(balance);
        chequingBean.setCurrentBalance(balance);
        chequingBean.setAnnualInterestRate(annualInterestRate.add(new BigDecimal("0.05")));
        
    }
    
    public void makeDeposit(BigDecimal deposit ) { // Method that permits you to deposit an amount of money in the Chequing Account.
        chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().add(deposit));
        chequingBean.setMonthTotalDeposits(chequingBean.getMonthTotalDeposits().add(deposit));
        chequingBean.setNumberOfDeposits(chequingBean.getNumberOfDeposits() + 1);
    }
    
    public boolean makeWithdraw(BigDecimal withdrawal) { // Method that permits you to withdraw an amount of money in the Chequing Account.
        BigDecimal serviceCharge = new BigDecimal("0.50");
        BigDecimal newBalance = chequingBean.getCurrentBalance().subtract(withdrawal).subtract(serviceCharge);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            chequingBean.setCurrentBalance(newBalance);
            chequingBean.setMonthTotalWithdrawals(chequingBean.getMonthTotalWithdrawals().add(withdrawal));
            chequingBean.setNumberOfWithdrawals(chequingBean.getNumberOfWithdrawals() + 1);
            chequingBean.setMonthServiceCharge(chequingBean.getMonthServiceCharge().add(serviceCharge));
            return true;
        }
        return false;
    }

    public void calculateInterest() { // Method that calculates monthly interest for the Chequing Account.
        BigDecimal monthlyInterestRate = chequingBean.getAnnualInterestRate().divide(new BigDecimal("12"), RoundingMode.HALF_UP);
        BigDecimal monthlyInterest = chequingBean.getCurrentBalance().multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
        chequingBean.setMonthInterestAmount(monthlyInterest);
        chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().add(monthlyInterest));
    }
    
    public Account doMonthlyReport() { // Method that writes the monthly report for the Chequing Account.
        chequingBean.setMonthServiceCharge(chequingBean.getMonthServiceCharge().add(new BigDecimal("5.00")));
        chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().subtract(new BigDecimal("5.00")));
        calculateInterest();
        return chequingBean;
    }
    
    public void monthlyReset() { // Method that resets all account info for the month and updates the current balance as the starting balance.
        chequingBean.setStartingBalance(chequingBean.getCurrentBalance());
        chequingBean.setMonthTotalDeposits(BigDecimal.ZERO);
        chequingBean.setNumberOfDeposits(0);
        chequingBean.setMonthTotalWithdrawals(BigDecimal.ZERO);
        chequingBean.setNumberOfWithdrawals(0);
        chequingBean.setMonthServiceCharge(BigDecimal.ZERO);
    }
    
}

