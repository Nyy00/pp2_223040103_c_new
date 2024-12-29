package jfc_konkurensi.src;

import javax.swing.*;
import java.awt.*;
import java.util.List;



public class MainFrame {
    public static void main(String[] args) {
     SwingUtilities.invokeLater(()->{
        JFrame frame = new JFrame("Contoh konkurensi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());


        JLabel statusLabel = new JLabel("Tekan tombol untuk memulai tugas berat", JLabel.CENTER);
        JButton startButton = new JButton("Mulai");

        JProgressBar progressBar = new JProgressBar(0, 60);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(progressBar, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e->{
           startButton.setEnabled(false);
           statusLabel.setText("Tugas berat sedang berjalan");

           SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>(){
               @Override
               protected Void doInBackground() throws Exception {
                   for(int i = 0; i <= 100; i++){
                       Thread.sleep(50);
                       publish(i);
                   }
                   return null;
               }

               @Override
               protected void process(List<Integer> chunks) {
                  int latesProgress = chunks.get(chunks.size() - 1);
                    progressBar.setValue(latesProgress);
               }

               @Override
               protected void done() {
                   startButton.setEnabled(true);
                   statusLabel.setText("Tugas berat selesai");
               }
           };
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

     });
    }

}
