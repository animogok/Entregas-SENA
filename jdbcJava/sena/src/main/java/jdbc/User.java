package jdbc;

public class User {

    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String idNumber;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int get_idUser() {
        return this.idUser;
    }

    public User(int idUser, String name, String surname, String email, String password, String idNumber) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
    }

    public User(String name, String surname, String email, String password, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
    }
}
