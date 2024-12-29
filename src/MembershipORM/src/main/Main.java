package MembershipORM.src.main;

import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.dao.MemberDao;
import MembershipORM.src.view.main.MainFrame;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        // Lokasi file konfigurasi MyBatis
        String resource = "MembershipORM/src/resource/mybatis-config.xml";

        // Debugging: Coba memuat file konfigurasi
        try {
            System.out.println("Mencoba memuat konfigurasi MyBatis dari: " + resource);
            InputStream inputStream = Resources.getResourceAsStream(resource);
            System.out.println("File konfigurasi ditemukan.");

            // Membuat SqlSessionFactory dari file konfigurasi
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("SqlSessionFactory berhasil dibuat.");

            // Membuat DAO menggunakan SqlSessionFactory
            JenisMemberDao jenisMemberDao = new JenisMemberDao(sqlSessionFactory);
            MemberDao memberDao = new MemberDao(sqlSessionFactory);
            System.out.println("DAO berhasil dibuat.");

            System.out.println("Mapper yang terdaftar: " + sqlSessionFactory.getConfiguration().getMappedStatementNames());


            // Membuat dan menampilkan MainFrame
            MainFrame f = new MainFrame(jenisMemberDao, memberDao);
            javax.swing.SwingUtilities.invokeLater(() -> {
                f.setVisible(true);
                System.out.println("Aplikasi berhasil dijalankan.");
            });
        } catch (IOException e) {
            System.err.println("Error: Tidak dapat memuat file konfigurasi MyBatis.");
            System.err.println("Detail error:");
            e.printStackTrace();

            // Memberikan petunjuk kepada pengguna
            System.err.println("Pastikan file 'mybatis-config.xml' berada di folder yang sesuai (classpath).");
            System.err.println("Jika Anda menggunakan IntelliJ IDEA:");
            System.err.println("- Letakkan file di 'src/main/resources' (untuk proyek Maven/Gradle).");
            System.err.println("- Tandai folder tersebut sebagai 'Resources Root'.");
        } catch (Exception e) {
            System.err.println("Terjadi error saat menjalankan aplikasi:");
            e.printStackTrace();
        }
    }
}
