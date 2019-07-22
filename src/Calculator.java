import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JInternalFrame {
    private JPanel container = new JPanel();
    private String [] tab_string = {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
    private Button [] tab_button = new Button[tab_string.length];
    private JLabel screen = new JLabel();
    private Dimension dim = new Dimension(50, 40);
    private Dimension dim2 = new Dimension(50, 31);
    private double digit1;
    private boolean click_op = false, update = false;
    private String op = "";

    public Calculator(){
        this.setSize(240, 260);
        this.setTitle("Mini Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        this.setResizable(false);
        initComponents();
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void initComponents(){
        Font font = new Font("Arial", Font.BOLD, 20);
        screen = new JLabel("0");
        screen.setFont(font);
        screen.setHorizontalAlignment(JLabel.RIGHT);
        screen.setPreferredSize(new Dimension(220,20));
        JPanel operator = new JPanel();
        operator.setPreferredSize(new Dimension(55,225));
        JPanel digit = new JPanel();
        digit.setPreferredSize(new Dimension(165,225));
        JPanel screen_pan = new JPanel();
        screen_pan.setPreferredSize(new Dimension(220,30));
        for (int i = 0; i < tab_string.length; i++){
            tab_button[i] = new Button(tab_string[i]);
            switch (i){
                case 11:
                    tab_button[i].addActionListener(new EqualListener());
                    digit.add(tab_button[i]);
                    break;

                case 12:
                    tab_button[i].setForeground(Color.RED);
                    tab_button[i].addActionListener(new ResetListener());
                    operator.add(tab_button[i]);
                    break;

                case 13:
                    tab_button[i].addActionListener(new PlusListener());
                    tab_button[i].setPreferredSize(dim2);
                    operator.add(tab_button[i]);
                    break;

                case 14:
                    tab_button[i].addActionListener(new MinusListener());
                    tab_button[i].setPreferredSize(dim2);
                    operator.add(tab_button[i]);
                    break;

                case 15:
                    tab_button[i].addActionListener(new TimesListener());
                    tab_button[i].setPreferredSize(dim2);
                    operator.add(tab_button[i]);
                    break;

                case 16:
                    tab_button[i].addActionListener(new DivListener());
                    tab_button[i].setPreferredSize(dim2);
                    operator.add(tab_button[i]);
                    break;

                 default:
                     digit.add(tab_button[i]);
                     tab_button[i].addActionListener(new DigitListener());
                     break;
            }
        }
        screen_pan.add(screen);
        screen_pan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(screen_pan, BorderLayout.NORTH);
        container.add(digit, BorderLayout.CENTER);
        container.add(operator, BorderLayout.EAST);
    }

    private void calculate(){
        if(op.equals("+")){
            digit1 += Double.valueOf(screen.getText()).doubleValue();
        }

        if(op.equals("-")){
            digit1 -= Double.valueOf(screen.getText()).doubleValue();
        }

        if(op.equals("*")){
            digit1 *= Double.valueOf(screen.getText()).doubleValue();
        }

        if(op.equals("/")){
            try{
                digit1 /= Double.valueOf(screen.getText()).doubleValue();
                screen.setText(String.valueOf(digit1));
            }catch (ArithmeticException e){
                screen.setText("0");
            }
        }
    }

    class DigitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String str = ((Button)e.getSource()).getText();
            if(update)
                update = false;
            else{
                if (!screen.getText().equals("0"))
                    str = screen.getText() + str;
            }
            screen.setText(str);
        }
    }

    class EqualListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            calculate();
            update = true;
            click_op = false;
        }
    }

    class PlusListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click_op){
                calculate();
                screen.setText(String.valueOf(digit1));
            }
            else {
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                click_op = false;
            }
            op = "+";
            update = true;
        }
    }

    class MinusListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click_op){
                calculate();
                screen.setText(String.valueOf(digit1));
            }
            else {
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                click_op = false;
            }
            op = "-";
            update = true;
        }
    }

    class TimesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click_op){
                calculate();
                screen.setText(String.valueOf(digit1));
            }
            else {
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                click_op = false;
            }
            op = "*";
            update = true;
        }
    }

    class DivListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click_op){
                calculate();
                screen.setText(String.valueOf(digit1));
            }
            else {
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                click_op = false;
            }
            op = "/";
            update = true;
        }
    }

    class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            click_op = false;
            update = true;
            digit1 = 0;
            op = "";
            screen.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
