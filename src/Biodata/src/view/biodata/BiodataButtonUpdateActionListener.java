package Biodata.src.view.biodata;



import java.awt.event.*;
import Biodata.src.model.Biodata;
import Biodata.src.dao.BiodataDao;

public class BiodataButtonUpdateActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonUpdateActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Biodata selectedBiodata = biodataFrame.getSelectedBiodata();
        if (selectedBiodata != null) {
            String nama = biodataFrame.getNama();
            String nrp = biodataFrame.getNrp();
            String alamat = biodataFrame.getAlamat();

            selectedBiodata.setNama(nama);
            selectedBiodata.setNrp(nrp);
            selectedBiodata.setAlamat(alamat);

            biodataDao.update(selectedBiodata);
            biodataFrame.updateBiodata(selectedBiodata);
        }
    }
}
