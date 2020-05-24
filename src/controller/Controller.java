package controller;

import item.Student;
import load.SAXLoader;
import save.DOMSaver;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Student> students;
    private SAXLoader loader;
    private DOMSaver saver;

    public Controller(ArrayList<Student> students) {
        this.students = students;
        loader = new SAXLoader();
        saver = new DOMSaver();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void load(String filePath) {
        this.students = loader.load(filePath);
    }

    public void save(String filePath) {
        saver.save(this.students, filePath);
    }

    public void addStudent(String name, int group, int sicknessAbsenceCount, int otherReasonAbsenceCount, int noReasonAbsenceCount){
        Student student = new Student();
        student.setName(name);
        student.setGroup(group);
        student.setSicknessAbsenceCount(sicknessAbsenceCount);
        student.setOtherReasonAbsenceCount(otherReasonAbsenceCount);
        student.setNoReasonAbsenceCount(noReasonAbsenceCount);

        students.add(student);
    }

    public void deleteStudent(ArrayList<Student> students){
        for(Student student : students){
            this.students.remove(student);
        }
    }

    public ArrayList<Student> findByName(String studentName, ArrayList<Student> searchAmong){
        ArrayList<Student> searchResult = new ArrayList<Student>();
        for(Student student : searchAmong){
            if(student.getName().equals(studentName)){
                searchResult.add(student);
            }
        }
            return searchResult;
    }

    public ArrayList<Student> findByGroup(int group, ArrayList<Student> searchAmong){
        ArrayList<Student> searchResult = new ArrayList<Student>();
        for(Student student : searchAmong){
            if(student.getGroup() == group){
                searchResult.add(student);
            }
        }
        return searchResult;
    }

    public ArrayList<Student> findBySicknessAbsence(int sickness, ArrayList<Student> searchAmong){
        ArrayList<Student> searchResult = new ArrayList<Student>();
        for(Student student : searchAmong){
            if(student.getSicknessAbsenceCount() == sickness){
                searchResult.add(student);
            }
        }
        return searchResult;
    }

    public ArrayList<Student> findByOtherReasonAbsence(int other, ArrayList<Student> searchAmong){
        ArrayList<Student> searchResult = new ArrayList<Student>();
        for(Student student : searchAmong){
            if(student.getOtherReasonAbsenceCount() == other){
                searchResult.add(student);
            }
        }
        return searchResult;
    }

    public ArrayList<Student> findByNoReasonAbsence(int no, ArrayList<Student> searchAmong){
        ArrayList<Student> searchResult = new ArrayList<Student>();
        for(Student student : searchAmong){
            if(student.getNoReasonAbsenceCount() == no){
                searchResult.add(student);
            }
        }
        return searchResult;
    }

    public ArrayList<Student> findByTotalAbsence(int total, ArrayList<Student> searchAmong){
        ArrayList<Student> searchResult = new ArrayList<Student>();
        for(Student student : searchAmong){
            if(student.getTotalAbsenceCount() ==  total){
                searchResult.add(student);
            }
        }
        return searchResult;
    }

}
