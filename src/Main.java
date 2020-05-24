import controller.Controller;
import item.Student;
import view.Window;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ArrayList<Student>());
        Window window = new Window(controller);
        window.show();
    }

}
