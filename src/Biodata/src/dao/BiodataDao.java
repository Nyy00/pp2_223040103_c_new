package Biodata.src.dao;

import Biodata.src.db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import Biodata.src.model.Biodata;

public class BiodataDao {

  // Method untuk insert data Biodata
  public int insert(Biodata biodata) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "insert into biodata (id, nama, nrp, alamat) values (?, ?, ?, ?)");
      statement.setString(1, biodata.getId());
      statement.setString(2, biodata.getNama());
      statement.setString(3, biodata.getNrp());
      statement.setString(4, biodata.getAlamat());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  // Method untuk update data Biodata
  public int update(Biodata biodata) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "update biodata set nama = ?, nrp = ?, alamat = ? where id = ?");
      statement.setString(1, biodata.getNama());
      statement.setString(2, biodata.getNrp());
      statement.setString(3, biodata.getAlamat());
      statement.setString(4, biodata.getId());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  // Method untuk delete data Biodata
  public int delete(Biodata biodata) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "delete from biodata where id = ?");
      statement.setString(1, biodata.getId());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  // Method untuk mendapatkan semua data Biodata
  public List<Biodata> findAll() {
    List<Biodata> list = new ArrayList<>();
    try (Connection connection = MySqlConnection.getInstance().getConnection()) {
      try (Statement statement = connection.createStatement()) {
        ResultSet resultSet = statement.executeQuery(
            "select id, nama, nrp, alamat from biodata");

        // Mengambil data
        while (resultSet.next()) {
          Biodata biodata = new Biodata();
          biodata.setId(resultSet.getString("id"));
          biodata.setNama(resultSet.getString("nama"));
          biodata.setNrp(resultSet.getString("nrp"));
          biodata.setAlamat(resultSet.getString("alamat"));

          list.add(biodata);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
}
