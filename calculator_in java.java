package hw.pkg2;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

class ConversionCalculator extends JFrame implements ActionListener, FocusListener, KeyListener, DocumentListener {

    private JLabel N, ND, NB, NO, NH, line;
    private JRadioButton D, B, O, H;
    private JPanel south, north, center, a;
    private JTextField f;

    public ConversionCalculator() {
        super("Conversion Calculator");
        D = new JRadioButton("Decimal", true);
        B = new JRadioButton("Binary", false);
        O = new JRadioButton("Octal", false);
        H = new JRadioButton("hexaDecimal", false);

        ButtonGroup G = new ButtonGroup();
        G.add(D);
        G.add(B);
        G.add(O);
        G.add(H);

        f = new JTextField("10", 15);

        N = new JLabel("Number:      ");
        ND = new JLabel(getD());
        NB = new JLabel(getB());
        NO = new JLabel(getO());
        NH = new JLabel(getH());
        line = new JLabel("");

        south = new JPanel();
        north = new JPanel();
        center = new JPanel();
        a = new JPanel();
        add(south, BorderLayout.SOUTH);
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(a, BorderLayout.SOUTH);
        north.setBorder(BorderFactory.createTitledBorder("Number To Convert"));
        north.add(N);
        north.add(f);

        center.add(D);
        center.add(B);
        center.add(O);
        center.add(H);

        a.setBorder(BorderFactory.createTitledBorder("Other Numbering System"));
        a.add(ND);
        a.add(NB);
        a.add(NO);
        a.add(NH);

        a.setBorder(BorderFactory.createTitledBorder("Other Numbering System"));
        a.add(ND);
        a.add(NB);
        a.add(NO);
        a.add(NH);
        a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));

        D.addActionListener((ActionListener) this);
        B.addActionListener((ActionListener) this);
        O.addActionListener((ActionListener) this);
        H.addActionListener((ActionListener) this);
        f.addActionListener((ActionListener) this);

        D.addFocusListener(this);
        B.addFocusListener(this);
        O.addFocusListener(this);
        H.addFocusListener(this);
        f.addFocusListener(this);

        f.addKeyListener(this);
        f.getDocument().addDocumentListener(this);

        setBounds(0, 0, 350, 370);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == D) {
            ND.setText("Decimal  :  " + f.getText());
            NB.setText(getB());
            NO.setText(getO());
            NH.setText(getH());
            
            
        }
        if (e.getSource() == B) {
            ND.setText(getD());
            NB.setText("Binary  :  " + f.getText());
            NO.setText(getO());
            NH.setText(getH());
          

        }
        if (e.getSource() == O) {
            ND.setText(getD());
            NB.setText(getB());
            NO.setText("Octal  :  " + f.getText());
            NH.setText(getH());
            

        }
        if (e.getSource() == H) {
            ND.setText(getD());
            NB.setText(getB());
            NO.setText(getO());
            NH.setText("Hexadecimal  :  " + f.getText());
            
           
        }

    }

    public String getD() {
        String the_number1 = f.getText();
        Integer N1=0;
        if(D.isSelected()){
        N1 = Integer.parseInt(the_number1);
       
    }
            if(B.isSelected()){
       N1 = Integer.parseInt(the_number1,2);
       
    }
                if(O.isSelected()){
         N1 = Integer.parseInt(the_number1,8);
       
    }
                    if(H.isSelected()){
         N1 = Integer.parseInt(the_number1,16);
       
    }
        
     return ("Decimal  :  " + N1);
    }

    public String getB() {
        String the_number2 = f.getText();
        Integer N2 = Integer.parseInt(the_number2);
        return ("Binary  :  " + Integer.toBinaryString(N2));
    }

    public String getO() {
        String the_number3 = f.getText();
        Integer N3 = Integer.parseInt(the_number3);
        return ("Octal  :  " + Integer.toOctalString(N3));
    }

    public String getH() {
        String the_number4 = f.getText();
        Integer N4 = Integer.parseInt(the_number4);
        return ("Hexadecimal  :  " + Integer.toHexString(N4));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == D) {
            D.doClick();
           
        }
        if (e.getSource() == B) {
            B.doClick();
        }
        if (e.getSource() == O) {
            O.doClick();
        }
        if (e.getSource() == H) {
            H.doClick();
        }
    }
/*/**/
    @Override
    public void focusLost(FocusEvent fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
       if (f.getText().length() == 0) {
            D.setEnabled(false);
            B.setEnabled(false);
            O.setEnabled(false);
            H.setEnabled(false);
            ND.setText("Decimal  :   ");
            NB.setText("Binary  :   ");
            NO.setText("Octal  :   ");
            NH.setText("Hexadecimal  :   ");
        }
        
        if (!(f.getText().length() == 0)) {
            D.setEnabled(true);
            B.setEnabled(true);
            O.setEnabled(true);
            H.setEnabled(true);
             if (H.isSelected()) {
                String x = f.getText();
                for (int i = 0; i < x.length(); i++) {
                    char ch = x.charAt(i);
                    if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f')) {
                        D.setEnabled(false);
                        B.setEnabled(false);
                        O.setEnabled(false);
                        hexaPrint();
                        return;
                    }
                }
            }

            if (!(B.isSelected())) {
                Integer num = Integer.parseInt(f.getText());
                int check;
                while (num > 0) {
                    check = num % 10;
                    if (check != 0 && check != 1) {
                        B.setEnabled(false);
                        break;
                    }
                    num = num / 10;
                    if (num == 0) {
                        B.setEnabled(true);
                    }
                }
            }

            if (!O.isSelected()) {
                Integer num1 = Integer.parseInt(f.getText());
                while (num1 > 0) {
                    if (num1 % 10 <= 7) {
                        O.setEnabled(true);
                    } else {
                        O.setEnabled(false);
                        break;
                    }
                    num1 /= 10;
                }
            }

            if (D.isSelected()) {
                D.doClick();
            }
            if (B.isSelected()) {
                B.doClick();
            }
            if (O.isSelected()) {
                O.doClick();
            }
            if (H.isSelected()) {
                H.doClick();
            }
    }
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        if (f.getText().length() == 0) {
            D.setEnabled(false);
            B.setEnabled(false);
            O.setEnabled(false);
            H.setEnabled(false);
            ND.setText("Decimal  :   ");
            NB.setText("Binary  :   ");
            NO.setText("Octal  :   ");
            NH.setText("Hexadecimal  :   ");
        }
        
        if (!(f.getText().length() == 0)) {
            D.setEnabled(true);
            B.setEnabled(true);
            O.setEnabled(true);
            H.setEnabled(true);
             if (H.isSelected()) {
                String x = f.getText();
                for (int i = 0; i < x.length(); i++) {
                    char ch = x.charAt(i);
                    if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f')) {
                        D.setEnabled(false);
                        B.setEnabled(false);
                        O.setEnabled(false);
                        hexaPrint();
                        return;
                    }
                }
            }

            if (!(B.isSelected())) {
                Integer num = Integer.parseInt(f.getText());
                int check;
                while (num > 0) {
                    check = num % 10;
                    if (check != 0 && check != 1) {
                        B.setEnabled(false);
                        break;
                    }
                    num = num / 10;
                    if (num == 0) {
                        B.setEnabled(true);
                    }
                }
            }

            if (!O.isSelected()) {
                Integer num1 = Integer.parseInt(f.getText());
                while (num1 > 0) {
                    if (num1 % 10 <= 7) {
                        O.setEnabled(true);
                    } else {
                        O.setEnabled(false);
                        break;
                    }
                    num1 /= 10;
                }
            }
           if (D.isSelected()) {
                D.doClick();
            }
            if (B.isSelected()) {
                B.doClick();
            }
            if (O.isSelected()) {
                O.doClick();
            }
            if (H.isSelected()) {
                H.doClick();
            }
           }}

    @Override
    public void changedUpdate(DocumentEvent de) {
        
        if (f.getText().length() == 0) {
            D.setEnabled(false);
            B.setEnabled(false);
            O.setEnabled(false);
            H.setEnabled(false);
            ND.setText("Decimal  :   ");
            NB.setText("Binary  :   ");
            NO.setText("Octal  :   ");
            NH.setText("Hexadecimal  :   ");
        }
        
        if (!(f.getText().length() == 0)) {
            D.setEnabled(true);
            B.setEnabled(true);
            O.setEnabled(true);
            H.setEnabled(true);
             if (H.isSelected()) {
                String x = f.getText();
                for (int i = 0; i < x.length(); i++) {
                    char ch = x.charAt(i);
                    if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f')) {
                        D.setEnabled(false);
                        B.setEnabled(false);
                        O.setEnabled(false);
                        hexaPrint();
                        return;
                    }
                }
            }

            if (!(B.isSelected())) {
                Integer num = Integer.parseInt(f.getText());
                int check;
                while (num > 0) {
                    check = num % 10;
                    if (check != 0 && check != 1) {
                        B.setEnabled(false);
                        break;
                    }
                    num = num / 10;
                    if (num == 0) {
                        B.setEnabled(true);
                    }
                }
            }

            if (!O.isSelected()) {
                Integer num1 = Integer.parseInt(f.getText());
                while (num1 > 0) {
                    if (num1 % 10 <= 7) {
                        O.setEnabled(true);
                    } else {
                        O.setEnabled(false);
                        break;
                    }
                    num1 /= 10;
                }
            }
  if (D.isSelected()) {
                D.doClick();
            }
            if (B.isSelected()) {
                B.doClick();
            }
            if (O.isSelected()) {
                O.doClick();
            }
            if (H.isSelected()) {
                H.doClick();
            }    }}

    @Override
    public void keyTyped(KeyEvent e) {
        if (D.isSelected()) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                e.consume();
            }
        } else if (B.isSelected()) {
            if (!(e.getKeyChar() == '0' || e.getKeyChar() == '1')) {
                e.consume();
            }
        } else if (O.isSelected()) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '7')) {
                e.consume();
            }
        } else if (H.isSelected()) {
            if (!(e.getKeyChar() >= '0' || e.getKeyChar() <= '9')
                    && (e.getKeyChar() >= 'a' || e.getKeyChar() <='f')) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public void hexaPrint() {
        String the_number4 = f.getText();
        Integer D2 = Integer.parseInt(the_number4, 16);
        ND.setText("Decimal  :  " + D2);
        NB.setText("Binary  :  " + Integer.toBinaryString(D2));
        NO.setText("Octal  :  " + Integer.toOctalString(D2));
        NH.setText("Hexadecimal  :  " + the_number4);
    }
}


public class HW2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ConversionCalculator();
    }

}
