package menu;

import controller.Controller;
import load.SAXLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import view.Window;

public class LoadStudentList implements Listener {


    private Shell parent;
    private Controller controller;
    private Window window;
    private SAXLoader loader;

    public LoadStudentList(Shell parent, Window window, Controller controller) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        FileDialog fDialog = new FileDialog(parent, SWT.OPEN);
        fDialog.setFilterExtensions(new String[] {"*.xml"});
        String inputFile = fDialog.open();
        controller.load(inputFile);
        window.updateTable();
    }

}
