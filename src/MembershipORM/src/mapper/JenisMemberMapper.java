package MembershipORM.src.mapper;

import java.util.List;
import MembershipORM.src.model.JenisMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface JenisMemberMapper {

  @Insert("INSERT INTO jenis_member (id, nama) VALUES (#{id}, #{nama})")
  int insert(JenisMember jenisMember);

  @Update("UPDATE jenis_member SET nama = #{nama} WHERE id = #{id}")
  int update(JenisMember jenisMember);

  @Delete("DELETE FROM jenis_member WHERE id = #{id}")
  int delete(String id); // Tipe data String

  @Select("SELECT * FROM jenis_member")
  @Results({
          @Result(property = "id", column = "id"),
          @Result(property = "nama", column = "nama")
  })
  List<JenisMember> findAll();

  @Select("SELECT * FROM jenis_member WHERE id = #{id}")
  @Results({
          @Result(property = "id", column = "id"),
          @Result(property = "nama", column = "nama")
  })
  JenisMember getJenisMember(String id); // Tipe data String
}
