package BUS;

import DAO.SubjectDAO;
import DTO.SubjectDTO;

import java.util.ArrayList;

public class SubjectBUS {
        private SubjectDAO subjectDAO;
        public SubjectBUS() {
                subjectDAO = new SubjectDAO();
        }
        public ArrayList<SubjectDTO> getSubjectsByAcademic(String academicID) {

                ArrayList<SubjectDTO> subjectList = new ArrayList<>();
                try{
                        subjectList = subjectDAO.getSubjectsByAcademic(academicID);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return subjectList;
        }
}
