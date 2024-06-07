package BUS;

import DAO.DepartmentsDAO;
import DTO.DepartmentsDTO;

import java.util.ArrayList;

public class DepartmentsBUS {

        private DepartmentsDAO departmentsDAO;

        public DepartmentsBUS() {
                departmentsDAO = new DepartmentsDAO();
        }
        public ArrayList<DepartmentsDTO> getAllDepartments() {
                ArrayList<DepartmentsDTO> departmentsList = new ArrayList<>();
                try {
                        departmentsList = departmentsDAO.menuDepartment();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return departmentsList;
        }

}
