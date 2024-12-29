package MembershipORM.src.view.member;

import MembershipORM.src.model.Member;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Nama", "Jenis Member"};
    private List<Member> memberList;

    public MemberTableModel(List<Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return memberList.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member member = memberList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return member.getId();
            case 1:
                return member.getNama();
            case 2:
                return member.getJenisMember().getNama(); // Assuming JenisMember has a getNama() method
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addMember(Member member) {
        memberList.add(member);
        fireTableRowsInserted(memberList.size() - 1, memberList.size() - 1);
    }

    public void updateMember(int rowIndex, Member member) {
        memberList.set(rowIndex, member);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void removeMember(int rowIndex) {
        memberList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public int getSelectedRow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSelectedRow'");
    }
}
