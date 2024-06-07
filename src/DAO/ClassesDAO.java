package DAO;

import java.sql.*;
import java.util.ArrayList;
import DTO.ClassesDTO;

public class ClassesDAO {
        private ArrayList<ClassesDTO> classes;
        public ClassesDAO() {
                classes = new ArrayList<>();
        }
        public ArrayList<ClassesDTO> menuClass() {
                ArrayList<ClassesDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllClasses}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                ClassesDTO cls = new ClassesDTO();
                                cls.setClass_id(rs.getString("class_id"));
                                cls.setClass_name(rs.getString("class_name"));
                                list.add(cls);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }

        public boolean isClassIdExist(String classId) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getClassById(?)}");
                        stmt.setString(1, classId);
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
        public boolean addClass(ClassesDTO cls) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_insertClasses(?, ?)}");
                        stmt.setString(1, cls.getClass_id());
                        stmt.setString(2, cls.getClass_name());
                        stmt.executeUpdate();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }

        public boolean updateClass(ClassesDTO cls) {
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_updateClasses(?, ?)}");
                        stmt.setString(1, cls.getClass_id());
                        stmt.setString(2, cls.getClass_name());
                        stmt.executeUpdate();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }
        public  boolean deleteClass(String classId){
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_deleteClasses(?)}");
                        stmt.setString(1, classId);
                        stmt.executeUpdate();
                        helper.close();
                        return true;
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return false;
                }
        }
}
