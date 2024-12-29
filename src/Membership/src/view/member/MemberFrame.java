package  Membership.src.view.member;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import  Membership.src.model.*;
import  Membership.src.dao.MemberDao;
import  Membership.src.dao.JenisMemberDao;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private JTable table;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        setTitle("Membership Management"); // Judul utama jendela
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        // Mengambil data Member dan JenisMember dari database
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        // Label untuk input nama
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        // Text field untuk input nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Label untuk combo box Jenis Member
        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);

        // JComboBox untuk memilih Jenis Member
        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        // Tombol Simpan
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 160, 100, 40);

        // Tombol Update
        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(130, 160, 100, 40);

        // Tombol Delete
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(245, 160, 100, 40);

        // Tabel untuk menampilkan daftar Member
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        // Model tabel untuk daftar Member
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Listener untuk tombol Simpan
        buttonSimpan.addActionListener(new MemberButtonSimpanActionListener(this, memberDao));

        // Listener untuk tombol Update
        buttonUpdate.addActionListener(new MemberButtonUpdateActionListener(this, memberDao));

        // Listener untuk tombol Delete
        buttonDelete.addActionListener(new MemberButtonDeleteActionListener(this, memberDao));

        // Menambahkan komponen ke JFrame
        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
        this.setVisible(true);

        // Memuat data Jenis Member ke ComboBox
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
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void updateMember(Member member) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            memberList.set(selectedRow, member);
            tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
        }
        textFieldNama.setText("");
    }

    public void deleteMember(Member member) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            memberList.remove(selectedRow);
            tableModel.fireTableRowsDeleted(selectedRow, selectedRow);
        }
        textFieldNama.setText("");
    }

    public Member getSelectedMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            return memberList.get(selectedRow);
        }
        return null;
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void refreshMemberTable() {
        memberList = memberDao.findAll();
        tableModel.setData(memberList);
        tableModel.fireTableDataChanged();
    }
}