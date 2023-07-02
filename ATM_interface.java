import java.util.ArrayList;
import java.util.Scanner;

class user {
    String name;
    String username;
    String ATMpin;
    String accountno;
    float balance = 0f;
    ArrayList<String> transactionHistory = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        for (int i = 0; i < strNum.length(); i++) {
            if (!Character.isDigit(strNum.charAt(i)))
                return false;
        }
        return true;
    }

    public static String setname() {
        String name = "";
        boolean seted = false;
        while (!seted) {
            name = sc.nextLine();
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetter(name.charAt(i))) {
                    seted = false;
                    break;
                }
                seted = true;
            }
            if (seted == false) {
                System.out.print("\033[31m");
                System.out.println("Inavalid Name .... !");
                System.out.println("\033[0m");
                System.out.print("Enter Proper Name : ");
            }
        }

        return name;
    }

    public static String setuserName() {
        String name = "";
        boolean seted = false;
        while (!seted) {
            name = sc.nextLine();
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetterOrDigit(name.charAt(i))) {
                    seted = false;
                    break;
                }
                seted = true;
            }
            if (seted == false) {
                System.out.print("\033[31m");
                System.out.println("Inavalid Username .... !");
                System.out.println("\033[0m");
                System.out.print("Enter Proper Username : ");
            }
        }

        return name;
    }

    public static String setpassword() {
        String password = "";
        Boolean seted = false;
        while (!seted) {
            password = sc.nextLine();
            if (!isNumeric(password)) {
                System.out.print("\033[31m");
                System.out.println("PIN must be in form of Digit");
                System.out.println("\033[0m");
                seted = false;
            } else if (password.length() != 4) {
                System.out.print("\033[31m");
                System.out.println("PIN must be of 4 Digit");
                System.out.println("\033[0m");
                seted = false;
            } else {
                seted = true;
                return password;
            }
            System.out.print("Enter Password Again : ");
        }
        return password;
    }

    public static String setaccno() {
        String accno = "";
        Boolean seted = false;
        while (!seted) {
            accno = sc.nextLine();
            if (!isNumeric(accno)) {
                System.out.print("\033[31m");
                System.out.println("Account Number must be in form of Digit");
                System.out.println("\033[0m");
                seted = false;
            } else if (accno.length() != 12) {
                System.out.print("\033[31m");
                System.out.println("Account Number must be of 12 Digit");
                System.out.println("\033[0m");
                seted = false;
            } else {
                seted = true;
                return accno;
            }
            System.out.print("Enter Account Number Again : ");
        }
        return accno;
    }

    public void signup() {

        System.out.print("Enter your Name : ");
        name = setname();
        System.out.print("Enter your Username : ");
        username = setuserName();
        System.out.print("Enter yout Account No : ");
        accountno = setaccno();
        System.out.print("Enter your ATM pin : ");
        ATMpin = setpassword();
        System.out.print("\033[32m \033[3m");
        System.out.println("\nSuccesfully SignUp ...");
        System.out.println("\033[0m");
    }

    public boolean signin() {
        boolean islogin = false;

        while (!islogin) {
            System.out.print("Enter your Username : ");
            String uname = sc.nextLine();
            System.out.print("Enter ATM pin : ");
            String ps = sc.nextLine();

            if (uname.equals(this.username) && ps.equals(this.ATMpin)) {
                System.out.print("\033[32m \033[3m");
                System.out.println("\nSuccesfully Login...");
                System.out.println("\033[0m");
                System.out.println("\033[33m");
                System.out.println("Welcome " + name);
                System.out.println("\033[0m");
                islogin = true;
            } else {
                System.out.print("\033[31m");
                System.out.println("Invalid Credentials \n");
                System.out.println("\033[0m");
                islogin = false;
            }
        }
        return islogin;
    }

}

class ATM extends user {

    public float checkBalance() {
        return balance;
    }
    

    public void withdraw() {
        System.out.println("\033[34m");
        System.out.println("Withdraw : ");
        System.out.print("\033[0m");
        System.out.print("Enter Amount : ");
        float amount = sc.nextFloat();

        if (amount < 0) {
            System.out.print("\033[31m");
            System.out.println("Invalid Amount");
            System.out.println("\033[0m");
            return;
        }
        else if (checkBalance() < amount) {
            System.out.print("\033[31m");
            System.out.println("Not Sufficient Balance ");
            System.out.println("\033[0m");
        } else {
            System.out.print("\033[32m \033[3m");
            System.out.println("Succesfully Withdraw " + amount + "Rs.");
            System.out.println("\033[0m");
            balance -= amount;
            String trancation = "Rs. " + amount + " is withdraw ";
            transactionHistory.add(trancation);
        }

    }

    public void transactionHistoryprint() {
        System.out.println("\033[34m");
        System.out.println("Your transaction History : ");
        System.out.print("\033[0m");
        for (String i : transactionHistory) {
            System.out.println(i);
        }
        System.out.println();
    }

    public void deposit() {
        System.out.println("\033[34m");
        System.out.println("Deposit : ");
        System.out.print("\033[0m");
        System.out.print("Enter your Amount : ");
        float amount = sc.nextFloat();
        if (amount < 0) {
            System.out.print("\033[31m");
            System.out.println("Invalid Amount");
            System.out.println("\033[0m");
            return;
        } else {
            System.out.print("\033[32m\033[3m");
            System.out.print("Rs " + amount + " Deposit Succesfully ");
            System.out.println("\033[0m\n");
            balance += amount;
            String transaction = "Rs. " + amount + " is Deposit";
            transactionHistory.add(transaction);
        }
    }

    public void transfer() {
        System.out.println("\033[34m");
        System.out.println("Transfer : ");
        System.out.print("\033[0m");
        System.out.print("Enter Receipent's Account No : ");
        sc.nextLine();
        String accno = setaccno();
        System.out.print("Enter Amount : ");
        float amount = sc.nextFloat();
        if (amount < 0) {
            System.out.print("\033[31m");
            System.out.println("Invalid Amount");
            System.out.println("\033[0m");
            return;
        } else if (amount > checkBalance()) {
            System.out.print("\033[31m");
            System.out.println("Not Sufficient Balance to Transfer ");
            System.out.print("Transaction Fail....!");
            System.out.println("\033[0m\n");
        } else {
            System.out.print("\033[32m \033[3m");
            System.out.print("Rs " + amount + " Transfer Succesfully...");
            System.out.println("\033[0m\n");
            balance -= amount;
            String transaction = "Rs. " + amount + " is Tranfer To Account no  " + accno;
            transactionHistory.add(transaction);
        }
    }
}

public class ATM_interface {

    public static int takeChoice(int limit) {
    int input = 0;
    boolean set = false;

    while (!set) {
      try {
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        set = true;

        if (set && input > limit || input < 1) {
            System.out.print("\033[31m");
          System.out.println("Choose the number between 1 to " + limit);
          System.out.println("\033[0m");
          System.out.print("Enter Proper Value : ");
          set = false;
        }
      } catch (Exception e) {
        System.out.print("\033[31m");
        System.out.println("Enter only integer value !");
        System.out.print("\033[0m\n");
        System.out.print("Enter Proper Value : ");
        set = false;
      }
    }
    return input;
  }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        user atmusers = new user();
        ATM atm = new ATM();
        System.out.println("--------------------");
        System.out.println("|\033[33m  WELCOME TO ATM  \033[0m|");
        System.out.println("--------------------\n");

        System.out.println("\033[4mRegister Yourself\033[0m\n");
        atmusers.signup();

        System.out.println("\n1.Login now \n2.Exit");
        System.out.print("Enter Your Choice : ");
        int choiceno = takeChoice(2);
    
        if (choiceno == 1) {
            System.out.println("\n\033[4mLogin To Account\033[0m\n");
            atmusers.signin();
            while (true) {
                System.out.println(
                        "1) Account Balance \n2) Deposit \n3) Withdraw \n4) Transfer \n5) Transaction History\n6) Exit\n");
                        System.out.print("Enter Your Choice :");
                int choice = takeChoice(6);

                switch (choice) {
                    case 1:
                        System.out.println("\033[34m");
                        System.out.println("Account Balance : " + atm.checkBalance());
                        System.out.println("\033[0m");
                        break;
                    case 2:
                        atm.deposit();
                        break;
                    case 3:
                        atm.withdraw();
                        break;
                    case 4:
                        atm.transfer();
                        break;
                    case 5:
                        atm.transactionHistoryprint();
                        break;
                    case 6:
                        System.out.print("\033[33m");
                        System.out.println("\nThank you for visit.");
                        System.out.println("\033[0m");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\033[0m");
                        System.out.println("Invalid Choice ....!");
                        System.out.println("\033[0m");
                        break;
                }
            }
        }
        if(choiceno == 2)
        System.exit(0);

    }
}