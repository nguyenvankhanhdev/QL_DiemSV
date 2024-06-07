package DAO;

import DTO.Assigning_examDTO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Assigning_examDAO {
        private ArrayList<Assigning_examDTO> assigning_exam;
        public Assigning_examDAO() {
                assigning_exam = new ArrayList<>();
        }
        public ArrayList<Assigning_examDTO> menuAssigning_exam() {
                ArrayList<Assigning_examDTO> list = new ArrayList<>();
                try {
                        MysqlAccess helper = new MysqlAccess();
                        helper.open();
                        CallableStatement stmt = helper.getConnection().prepareCall("{call sp_getAllAssigning_exam}");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                Assigning_examDTO stu = new Assigning_examDTO();
                                stu.setSub_id(rs.getString("sub_id"));
                                stu.setTea_id(rs.getString("tea_id"));
                                list.add(stu);
                        }
                        helper.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                return list;
        }

}
