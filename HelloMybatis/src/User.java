public class User {
    private int id;
    private String name;
    private String password;
    private int sex;
    private int account;

    public User(int id, String name, String password, int sex, int account) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswors() {
        return password;
    }

    public int getSex() {
        return sex;
    }

    public int getAccount() {
        return account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswors(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", account=" + account +
                '}';
    }
}
