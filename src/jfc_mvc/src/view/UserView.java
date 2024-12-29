package jfc_mvc.src.view;

import jfc_mvc.src.controller.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtNrp = new JTextField(20);
    private JTextField txtNoTelp = new JTextField(20);
    private JButton btnAdd = new JButton("Add User");
    private JButton btnUpdate = new JButton("Update User");
    private JButton btnDelete = new JButton("Delete User");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnExport = new JButton("Export PDF");
    private JButton btnProcessData = new JButton("Process Data Concurrently");
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton startButton = new JButton("Mulai");
    private JProgressBar progressBar = new JProgressBar(0, 60);
    private JLabel statusLabel = new JLabel("Tekan tombol memulai", JLabel.CENTER);

    public UserView() {
        setTitle("User Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel input
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("NRP:"));
        panel.add(txtNrp);
        panel.add(new JLabel("No Telepon:"));
        panel.add(txtNoTelp);

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnExport);
        buttonPanel.add(btnProcessData); // Tambahkan tombol baru
        buttonPanel.add(startButton);
        panel.add(buttonPanel);

        // Tabel data pengguna
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "NRP", "No Telp"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        JPanel statusPanel = new JPanel();
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(progressBar, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }

    // Getter untuk input
    public String getNameInput() {
        return txtName.getText();
    }

    public String getEmailInput() {
        return txtEmail.getText();
    }

    public String getNrpInput() {
        return txtNrp.getText();
    }

    public String getNoTelpInput() {
        return txtNoTelp.getText();
    }

    public void setTableData(Object[][] data) {
        tableModel.setRowCount(0); // Hapus data lama
        for (Object[] row : data) {
            tableModel.addRow(row); // Tambahkan data baru
        }
    }

    public int getSelectedUserIndex() {
        return userTable.getSelectedRow();
    }

    public JTable getUserTable() {
        return userTable;
    }

    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addUpdateUserListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteUserListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addExportPdfListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }

    public void addConcurrentDataProcessingListener(ActionListener listener) {
        btnProcessData.addActionListener(listener);
    }

    public void addStartButtonListener(UserController.startButtonListener startButtonListener) {
        startButton.addActionListener(startButtonListener);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
