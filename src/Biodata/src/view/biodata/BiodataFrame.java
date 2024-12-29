package Biodata.src.view.biodata;


import Biodata.src.dao.BiodataDao;
import java.util.*;
import javax.swing.*;
import Biodata.src.model.Biodata;

public class BiodataFrame extends JFrame {
    private JTextField textFieldNama;
    private JTextField textFieldNrp;
    private JTextField textFieldAlamat;
    private JTable table;
    private BiodataTableModel tableModel;
    private BiodataDao biodataDao;

    public BiodataFrame(BiodataDao biodataDao) {
        setTitle("Biodata");
        this.biodataDao = biodataDao;
        List<Biodata> biodataList = biodataDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 150, 20);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 40, 250, 30);

        JLabel labelNrp = new JLabel("NRP:");
        labelNrp.setBounds(15, 80, 150, 20);

        textFieldNrp = new JTextField();
        textFieldNrp.setBounds(15, 100, 250, 30);

        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 140, 150, 20);

        textFieldAlamat = new JTextField();
        textFieldAlamat.setBounds(15, 160, 250, 30);

        JButton buttonSave = new JButton("Simpan");
        buttonSave.setBounds(15, 200, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 200, 100, 40);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(225, 200, 100, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 260, 350, 200);

        tableModel = new BiodataTableModel(biodataList);
        table.setModel(tableModel);

        BiodataButtonSimpanActionListener saveListener = 
        new BiodataButtonSimpanActionListener(this, biodataDao);
        buttonSave.addActionListener(saveListener);

        BiodataButtonUpdateActionListener updateListener = 
        new BiodataButtonUpdateActionListener(this, biodataDao);
        buttonUpdate.addActionListener(updateListener);

        BiodataButtonDeleteActionListener deleteListener = 
        new BiodataButtonDeleteActionListener(this, biodataDao);
        buttonDelete.addActionListener(deleteListener);

        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelNrp);
        this.add(textFieldNrp);
        this.add(labelAlamat);
        this.add(textFieldAlamat);
        this.add(buttonSave);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
        this.setVisible(true);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public String getNrp() {
        return textFieldNrp.getText();
    }

    public String getAlamat() {
        return textFieldAlamat.getText();
    }

    public Biodata getSelectedBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return tableModel.getBiodataAt(selectedRow);
        }
        return null;
    }

    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata);
        clearFields();
    }

    public void updateBiodata(Biodata biodata) {
        tableModel.update(biodata);
        clearFields();
    }

    public void deleteBiodata(Biodata biodata) {
        tableModel.remove(biodata);
        clearFields();
    }

    private void clearFields() {
        textFieldNama.setText("");
        textFieldNrp.setText("");
        textFieldAlamat.setText("");
    }
}
