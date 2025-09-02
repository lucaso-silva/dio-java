package exercise3;

import java.util.ArrayList;
import java.util.Scanner;

public class
AccTester {
    public static void main(String[] args) {
        int accCount = 1000;
        Scanner input = new Scanner(System.in);
        int option;
        ArrayList<Account> accounts = new ArrayList<>();

        do {
            System.out.println("""
                Hello. Choose one option:
                \t1. Create new account
                \t2. Deposit money
                \t3. Withdraw money
                \t4. Check balance
                \t5. Check special limit
                \t6. Pay a count
                \t7. Check active accounts
                \t0. Exit"""
            );

            option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter the initial account value");
                    double value = input.nextDouble();
                    Account acc = new Account(accCount, value);
                    accounts.add(acc);
                    accCount++;
                    break;
                case 2:
                    System.out.println("Option 2");
                    break;
                case 3:
                    System.out.println("Option 3");
                    break;
                case 4:
                    System.out.println("Option 4");
                    break;
                case 5:
                    System.out.println("Option 5");
                    break;
                case 6:
                    System.out.println("Option 6");
                    break;
                case 7:
                    if(!accounts.isEmpty()){
                        for(Account a : accounts){
                            System.out.println("acc: " + a.getAccNum() + ", balance: " + a.getBalance() + ", extra limit: " + a.getExtraLimit());
                        }
                    } else {
                        System.out.println("There is no account created");
                    }
                    break;
                case 0:
                    System.out.println("Finishing program");
                    break;
                default:
                    System.out.println("Incorrect option");
            }
        }while (option != 0);

    }
}
