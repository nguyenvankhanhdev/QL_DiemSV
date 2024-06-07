package DTO;

public class TeacherDTO {
        private String teacherID;
        private String teacherName;
        private String teacherSex;

        public String getTeacherID() {
                return teacherID;
        }

        public void setTeacherID(String teacherID) {
                this.teacherID = teacherID;
        }

        public String getTeacherName() {
                return teacherName;
        }

        public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
        }

        public String getTeacherSex() {
                return teacherSex;
        }

        public void setTeacherSex(String teacherSex) {
                this.teacherSex = teacherSex;
        }

        public String getDepartment() {
                return Department;
        }

        public void setDepartment(String department) {
                Department = department;
        }

        private String Department;

        public TeacherDTO() {
        }
        public TeacherDTO(String teacherID, String teacherName, String teacherSex, String department) {
                this.teacherID = teacherID;
                this.teacherName = teacherName;
                this.teacherSex = teacherSex;
                Department = department;
        }



}
