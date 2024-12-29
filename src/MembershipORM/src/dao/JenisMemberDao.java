package MembershipORM.src.dao;

import java.util.List;
import MembershipORM.src.model.JenisMember;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import MembershipORM.src.mapper.JenisMemberMapper;

public class JenisMemberDao {

    private final SqlSessionFactory sqlSessionFactory;

    public JenisMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.insert("MembershipORM.src.mapper.JenisMemberMapper.insert", jenisMember);
            session.commit();
        }
        return result;
    }

    public int update(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.update("MembershipORM.src.mapper.JenisMemberMapper.update", jenisMember);
            session.commit();
        }
        return result;
    }

    public int delete(int jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("MembershipORM.src.mapper.JenisMemberMapper.delete", jenisMember);
            session.commit();
        }
        return result;
    }

    public List<JenisMember> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            JenisMemberMapper mapper = session.getMapper(JenisMemberMapper.class);
            return mapper.findAll();
        }
    }
}
