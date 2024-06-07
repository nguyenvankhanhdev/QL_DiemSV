package GUI;

import BUS.AcademicBUS;
import BUS.Assigning_examBUS;
import BUS.ExamRoomBUS;
import BUS.TeacherBUS;
import DTO.Assigning_examDTO;
import DTO.ComboItem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Assigning_examGUI extends JInternalFrame implements ActionListener {

        private Label lblTeacherID;
        private Label lblSubjectID;
        private Label lblDepartmentID;
        private Label lblAcademicID;

        private JButton btnAdd;
        private JButton btnUpdate;
        private JButton btnDelete;
        private JTable table;
        private DefaultTableModel tableModel;

        private Assigning_examBUS Assigning_examBUS;
        private JComboBox<ComboItem> cbTeacherID;
        private JComboBox<ComboItem> cbSubjectID;
        private JComboBox<ComboItem> cbDepartmentID;
        private JComboBox<ComboItem> cbAcademicID;

        public Assigning_examGUI() {
                this.Assigning_examBUS = new Assigning_examBUS();
                initComponents();
                populateTable();
        }

        private void initComponents() {
                this.setTitle("Phân công chấm thi");
                this.setSize(1000, 500);
                getContentPane().setLayout(null);

                JPanel inputPanel = new JPanel();
                inputPanel.setBounds(30, 20, 300, 380);
                inputPanel.setBorder(new TitledBorder("Assigning Information"));
                inputPanel.setLayout(null);
                getContentPane().add(inputPanel);

                lblDepartmentID = new Label("Departments: ");
                lblDepartmentID.setBounds(10, 20, 100, 25);
                inputPanel.add(lblDepartmentID);


                cbDepartmentID = new JComboBox<ComboItem>();
                cbDepartmentID.setBounds(120, 20, 160, 25);
                inputPanel.add(cbDepartmentID);

                loadDepartment();

                lblAcademicID = new Label("Academic: ");
                lblAcademicID.setBounds(10, 60, 100, 25);
                inputPanel.add(lblAcademicID);

                cbAcademicID = new JComboBox<ComboItem>();
                cbAcademicID.setBounds(120, 60, 160, 25);
                inputPanel.add(cbAcademicID);

                lblSubjectID = new Label("Subject: ");
                lblSubjectID.setBounds(10, 100, 100, 25);
                inputPanel.add(lblSubjectID);

                cbSubjectID = new JComboBox<ComboItem>();
                cbSubjectID.setBounds(120, 100, 160, 25);
                inputPanel.add(cbSubjectID);

                lblTeacherID = new Label("Teachers: ");
                lblTeacherID.setBounds(10, 140, 100, 25);
                inputPanel.add(lblTeacherID);

                cbTeacherID = new JComboBox<ComboItem>();
                cbTeacherID.setBounds(120, 140, 160, 25);
                inputPanel.add(cbTeacherID);

                btnAdd = new JButton("Add");
                btnAdd.setBounds(30, 410, 80, 30);
                btnAdd.addActionListener(this);
                getContentPane().add(btnAdd);

                btnUpdate = new JButton("Update");
                btnUpdate.setBounds(140, 410, 80, 30);
                btnUpdate.addActionListener(this);
                getContentPane().add(btnUpdate);

                btnDelete = new JButton("Delete");
                btnDelete.setBounds(250, 410, 80, 30);
                btnDelete.addActionListener(this);
                getContentPane().add(btnDelete);

                tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Name");

                table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(350, 20, 800, 420);
                getContentPane().add(scrollPane);
                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {

                        }
                });
                cbDepartmentID.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                ComboItem selectedItem = (ComboItem) cbDepartmentID.getSelectedItem();
                                if (selectedItem != null) {
                                        loadMajor(selectedItem.getId());
                                        loadTeacher(selectedItem.getId());

                                }
                        }
                });
                cbAcademicID.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                ComboItem selectedItem = (ComboItem) cbAcademicID.getSelectedItem();
                                if (selectedItem != null) {
                                        loadSubject(selectedItem.getId());
                                }
                        }
                });
        }

        private void loadSubject(String academicID) {
                // Clear the current items
                cbSubjectID.removeAllItems();
                BUS.SubjectBUS subjectBUS = new BUS.SubjectBUS();
                ArrayList<DTO.SubjectDTO> subjectList = subjectBUS.getSubjectsByAcademic(academicID);
                for (DTO.SubjectDTO subject : subjectList) {
                        cbSubjectID.addItem(new ComboItem(subject.getSubjectID(), subject.getSubjectName()));
                }
        }

        private void loadMajor(String departmentID) {
                AcademicBUS academicBUS = new AcademicBUS();
                ArrayList<DTO.AcademicDTO> academicList = academicBUS.getAcademicByDepartment(departmentID);
                cbAcademicID.removeAllItems();
                for (DTO.AcademicDTO academic : academicList) {
                        cbAcademicID.addItem(new ComboItem(academic.getAca_id(), academic.getAca_name()));
                }

        }
        private void loadTeacher(String departmentID) {
                TeacherBUS teacherBUS = new TeacherBUS();
                ArrayList<DTO.TeacherDTO> teacherList = teacherBUS.getTeachersByAcademic(departmentID);
                cbTeacherID.removeAllItems();
                for (DTO.TeacherDTO teacher : teacherList) {
                        cbTeacherID.addItem(new ComboItem(teacher.getTeacherID(), teacher.getTeacherName()));
                }

        }

        private void populateTable() {
                ArrayList<Assigning_examDTO> Assigning_examList = Assigning_examBUS.getAllAssigning_exam();
                for (Assigning_examDTO Assigning_exam : Assigning_examList) {
                        tableModel.addRow(new Object[]{Assigning_exam.getSub_id(), Assigning_exam.getTea_id()});
                }
        }

        private void loadDepartment() {
                BUS.DepartmentsBUS departmentBUS = new BUS.DepartmentsBUS();
                ArrayList<DTO.DepartmentsDTO> departmentList = departmentBUS.getAllDepartments();
                for (DTO.DepartmentsDTO department : departmentList) {
                        cbDepartmentID.addItem(new ComboItem(department.getDepartment_id(), department.getDepartment_name()));
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
}
