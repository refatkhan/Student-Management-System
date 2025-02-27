package airlinemanagementsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login() {
        getContentPane().setBackground(Color.yellow);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(20, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        add(tfpassword);

        reset = new JButton("Forget Password");
        reset.setBounds(40, 120, 120, 20);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("Submit");
        submit.setBounds(190, 120, 120, 20);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Close");
        close.setBounds(140, 160, 120, 20);
        close.addActionListener(this);
        add(close);

        setSize(600, 350);
        setLocation(600, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            @SuppressWarnings("deprecation")
            String password = tfpassword.getText();

            try {
                Connect c = new Connect();
                String query = "select * from login where username = '" + username + "'and password= '" + password
                        + "'";
                ResultSet rs = c.statement.executeQuery(query);

                if (rs.next()) {
                    new Home();
                }
                // for invalid password
                else {
                    JOptionPane.showMessageDialog(null, "invalid username & password");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // if we click reset it will be empty
        else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");

        }
        // if we cllick close the window will be closed
        else if (ae.getSource() == close) {
            setVisible(false);
        }
    }

}
