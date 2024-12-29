package MembershipORM.src.view.jenismember;

import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.model.JenisMember;
import javax.swing.*;
import java.util.List;

public class JenisMemberFrame extends JFrame {

    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Jenis Member Management");

        // Label input
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 20, 350, 20);

        // TextField untuk input nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 50, 350, 30);

        // Tombol simpan
        JButton buttonSave = new JButton("Simpan");
        buttonSave.setBounds(15, 90, 100, 30);

        // Tombol delete
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(130, 90, 100, 30);
        buttonDelete.addActionListener(new JenisMemberDeleteButtonActionListener(this, jenisMemberDao));

        // Tombol update
        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(245, 90, 100, 30);
        buttonUpdate.addActionListener(new JenisMemberUpdateButtonActionListener(this, jenisMemberDao));

        // Tabel untuk menampilkan data
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 140, 350, 300);

        // Menyiapkan model tabel
        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Menambahkan listener ke tombol simpan
        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        buttonSave.addActionListener(actionListener);

        // Menambahkan komponen ke frame
        this.add(buttonSave);
        this.add(buttonDelete);
        this.add(buttonUpdate);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);

        // Pengaturan ukuran dan layout frame
        this.setSize(400, 500);
        this.setLayout(null);
    }

    /**
     * Mendapatkan teks nama dari textField.
     *
     * @return nama yang diinputkan
     */
    public String getNama() {
        return textFieldNama.getText();
    }

    /**
     * Menambahkan JenisMember baru ke tabel dan mengosongkan input.
     *
     * @param jenisMember objek JenisMember yang akan ditambahkan
     */
    public void addJenisMember(JenisMember jenisMember) {
        tableModel.addJenisMember(jenisMember);
        textFieldNama.setText("");
    }

    /**
     * Mengupdate data JenisMember pada tabel.
     *
     * @param jenisMember objek JenisMember yang sudah diperbarui
     * @param row         indeks baris yang akan diupdate
     */
    public void updateJenisMember(JenisMember jenisMember, int row) {
        tableModel.update(row, jenisMember);
        textFieldNama.setText("");
    }

    /**
     * Menghapus data JenisMember dari tabel.
     *
     * @param row indeks baris yang akan dihapus
     */
    public void deleteJenisMember(JenisMember jenisMember,int row) {
        tableModel.remove(row);
        textFieldNama.setText("");
    }

    /**
     * Mengambil objek JenisMember yang dipilih pada tabel.
     *
     * @return JenisMember yang dipilih
     */
    public JenisMember getSelectedJenisMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return jenisMemberList.get(selectedRow);
        }
        return null;
    }

    /**
     * Mengembalikan model tabel yang digunakan untuk menampilkan data.
     *
     * @return JenisMemberTableModel yang digunakan
     */
    public JenisMemberTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getTextFieldNama() {
        return textFieldNama;
    }
}
