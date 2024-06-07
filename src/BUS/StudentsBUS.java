package BUS;

import DAO.StudentsDAO;
import DTO.StudentsDTO;

import java.util.ArrayList;

public class StudentsBUS {

        private StudentsDAO studentsDAO;
        public StudentsBUS() {
                studentsDAO = new StudentsDAO();
        }
        public ArrayList<StudentsDTO> getAllStudent() {

                ArrayList<StudentsDTO> studentList = new ArrayList<>();
                try{
                        studentList = studentsDAO.menuStudent();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return studentList;
        }
        public boolean addStudent(StudentsDTO stu) {
                try {
                        if (studentsDAO.isStudentIdExist(stu.getStu_id())) {
                                return false;
                        }
                        else {
                                return studentsDAO.addStudent(stu);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public  boolean updateStudent(StudentsDTO stu) {
                try {
                        return studentsDAO.updateStudent(stu);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public boolean deleteStudent(String stuId) {
                try {
                        return studentsDAO.deleteStudent(stuId);
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }



}
