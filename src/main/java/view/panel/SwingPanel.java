package main.java.view.panel;

import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * super class of other handlers.
 */
public abstract class SwingPanel extends JPanel {

    protected ActionHandler[] actionHandlers;

    public SwingPanel(ActionHandler[] actionHandlers) {
        this.actionHandlers = actionHandlers.clone();
    }

    protected void setDisabled(JTextArea textArea) {
        for (MouseListener mouseListener: textArea.getMouseListeners()) {
            textArea.removeMouseListener(mouseListener);
        }
        for (MouseMotionListener mouseMotionListener: textArea.getMouseMotionListeners()) {
            textArea.removeMouseMotionListener(mouseMotionListener);
        }
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        textArea.setBackground(new Color(0, 0, 0, 0));
    }

    protected void setHeader(JTextArea textArea) {
        setDisabled(textArea);
        Font font = new Font("Segoe Script", Font.BOLD, 20);
        textArea.setFont(font);
    }

    protected void setErrorText(JTextArea textArea) {
        setDisabled(textArea);
        Font font = new Font("TimesRoman", Font.BOLD | Font.ITALIC, 12);
        textArea.setFont(font);
        textArea.setDisabledTextColor(Color.RED);
        textArea.setVisible(false);
    }

    protected void setErrorTextKeyListener(JTextField textField, JTextArea errorTextArea) {
        textField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (errorTextArea.isVisible()) {
                            errorTextArea.setVisible(false);
                        }
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
    }
}
