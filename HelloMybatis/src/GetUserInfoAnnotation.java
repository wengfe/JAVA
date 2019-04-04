import org.apache.ibatis.annotations.Select;

public interface GetUserInfoAnnotation {
    @Select("select id,name,password,sex,account from user where id = #{id}")
    public User getUser(int id);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
}
