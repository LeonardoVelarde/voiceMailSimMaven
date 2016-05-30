package View;
import Controller.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfacePhoneGUI extends JDialog implements UserInterface {
    private JPanel contentPane;
    private JPanel outputShowPanel;
    private JPanel keypadPanel;
    private JPanel formatPanel;
    private JPanel buttonsPanel;
    private JPanel showInputPanel;
    private JTextPane outputTextPane;
    private JButton btn7;
    private JButton btn9;
    private JButton btn8;
    private JButton btn6;
    private JButton btn5;
    private JButton btn4;
    private JButton btn3;
    private JButton btn2;
    private JButton btn1;
    private JButton btnHash;
    private JButton btnHangUp;
    private JButton btnQuit;
    private JButton btnSend;
    private JTextArea inputTextArea;

    private Connection connection;

    public void speak(String message){
        this.outputTextPane.setText(message.replace("\n", "<br>"));
    }

    public InterfacePhoneGUI(Connection connection) {
        this.connection = connection;
        setContentPane(contentPane);
        setModal(true);
        this.outputTextPane.setContentType("text/html");
        this.outputTextPane.setEditable(false);

        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("1");
            }
        });

        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("2");
            }
        });

        btn3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("3");
            }
        });

        btn4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("4");
            }
        });

        btn5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("5");
            }
        });

        btn6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("6");
            }
        });

        btn7.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("7");
            }
        });

        btn8.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("8");
            }
        });

        btn9.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("9");
            }
        });

        btnHash.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("#");
            }
        });

        btnHangUp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("H");
            }
        });

        btnQuit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress("Q");
            }
        });

        btnSend.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onKeyPress(inputTextArea.getText());
                inputTextArea.setText("");
            }
        });

        setButtonStyles();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }

        });

// call onCancel() on ESCAPE key press
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void setButtonStyles(){
        outputTextPane.setContentType("text/html");
        btn1.setForeground(new Color(255, 255, 255));
        btn2.setForeground(new Color(255, 255, 255));
        btn3.setForeground(new Color(255, 255, 255));
        btn4.setForeground(new Color(255, 255, 255));
        btn5.setForeground(new Color(255, 255, 255));
        btn6.setForeground(new Color(255, 255, 255));
        btn7.setForeground(new Color(255, 255, 255));
        btn8.setForeground(new Color(255, 255, 255));
        btn9.setForeground(new Color(255, 255, 255));
        btnHangUp.setForeground(new Color(255, 255, 255));
        btnHash.setForeground(new Color(255, 255, 255));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnSend.setForeground(new Color(255, 255, 255));

        btn1.setBackground(new Color(0, 0, 0));
        btn2.setBackground(new Color(0, 0, 0));
        btn3.setBackground(new Color(0, 0, 0));
        btn4.setBackground(new Color(0, 0, 0));
        btn5.setBackground(new Color(0, 0, 0));
        btn6.setBackground(new Color(0, 0, 0));
        btn7.setBackground(new Color(0, 0, 0));
        btn8.setBackground(new Color(0, 0, 0));
        btn9.setBackground(new Color(0, 0, 0));
        btnHangUp.setBackground(new Color(0, 0, 0));
        btnHash.setBackground(new Color(0, 0, 0));
        btnQuit.setBackground(new Color(0, 0, 0));
        btnSend.setBackground(new Color(0, 0, 0));

        btn1.setBorderPainted(false);
        btn2.setBorderPainted(false);
        btn3.setBorderPainted(false);
        btn4.setBorderPainted(false);
        btn5.setBorderPainted(false);
        btn6.setBorderPainted(false);
        btn7.setBorderPainted(false);
        btn8.setBorderPainted(false);
        btn9.setBorderPainted(false);
        btnHangUp.setBorderPainted(false);
        btnHash.setBorderPainted(false);
        btnQuit.setBorderPainted(false);
        btnSend.setBorderPainted(false);

//        btn1.setPreferredSize(new Dimension(130, 400));

        this.setPreferredSize(new Dimension(500, 500));

    }

    private void onKeyPress(String key) {
        this.connection.receiveInput(key);
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
