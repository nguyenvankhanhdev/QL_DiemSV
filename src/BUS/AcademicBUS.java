package BUS;

import DAO.AcademicDAO;
import DTO.AcademicDTO;

import java.util.ArrayList;

public class AcademicBUS {
        private AcademicDAO academicDAO;
        public AcademicBUS() {
                academicDAO = new AcademicDAO();
        }
        public ArrayList<AcademicDTO> getAllAcademic() {
                ArrayList<AcademicDTO> academicList = new ArrayList<>();
                try {
                        academicList = academicDAO.menuAcademic();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return academicList;
        }
        public ArrayList<AcademicDTO> getAcademicByDepartment(String departmentID) {
                ArrayList<AcademicDTO> academicList = new ArrayList<>();
                try {
                        academicList = academicDAO.getAcademicByDepartment(departmentID);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return academicList;
        }

}
