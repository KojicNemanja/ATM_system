package my_atm.models;

public class User {
    private String first_name, last_name, account_number;
    private int pin;
    private double balance;

    public static User userFromLine(String line){
        String data[] = line.split(",");
        return new User(data[0], data[1], data[2],
                Integer.parseInt(data[3]), Double.parseDouble(data[4]));
    }

    public User(String first_name, String last_name, String account_number, int pin, double balance) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_number = account_number;
        this.pin = pin;
        this.balance = balance;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toLine(){
        String s = this.first_name + "," + this.last_name + "," + this.account_number + "," +
                this.pin + "," + this.balance;
        return s;
    }

    public String toString(){
        String s = String.format("%-15s%-20s%-20s%-4s%15s",
                this.first_name, this.last_name, this.account_number, this.pin, this.balance);
        return s;
    }
}
