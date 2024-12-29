package jfc_mvc.src.model;
import java.util.List;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Insert("INSERT INTO users (name, email, nrp, noTelp) VALUES (#{name}, #{email}, #{nrp}, #{noTelp})")
    void insertUser(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email}, nrp = #{nrp}, noTelp = #{noTelp} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(int id);
}
