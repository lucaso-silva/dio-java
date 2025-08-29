package exercise3;

import java.lang.Math;

/*
*
*
*/

public class Account {
    private int accNum;
    private double balance;
    private double extraLimit;
    private double totalLimit;
    private boolean usingExtraLimit;

    public Account(int accNum, double amount){
        this.accNum = accNum;
        this.balance = amount;
        if(amount <= 500){
            this.extraLimit = 50;
        }else {
            this.extraLimit = amount/2;
        }
        this.totalLimit = amount + extraLimit;
        usingExtraLimit = false;
    }

    public int getAccNum() {
        return accNum;
    }

    public double getBalance(){
        return balance;
    }

    public double getExtraLimit(){
        return extraLimit;
    }

    public void deposit(double value){
        if(!usingExtraLimit){
            this.balance += value;
        }else{
            this.balance += (value - Math.abs(balance * (double)20/100));
        }
    }

    public void withdraw(double value){
        if(value <= this.balance){
            this.balance -= value;
        }else{
            usingExtraLimit = true;
            if(value <= this.totalLimit){
                this.totalLimit -= value;
                this.balance -= value;
            }else{
                System.out.println("Insufficient funds");
            }
        }

    }

    public boolean isUsingExtraLimit(){
        return usingExtraLimit;
    }
}
