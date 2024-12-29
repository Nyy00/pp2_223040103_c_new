package MembershipORM.src.view.member;

import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.dao.MemberDao;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import MembershipORM.src.model.JenisMember;
import MembershipORM.src.model.Member;
import MembershipORM.src.view.jenismember.JenisMemberTableModel;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        // Tombol simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 30); // Ubah posisi tombol Simpan

        // Tombol delete
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(130, 160, 100, 30); // Ubah posisi tombol Delete
        buttonDelete.addActionListener(new MemberDeleteButtonActionListener(this, memberDao));

        // Tombol update
        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(245, 160, 100, 30); // Ubah posisi tombol Update
        buttonUpdate.addActionListener(new MemberUpdateButtonActionListener(this, memberDao));

        table = new JTable(); // Inisialisasi JTable
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel); // Set model untuk JTable

        // Tambahkan action listener untuk tombol
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        // Tambahkan komponen ke frame
        this.add(button);
        this.add(buttonDelete);
        this.add(buttonUpdate);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        // Atur ukuran dan tata letak frame
        this.setSize(400, 500);
        this.setLayout(null);

        // Isi combo box dengan data jenis member
        populateComboJenis();
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.addMember(member); // Tambahkan member ke tabel
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public MemberTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getTextFieldNama() {
        return textFieldNama;  // Mengembalikan JTextField untuk input nama
    }

    public JTable getTable() {
        return table;  // Mengembalikan JTable
    }
}
