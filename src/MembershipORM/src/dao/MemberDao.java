package MembershipORM.src.dao;

import java.util.List;
import MembershipORM.src.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {

  private final SqlSessionFactory sqlSessionFactory;

  public MemberDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public int insert(Member member) {
    int result;
    try (SqlSession session = sqlSessionFactory.openSession()) {
      result = session.insert("MembershipORM.src.mapper.MemberMapper.insert", member);
      session.commit();
    }
    return result;
  }

  public int update(Member member) {
    int result;
    try (SqlSession session = sqlSessionFactory.openSession()) {
      result = session.update("MembershipORM.src.mapper.MemberMapper.update", member);
      session.commit();
    }
    return result;
  }

  public int delete(int id) {
    int result;
    try (SqlSession session = sqlSessionFactory.openSession()) {
      result = session.delete("MembershipORM.src.mapper.MemberMapper.delete", id);
      session.commit();
    }
    return result;
  }

  public List<Member> findAll() {
    List<Member> result;
    try (SqlSession session = sqlSessionFactory.openSession()) {
      result = session.selectList("MembershipORM.src.mapper.MemberMapper.findAll");
    }
    return result;
  }
}
