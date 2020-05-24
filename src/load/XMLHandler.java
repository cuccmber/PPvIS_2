package load;

import item.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {
    private ArrayList<Student> students = null;
    private Student student = null;

    private boolean bName = false;
    private boolean bGroup = false;
    private boolean bSicknessAbsenceCount = false;
    private boolean bOtherReasonAbsenceCount = false;
    private boolean bNoReasonAbsenceCount = false;

    public ArrayList<Student> getStudentList() {
        return students;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("student")) {
            student = new Student();
            if(students == null) {
                students = new ArrayList<Student>();
            }
        }

        if (qName.equalsIgnoreCase("Name")) {
            bName = true;
        }

        if (qName.equalsIgnoreCase("Group")) {
            bGroup = true;
        }

        if (qName.equalsIgnoreCase("SicknessAbsenceCount")) {
            bSicknessAbsenceCount = true;
        }

        if (qName.equalsIgnoreCase("OtherReasonAbsenceCount")) {
            bOtherReasonAbsenceCount = true;
        }

        if (qName.equalsIgnoreCase("NoReasonAbsenceCount")) {
            bNoReasonAbsenceCount = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("Student")) {
            students.add(student);
        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bName) {
            student.setName(new String(ch, start, length));
            bName = false;
        }

        if (bGroup) {
            student.setGroup(Integer.parseInt(new String(ch, start, length)));
            bGroup = false;
        }

        if (bSicknessAbsenceCount) {
            student.setSicknessAbsenceCount(Integer.parseInt(new String(ch, start, length)));
            bSicknessAbsenceCount = false;
        }

        if (bOtherReasonAbsenceCount) {
            student.setOtherReasonAbsenceCount(Integer.parseInt(new String(ch, start, length)));
            bOtherReasonAbsenceCount = false;
        }

        if (bNoReasonAbsenceCount) {
            student.setNoReasonAbsenceCount(Integer.parseInt(new String(ch, start, length)));
            bNoReasonAbsenceCount = false;
        }
    }

}
