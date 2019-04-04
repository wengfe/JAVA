public interface GetUserInfo {
    public User getUser(int id);
    public void addUser(User user);
    public void updateUserAccount(User user);
    public void deleteUser(int id);
}
