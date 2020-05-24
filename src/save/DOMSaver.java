package save;

import item.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOMSaver {
    public void save(List<Student> students, String filePath) {
        try {
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Students");
            doc.appendChild(rootElement);

            for (Student student : students) {
                Element studentElement = doc.createElement("Student");
                rootElement.appendChild(studentElement);

                Element studName = doc.createElement("Name");
                studName.appendChild(doc.createTextNode(student.getName()));
                studentElement.appendChild(studName);

                Element group = doc.createElement("Group");
                group.appendChild(doc.createTextNode(String.valueOf(student.getGroup())));
                studentElement.appendChild(group);

                Element ilnessMissingsCount = doc.createElement("SicknessAbsenceCount");
                ilnessMissingsCount.appendChild(doc.createTextNode(String.valueOf(student.getSicknessAbsenceCount())));
                studentElement.appendChild(ilnessMissingsCount);

                Element withoutAppropriateReasonCount = doc.createElement("OtherReasonAbsenceCount");
                withoutAppropriateReasonCount.appendChild(doc.createTextNode(String.valueOf(student.getOtherReasonAbsenceCount())));
                studentElement.appendChild(withoutAppropriateReasonCount);

                Element anotherReasonCount = doc.createElement("NoReasonAbsenceCount");
                anotherReasonCount.appendChild(doc.createTextNode(String.valueOf(student.getNoReasonAbsenceCount())));
                studentElement.appendChild(anotherReasonCount);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
