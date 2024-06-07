package BUS;

import DAO.TeacherDAO;
import DTO.TeacherDTO;

import java.util.ArrayList;

public class TeacherBUS {
        private TeacherDAO teacherDAO;
        public TeacherBUS() {
                teacherDAO = new TeacherDAO();
        }
        public ArrayList<TeacherDTO> getTeachersByAcademic(String academicID) {

                ArrayList<TeacherDTO> teacherList = new ArrayList<>();
                try{
                        teacherList = teacherDAO.getTeachersByAcademic(academicID);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return teacherList;
        }



}
