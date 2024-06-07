package DAO;

import DTO.AcademicDTO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class AcademicDAO {

        private Collection con;

        private ArrayList<AcademicDTO> academic;

        public AcademicDAO() {
                academic = new ArrayList<>();
        }

        public ArrayList<AcademicDTO> menuAcademic() {
                ArrayList<AcademicDTO> academic = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllAcademic}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                AcademicDTO aca = new AcademicDTO();
                                aca.setAca_id(rs.getString("aca_id"));
                                aca.setAca_name(rs.getString("aca_name"));
                                academic.add(aca);
                        }
                        helper.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return academic;
        }
        public ArrayList<AcademicDTO> getAcademicByDepartment(String departmentID) {
                ArrayList<AcademicDTO> academic = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAcademicByDepartment(?)}");
                        stmt.setString(1, departmentID);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                AcademicDTO aca = new AcademicDTO();
                                aca.setAca_id(rs.getString("aca_id"));
                                aca.setAca_name(rs.getString("aca_name"));
                                academic.add(aca);
                        }
                        helper.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return academic;
        }


}
