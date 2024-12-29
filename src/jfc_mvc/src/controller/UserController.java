package jfc_mvc.src.controller;

import jfc_mvc.src.model.*;
import jfc_mvc.src.view.UserView;
import jfc_mvc.src.view.UserPdf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private User User; // Tambahkan userModel
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.User = new User(); // Inisialisasi userModel
        this.pdf = pdf;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addUpdateUserListener(new UpdateUserListener());
        this.view.addDeleteUserListener(new DeleteUserListener());
        this.view.addExportPdfListener(new ExportPdfListener());
        this.view.addStartButtonListener(new startButtonListener());
        this.addConcurrentDataProcessingListener(); // Tambahkan ini
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            String nrp = view.getNrpInput();
            String noTelp = view.getNoTelpInput();

            if (!name.isEmpty() && !email.isEmpty() && !nrp.isEmpty() && !noTelp.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setNrp(nrp);
                user.setNoTelp(noTelp);
                mapper.insertUser(user);
                JOptionPane.showMessageDialog(view, "User added successfully!");
                refreshTableData(); // Memanggil method untuk menyegarkan tabel
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTableData();
        }
    }

    class UpdateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = view.getSelectedUserIndex();
            if (selectedIndex != -1) {
                String name = view.getNameInput();
                String email = view.getEmailInput();
                String nrp = view.getNrpInput();
                String noTelp = view.getNoTelpInput();

                if (!name.isEmpty() && !email.isEmpty() && !nrp.isEmpty() && !noTelp.isEmpty()) {
                    User user = new User();
                    user.setId((int) view.getUserTable().getValueAt(selectedIndex, 0));
                    user.setName(name);
                    user.setEmail(email);
                    user.setNrp(nrp);
                    user.setNoTelp(noTelp);
                    mapper.updateUser(user);
                    JOptionPane.showMessageDialog(view, "User updated successfully!");
                    refreshTableData(); // Memanggil method untuk menyegarkan tabel
                } else {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a user to update.");
            }
        }
    }

    class DeleteUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = view.getSelectedUserIndex();
            if (selectedIndex != -1) {
                int response = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this user?");
                if (response == JOptionPane.YES_OPTION) {
                    int id = (int) view.getUserTable().getValueAt(selectedIndex, 0);
                    mapper.deleteUser(id);
                    JOptionPane.showMessageDialog(view, "User deleted successfully!");
                    refreshTableData(); // Memanggil method untuk menyegarkan tabel
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a user to delete.");
            }
        }
    }

    class ExportPdfListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pdf != null) {
                List<User> users = mapper.getAllUsers();
                pdf.exportPdf(users);
                JOptionPane.showMessageDialog(view, "PDF exported successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "PDF export failed. PDF object is null.");
            }
        }
    }

    public class startButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getStartButton().setEnabled(false);
            view.getStatusLabel().setText("Tugas berat sedang berjalan");

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        publish(i);
                    }
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    int latestProgress = chunks.get(chunks.size() - 1);
                    view.getProgressBar().setValue(latestProgress);
                }

                @Override
                protected void done() {
                    view.getStartButton().setEnabled(true);
                    view.getStatusLabel().setText("Tugas berat selesai");
                }
            };
            worker.execute();
        }
    }

    private void refreshTableData() {
        List<User> users = mapper.getAllUsers();
        Object[][] userData = users.stream()
                .map(u -> new Object[]{u.getId(), u.getName(), u.getEmail(), u.getNrp(), u.getNoTelp()})
                .toArray(Object[][]::new);
        view.setTableData(userData);
    }

    public void addConcurrentDataProcessingListener() {
        view.addConcurrentDataProcessingListener(new ConcurrentDataProcessingListener());
    }

    class ConcurrentDataProcessingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    // Generate dummy users
                    List<User> dummyUsers = User.generateDummyUsers(100);
                    // Insert each dummy user into the database
                    for (User user : dummyUsers) {
                        mapper.insertUser(user);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    refreshTableData();
                    JOptionPane.showMessageDialog(view, "Processed 100 dummy users successfully.");
                }
            };
            worker.execute();
        }
    }
}
