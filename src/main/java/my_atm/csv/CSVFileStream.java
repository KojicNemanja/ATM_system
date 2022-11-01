package my_atm.csv;

import my_atm.models.User;
import my_atm.utils.Input;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVFileStream {
    private static String env_path = System.getenv("JAVA_RESOURCES_E") + "/atm/users.csv";
    private static Path file_path = Path.of(env_path);
    private static List<User> users = null;
    private static boolean changes = true;

    public static List<User> load() throws IOException {
        if (changes == false) return users;
        users = new ArrayList<>();
        System.out.println("Read csv...");
        Files.lines(file_path, StandardCharsets.UTF_8)
                .skip(1)
                .map(User::userFromLine)
                .forEach(user -> { users.add(user); });
        changes = false;
        return users;
    }

    private static void write(List<User> users) throws IOException {
        FileWriter writer = new FileWriter(file_path.toFile());
        PrintWriter printWriter = new PrintWriter(writer);
        String header = "first_name,last_name,account_number,pin,balance\n";
        printWriter.print(header);
        users.forEach(user -> { printWriter.println(user.toLine()); });
        printWriter.close();
        writer.close();
        changes = true;
    }

    public static User get(String acc_num, int pin) throws IOException {
        List<User> users = load();
        for(User u : users){
            if(u.getAccount_number().equals(acc_num)){
                if(u.getPin() == pin) return u;
            }
        }
        return null;
    }

    public static void withDraw(User user, double amount) throws IOException {
        List<User> users = load();
        for(User u : users){
            if(u.getPin() == user.getPin()){
                if(user.getBalance() < amount){
                    System.err.println("You don't have enough money!");
                }else {
                    u.setBalance(u.getBalance() - amount);
                    user = u;
                }
                break;
            }
        }
        write(users);
    }

    public static void statement(User user){
        String format = "%-20s%-10s\n";
        String s = String.format(format,"First name:", user.getFirst_name());
        s += String.format(format, "Last name:", user.getLast_name());
        s += String.format(format, "Account number:", user.getAccount_number());
        s += String.format(format, "Your balance:", user.getBalance());
        System.out.println(s);
    }

    public static void sendMoney(User current_user) throws IOException {
        System.out.print("Enter account number on you wish to send money: ");
        String acc_num = Input.readString();
        List<User> users = load();
        User userToSend = null;
        for (User u : users){
            if(u.getAccount_number().equals(acc_num)){
                userToSend = u;
                break;
            }
        }
        if (userToSend != null){
            System.out.println(userToSend.getFirst_name());
            System.out.println(userToSend.getLast_name());
            System.out.print("Enter amount: ");
            double amount = Input.readDouble();
            Input.readString();
            if(current_user.getBalance() < amount){
                System.err.println("You have not enough money");
            }
            else {
                userToSend.setBalance(
                        userToSend.getBalance() + amount
                );
                current_user.setBalance(
                        current_user.getBalance() - amount
                );
                write(users);
                System.out.println("Money is successfully sent");
            }
        }
    }
}
