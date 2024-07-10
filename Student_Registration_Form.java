import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student_Registration_Form {
    private DefaultTableModel model;
    public Student_Registration_Form() {
        JFrame F1 = new JFrame("Registration Form");
        JPanel P1 = new JPanel();
        JPanel P2 = new JPanel();
        JLabel L1 = new JLabel("STUDENT REGISTRATION FORM");
        L1.setFont(new Font("ARIAL BLACK", Font.BOLD, 25));
        JLabel L2 = new JLabel("ID");
        L2.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JLabel L3 = new JLabel("NAME");
        L3.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JLabel L4 = new JLabel("GENDER");
        L4.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JLabel L5 = new JLabel("ADDRESS");
        L5.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JLabel L6 = new JLabel("CONTACT");
        L6.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JRadioButton R1 = new JRadioButton("MALE");
        JTextField T1 = new JTextField();
        T1.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JTextField T2 = new JTextField();
        T2.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JTextField T4 = new JTextField();
        T4.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JTextField T5 = new JTextField();
        T5.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        JButton B1 = new JButton("CLEAR");
        JButton B2 = new JButton("REGISTER");
        JButton B3 = new JButton("UPDATE");
        JButton B4 = new JButton("DELETE");
        R1.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        R1.setBackground(Color.BLUE);
        JRadioButton R2 = new JRadioButton("FEMALE");
        R2.setFont(new Font("ARIAL BLACK", Font.BOLD, 18));
        R2.setBackground(Color.BLUE);
        ButtonGroup G1 = new ButtonGroup();
        G1.add(R1);
        G1.add(R2);
        JTable Table1 = new JTable();
        model = new DefaultTableModel();
        Table1.setModel(model);
        Table1.setBounds(10, 10, 400, 500);
        P1.setBounds(0, 50, 500, 700);
        P2.setBounds(500, 50, 500, 700);
        L1.setBounds(300, 0, 1000, 50);
        L2.setBounds(50, 50, 100, 50);
        L3.setBounds(50, 100, 100, 50);
        L4.setBounds(50, 150, 100, 50);
        R1.setBounds(150, 160, 100, 30);
        R2.setBounds(250, 160, 130, 30);
        L5.setBounds(50, 200, 150, 50);
        L6.setBounds(50, 250, 150, 50);
        T1.setBounds(200, 50, 100, 40);
        T2.setBounds(200, 100, 100, 40);
        T4.setBounds(200, 200, 100, 40);
        T5.setBounds(200, 250, 100, 40);
        B1.setBounds(50, 350, 100, 50);
        B2.setBounds(250, 350, 100, 50);
        B3.setBounds(50, 450, 100, 50);
        B4.setBounds(250, 450, 100, 50);
        JScrollPane s1 = new JScrollPane(Table1);
        P2.add(s1);
        P1.setBackground(Color.BLUE);
        P1.setLayout(null);
        P1.add(L2);
        P1.add(L3);
        P1.add(L4);
        P1.add(L1);
        P1.add(T1);
        P1.add(T2);
        P1.add(T4);
        P1.add(T5);
        P1.add(R1);
        P1.add(R2);
        P1.add(L5);
        P1.add(L6);
        P1.add(B1);
        P1.add(B2);
        P1.add(B3);
        P1.add(B4);
        F1.add(P1);
        F1.add(P2);
        F1.add(L1);
        F1.setSize(1000, 700);
        F1.setLayout(null);
        F1.setVisible(true);
        loadData();

        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = T1.getText();
                String name = T2.getText();
                String gender = "";
                if (R1.isSelected()) {
                    gender = "Male";
                } else if (R2.isSelected()) {
                    gender = "Female";
                }
                String address = T4.getText();
                String contact = T5.getText();
                String url = "jdbc:mysql://localhost:3306/STUDENT";
                String userName = "root";
                String passWord = "sakthi";
                String query = "insert into STUDENT2 values(?,?,?,?,?)";
                try {
                    Connection con = DriverManager.getConnection(url, userName, passWord);
                    PreparedStatement pre = con.prepareStatement(query);
                    pre.setString(1, id);
                    pre.setString(2, name);
                    pre.setString(3, gender);
                    pre.setString(4, address);
                    pre.setString(5, contact);
                    int row = pre.executeUpdate();
                    if (row == 1) {
                        JOptionPane.showMessageDialog(F1, "Successfully Registered");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(F1, "Not Successfully Registered");
                    }
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(F1, "Registration Error", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                T1.setText("");
                T2.setText("");
                T4.setText("");
                T5.setText("");
            }
        });

        B4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = T1.getText();
                String url = "jdbc:mysql://localhost:3306/STUDENT";
                String userName = "root";
                String passWord = "sakthi";
                String query = "delete from STUDENT2 where ID=?";
                try {
                    Connection con = DriverManager.getConnection(url, userName, passWord);
                    PreparedStatement pre = con.prepareStatement(query);
                    pre.setString(1, id);
                    int row = pre.executeUpdate();
                    if (row == 1) {
                        JOptionPane.showMessageDialog(F1, "Successfully Deleted");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(F1, "Not Successfully Deleted");
                    }
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(F1, "Deletion Error", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        B3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = T1.getText();
                String name = T2.getText();
                String gender = "";
                if (R1.isSelected()) {
                    gender = "Male";
                } else if (R2.isSelected()) {
                    gender = "Female";
                }
                String address = T4.getText();
                String contact = T5.getText();
                String url = "jdbc:mysql://localhost:3306/STUDENT";
                String userName = "root";
                String passWord = "sakthi";
                String query = "update STUDENT2 set NAME=?,GENDER=?,ADDRESS=?,CONTACT=? where ID=?";
                try {
                    Connection con = DriverManager.getConnection(url, userName, passWord);
                    PreparedStatement pre = con.prepareStatement(query);
                    pre.setString(1, name);
                    pre.setString(2, gender);
                    pre.setString(3, address);
                    pre.setString(4, contact);
                    pre.setString(5, id);
                    int row = pre.executeUpdate();
                    if (row == 1) {
                        JOptionPane.showMessageDialog(F1, "Successfully Updated");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(F1, "Not Successfully Updated");
                    }
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(F1, "Update Error", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    public void loadData() {
        String url = "jdbc:mysql://localhost:3306/STUDENT";
        String userName = "root";
        String passWord = "sakthi";
        String query = "select*from STUDENT2";
        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             PreparedStatement pre = con.prepareStatement(query);
             ResultSet rs = pre.executeQuery()) {
            model.setRowCount(0); // Clear existing rows
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);
            while (rs.next()) {
                String ID = rs.getString(1);
                String NAME = rs.getString(2);
                String GENDER = rs.getString(3);
                String ADDRESS = rs.getString(4);
                String CONTACT = rs.getString(5);
                String[] row = {ID, NAME, GENDER, ADDRESS, CONTACT};
                model.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static void main(String args[]) {
        new Student_Registration_Form();

    }
}
