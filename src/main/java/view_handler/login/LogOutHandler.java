package main.java.view_handler.login;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.LoginText;
import main.java.view.panel.LoginPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LogOutHandler extends ActionHandler {

    public LogOutHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (AppController controller: controllers) {
            controller.save();
        }

        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        CommonText commonText = new CommonText();
        Object[] options = {commonText.getYes(), commonText.getNo()};
        int option = JOptionPane.showOptionDialog(appFrame,
                new LoginText().getConfirmLogOutQuestion(),
                commonText.getLogOut(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (option == JOptionPane.YES_OPTION){
            LoginPanel loginPanel = new LoginPanel(new ActionHandler[]{
                    new SignInHandler(controllers, null),
                    new SignUpHandler(controllers, null, false)},
                    false);
            appFrame.getJMenuBar().removeAll();
            appFrame.getContentPane().removeAll();
            appFrame.setContentPane(loginPanel);
            appFrame.setVisible(true);
        }
    }
}
