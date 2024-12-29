package MembershipORM.src.model;

public class Member {
  private String id; // UUID
  private String nama;
  private String jenisMemberId; // UUID
  private JenisMember jenisMember;

  // Getters and Setters
  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getNama() {
    return nama;
  }

  public void setJenisMemberId(String jenisMemberId) {
    this.jenisMemberId = jenisMemberId;
  }

  public String getJenisMemberId() {
    return jenisMemberId;
  }

  public void setJenisMember(JenisMember jenisMember) {
    this.jenisMember = jenisMember;
  }

  public JenisMember getJenisMember() {
    return jenisMember;
  }
}
