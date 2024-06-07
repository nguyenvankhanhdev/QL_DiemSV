package DTO;

public class SubjectDTO {
        private String subjectID;
        private String subjectName;
        private int subjectSemester;

        public String getSubjectID() {
                return subjectID;
        }

        public void setSubjectID(String subjectID) {
                this.subjectID = subjectID;
        }

        public String getSubjectName() {
                return subjectName;
        }

        public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
        }

        public int getSubjectSemester() {
                return subjectSemester;
        }

        public void setSubjectSemester(int subjectSemester) {
                this.subjectSemester = subjectSemester;
        }

        public SubjectDTO(String subjectID, String subjectName, int subjectSemester) {
                this.subjectID = subjectID;
                this.subjectName = subjectName;
                this.subjectSemester = subjectSemester;
        }

        public SubjectDTO() {
        }
}
