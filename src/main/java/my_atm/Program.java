package my_atm;
import my_atm.csv.CSVFileStream;
import my_atm.models.User;
import my_atm.utils.Input;


public class Program {
    public static void main(String[] args) {
        System.out.println("*** Welcome to ATM SYSTEM ***");
        try{
            while(true){
                System.out.print("Enter your account number: ");
                String acc_num = Input.readString().trim();
                System.out.print("Enter your pin: ");
                int pin = Input.readInt();
                Input.readString();
                User u = CSVFileStream.get(acc_num, pin);
                if(u != null){
                    while(true) {
                        System.out.println("[1] Withdraw");
                        System.out.println("[2] Statement");
                        System.out.println("[3] Send money");
                        System.out.println("[5] Logout");
                        System.out.print("Enter your choice: ");
                        String answ = Input.readString().trim();
                        if(answ.equals("1")){
                            System.out.println("Enter amount you wish to withdraw: ");
                            double amount = Input.readDouble();
                            Input.readString();
                            CSVFileStream.withDraw(u, amount);
                        }
                        else  if(answ.equals("2")){
                            CSVFileStream.statement(u);
                        }
                        else if(answ.equals("3")){
                            CSVFileStream.sendMoney(u);
                        }
                        else if(answ.equals("5")){
                            u = null;
                            break;
                        }else{
                            System.out.println("Invalid choice!");
                        }
                    }
                }else{
                    System.err.println("Account number/pin is invalid!");
                }
        }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
