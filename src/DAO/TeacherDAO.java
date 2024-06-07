package DAO;

import DTO.TeacherDTO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TeacherDAO {
        private ArrayList<TeacherDTO> teachers;
        public TeacherDAO() {
                teachers = new ArrayList<>();
        }
        public ArrayList<TeacherDTO> getTeachersByAcademic(String academicID) {
                ArrayList<TeacherDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getTeacherToDeparment(?)}");
                        stmt.setString(1, academicID);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                TeacherDTO teacher = new TeacherDTO();
                                teacher.setTeacherID(rs.getString("tea_id"));
                                teacher.setTeacherName(rs.getString("tea_name"));

                                list.add(teacher);
                        }
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }


}
