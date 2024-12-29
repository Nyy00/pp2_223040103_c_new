package Biodata.src.view.main;

import javax.swing.*;
import java.awt.*;
import Biodata.src.view.biodata.BiodataFrame;
import Biodata.src.dao.BiodataDao;
import Biodata.src.view.main.MainButtonActionListener;

public class MainFrame extends JFrame {
    private BiodataFrame biodataFrame;
    private JButton buttonBiodata;
    private JButton buttonJenisMember; // Deklarasi tombol Jenis Member
    private BiodataDao biodataDao;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(new FlowLayout());

        // Inisialisasi DAO dan frame Biodata
        this.biodataDao = new BiodataDao();
        this.biodataFrame = new BiodataFrame(biodataDao);

        // Membuat dan menambahkan tombol Biodata
        this.buttonBiodata = new JButton("Biodata");
        this.buttonBiodata.addActionListener(e -> showBiodataFrame());
        this.add(buttonBiodata);

        // Membuat dan menambahkan tombol Jenis Member
        this.buttonJenisMember = new JButton("Jenis Member");
        this.add(buttonJenisMember);
    }

    // Method untuk menampilkan BiodataFrame
    public void showBiodataFrame() {
        if (biodataFrame == null) {
            biodataFrame = new BiodataFrame(biodataDao);
        }
        biodataFrame.setVisible(true);
    }

    // Method untuk mendapatkan tombol Jenis Member
    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    // Method untuk menampilkan frame Jenis Member (tambahkan implementasi)
    public void showJenisMemberFrame() {
        // Implementasi untuk menampilkan frame Jenis Member
    }

    // Method untuk menampilkan frame Member (tambahkan implementasi)
    public void showMemberFrame() {
        // Implementasi untuk menampilkan frame Member
        JOptionPane.showMessageDialog(this, "Menampilkan Member Frame");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
