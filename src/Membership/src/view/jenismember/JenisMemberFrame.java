package  Membership.src.view.jenismember;
import  Membership.src.dao.JenisMemberDao;
import java.util.*;
import javax.swing.*;
import  Membership.src.model.JenisMember;

public class JenisMemberFrame extends JFrame {
    private JTextField textFieldNama;
    private JTable table;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        setTitle("Membership");
        this.jenisMemberDao = jenisMemberDao;
        List<JenisMember> jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 150, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 250, 30);

        JButton buttonSave = new JButton("Simpan");
        buttonSave.setBounds(15, 100, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 100, 100, 40);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(225, 100, 100, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        JenisMemberButtonSimpanActionListener saveListener = 
        new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        buttonSave.addActionListener(saveListener);

        JenisMemberButtonUpdateActionListener updateListener = 
        new JenisMemberButtonUpdateActionListener(this, jenisMemberDao);
        buttonUpdate.addActionListener(updateListener);

        JenisMemberButtonDeleteActionListener deleteListener = 
         new JenisMemberButtonDeleteActionListener(this, jenisMemberDao);
        buttonDelete.addActionListener(deleteListener);

        this.add(buttonSave);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
        this.setVisible(true);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getSelectedJenisMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return tableModel.getJenisMemberAt(selectedRow);
        }
        return null;
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }

    public void updateJenisMember(JenisMember jenisMember) {
        tableModel.update(jenisMember);
        textFieldNama.setText("");
    }

    public void deleteJenisMember(JenisMember jenisMember) {
        tableModel.remove(jenisMember);
        textFieldNama.setText("");
    }
}