package DAO;

import DTO.SubjectDTO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectDAO {
        private ArrayList<SubjectDTO> subjects;

        public SubjectDAO() {
                subjects = new ArrayList<>();
        }

        public ArrayList<SubjectDTO> getSubjectsByAcademic(String academicID) {
                ArrayList<SubjectDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getSubjectToAcac(?)}");
                        stmt.setString(1, academicID);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                SubjectDTO subject = new SubjectDTO();
                                subject.setSubjectID(rs.getString("sub_id"));
                                subject.setSubjectName(rs.getString("sub_name"));
                                list.add(subject);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }


}
