package  Membership.src.model;

public class Member {

  private String id;
  private String nama;
  private JenisMember jenisMember;

  public void setId(String id) {
    this.id = id;

  }

  public void setNama(String nama) {
    this.nama = nama;

  }

  public void setJenisMember(JenisMember jenisMember) {
    this.jenisMember = jenisMember;
  }

  public String getId() {
    return id;
  }

  public String getNama() {
    return nama;
  }
  public JenisMember getJenisMember() {
    return jenisMember;
  }
}