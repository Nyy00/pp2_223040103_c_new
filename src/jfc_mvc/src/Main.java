package jfc_mvc.src;

import jfc_mvc.src.model.MyBatisUtil;
import jfc_mvc.src.model.UserMapper;
import org.apache.ibatis.session.SqlSession;

import jfc_mvc.src.view.UserPdf;
import jfc_mvc.src.view.UserView;
import jfc_mvc.src.controller.UserController;
/**
 *
 * @author thega
 */
public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserPdf pdf = new UserPdf();

        UserView view = new UserView();
        new UserController(view, mapper, pdf);

        view.setVisible(true);
    }
}