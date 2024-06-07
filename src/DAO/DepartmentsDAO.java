package DAO;

import DTO.DepartmentsDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class DepartmentsDAO {
        private Connection con;
        private ArrayList<DepartmentsDTO> departments;

        public DepartmentsDAO() {
                departments = new ArrayList<>();
        }
        public ArrayList<DepartmentsDTO> menuDepartment() {
                ArrayList<DepartmentsDTO> list = new ArrayList<>();
                try{
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllDepartments}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                DepartmentsDTO dep = new DepartmentsDTO();
                                dep.setDepartment_id(rs.getString("depart_id"));
                                dep.setDepartment_name(rs.getString("depart_name"));
                                list.add(dep);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }
}
