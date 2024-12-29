package  Membership.src.dao;

import  Membership.src.db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import  Membership.src.model.Member;
import  Membership.src.model.JenisMember;

public class MemberDao {
  public int insert(Member member) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "insert into member (id, nama, jenis_member_id) values (?, ?, ?)");
      statement.setString(1, member.getId());
      statement.setString(2, member.getNama());
      statement.setString(3, member.getJenisMember().getId());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
    
  // Bagian 2: Method update dan delete

public int update(Member member) {
  int result = -1;
  try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "update member set nama = ?, jenis_member_id = ? where id = ?"
      );
      statement.setString(1, member.getNama());
      statement.setString(2, member.getJenisMember().getId());
      statement.setString(3, member.getId());

      result = statement.executeUpdate();
  } catch (SQLException e) {
      e.printStackTrace();
  }
  return result;
}

public int delete(Member member) {
  int result = -1;
  try (Connection connection = MySqlConnection.getInstance().getConnection()) {
    PreparedStatement statement = connection.prepareStatement(
        "delete from member where id = ?");
    statement.setString(1, member.getId());

    result = statement.executeUpdate();
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return result;
}

// Bagian 3: Method findAll

public List<Member> findAll() {
  List<Member> list = new ArrayList<>();
  try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      try (Statement statement = connection.createStatement()) {
          ResultSet resultSet = statement.executeQuery(
              "select member.id, member.nama, jenis_member.id jenis_member_id, jenis_member.nama jenis_member_nama " +
              "from member join jenis_member on jenis_member.id = member.jenis_member_id"
          );

          // Mengambil data
          while (resultSet.next()) {
              Member member = new Member();
              member.setId(resultSet.getString("id"));
              member.setNama(resultSet.getString("nama"));

              JenisMember jenisMember = new JenisMember();
              jenisMember.setId(resultSet.getString("jenis_member_id"));
              jenisMember.setNama(resultSet.getString("jenis_member_nama"));

              member.setJenisMember(jenisMember);
              list.add(member);
          }
      }
  } catch (SQLException e) {
      e.printStackTrace();
  }
  return list;
}


}
