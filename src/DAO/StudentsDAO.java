package DAO;

import DTO.StudentsDTO;
import DTO.StudyRecordDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentsDAO {

        private ArrayList<StudentsDTO> students;

        public StudentsDAO() {
                students = new ArrayList<>();
        }

        public ArrayList<StudentsDTO> menuStudent() {
                ArrayList<StudentsDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllStudents}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                StudentsDTO stu = new StudentsDTO();
                                stu.setStu_id(rs.getString("stu_id"));
                                stu.setStu_name(rs.getString("stu_name"));
                                stu.setStu_day_entry(rs.getDate("stu_day_entry"));
                                stu.setStu_semester(rs.getInt("stu_semester"));
                                stu.setStu_sex(rs.getString("stu_sex"));
                                stu.setDob(rs.getDate("stu_dob"));
                                stu.setAddress(rs.getString("stu_address"));
                                stu.setAca_id(rs.getString("aca_name"));
                                stu.setClass_id(rs.getString("class_name"));
                                list.add(stu);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }

        public boolean isStudentIdExist(String stuId) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getStudentById(?)}");
                        stmt.setString(1, stuId);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                                return true;
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return false;
        }

        public boolean addStudent(StudentsDTO stu) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_insertStudent(?,?,?,?,?,?,?,?,?)}");
                        stmt.setString(1, stu.getStu_id());
                        stmt.setString(2, stu.getStu_name());
                        stmt.setDate(3, stu.getStu_day_entry());
                        stmt.setInt(4, stu.getStu_semester());
                        stmt.setString(5, stu.getStu_sex());
                        stmt.setDate(6, stu.getDob());
                        stmt.setString(7, stu.getAddress());
                        stmt.setString(8, stu.getAca_id());
                        stmt.setString(9, stu.getClass_id());
                        stmt.executeUpdate();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }

        public boolean updateStudent(StudentsDTO stu) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_updateStudent(?,?,?,?,?,?,?,?,?)}");
                        stmt.setString(1, stu.getStu_id());
                        stmt.setString(2, stu.getStu_name());
                        stmt.setDate(3, stu.getStu_day_entry());
                        stmt.setInt(4, stu.getStu_semester());
                        stmt.setString(5, stu.getStu_sex());
                        stmt.setDate(6, stu.getDob());
                        stmt.setString(7, stu.getAddress());
                        stmt.setString(8, stu.getAca_id());
                        stmt.setString(9, stu.getClass_id());
                        stmt.executeUpdate();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }

        public boolean deleteStudent(String stuId) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_deleteStudent(?)}");
                        stmt.setString(1, stuId);
                        stmt.executeUpdate();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }

        public StudentsDTO searchStudent(String stuId) {
                StudentsDTO stu = null;
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call findIDStudent(?)}");
                        stmt.setString(1, stuId);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                                stu = new StudentsDTO();
                                stu.setStu_id(rs.getString("stu_id"));
                                stu.setStu_name(rs.getString("stu_name"));
                                stu.setStu_day_entry(rs.getDate("stu_day_entry"));
                                stu.setStu_semester(rs.getInt("stu_semester"));
                                stu.setStu_sex(rs.getString("stu_sex"));
                                stu.setDob(rs.getDate("stu_dob"));
                                stu.setAddress(rs.getString("stu_address"));
                                stu.setAca_id(rs.getString("aca_name"));
                                stu.setClass_id(rs.getString("class_name"));
                        }
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return stu;
        }

        public ArrayList<StudyRecordDTO> getStudyRecords(String studentId) {
                ArrayList<StudyRecordDTO> studyRecords = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_studyRecords(?)}");
                        stmt.setString(1, studentId);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                StudyRecordDTO record = new StudyRecordDTO();
                                record.setStu_id(rs.getString("stu_id"));
                                record.setStu_name(rs.getString("stu_name"));
                                record.setSub_name(rs.getString("sub_name"));
                                record.setRes_time(rs.getInt("res_time"));
                                record.setRes_score(rs.getFloat("res_score"));
                                studyRecords.add(record);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return studyRecords;
        }

}
