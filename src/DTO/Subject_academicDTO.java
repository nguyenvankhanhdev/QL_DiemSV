package DTO;

public class Subject_academicDTO {
        private String subjectID;
        private String academicID;

        public Subject_academicDTO(String subjectID, String academicID) {
                this.subjectID = subjectID;
                this.academicID = academicID;
        }

        public String getSubjectID() {
                return subjectID;
        }

        public void setSubjectID(String subjectID) {
                this.subjectID = subjectID;
        }

        public String getAcademicID() {
                return academicID;
        }

        public void setAcademicID(String academicID) {
                this.academicID = academicID;
        }

        public Subject_academicDTO() {
        }
}
