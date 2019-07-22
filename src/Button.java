import java.awt.*; //Color, Dimension, FontMetrics, GradientPaint, Graphics and Graphics2D
import java.awt.event.*; //MouseEvent and MouseListener

import javax.swing.JButton;

public class Button extends JButton implements MouseListener{
    //private Color color1 = Color.WHITE, color2 = Color.LIGHT_GRAY;
    private static final Color VERY_LIGHT_GREEN = new Color(102,255,102);
    private static  final Color LIGHT_GREEN = new Color(0,255,51);
    private static final Color GREEN = new Color(0,204,0);
    private static final Color DARK_GREEN = new Color(0,102,0);
    private static final Color YELLOW = new Color(255,255,0);
    private String name;
    private Color color1;
    private Color color2;

    public Button(String name, Color color1, Color color2){
        super(name);
        this.name = name;
        this.color1 = color1;
        this.color2 = color2;
        this.addMouseListener(this);
    }
    public Button(){
        this.name = "Button";
        this.color1 = LIGHT_GREEN;
        this.color2 = DARK_GREEN;
        this.addMouseListener(this);
    }

    public Button( String name){
        this.name = name;
        this.color1 = LIGHT_GREEN;
        this.color2 = DARK_GREEN;
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, this.getHeight()/2, color2, false);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        FontMetrics fm = g2d.getFontMetrics();
        int height = fm.getHeight();
        int width = fm.stringWidth(this.name);
        g2d.setColor(Color.BLACK);
        g2d.drawString(this.name, this.getWidth()/2 - width/2, this.getHeight()/2 + height/4);

    }

    public void setColors(Color RGB_Color1, Color RGB_Color2){
        this.color1 = RGB_Color1;
        this.color2 = RGB_Color2;
    }

    public void mouseClicked(MouseEvent e) { this.setColors(YELLOW, YELLOW);}

    public void mousePressed(MouseEvent e) {
        this.setColors(YELLOW, YELLOW);
    }

    public void mouseReleased(MouseEvent e) {
        if((e.getY()>0 && e.getY()<this.getHeight()) && (e.getX()>0 && e.getX()<this.getWidth()))
            this.setColors(VERY_LIGHT_GREEN, GREEN);
        else
            this.setColors(LIGHT_GREEN, DARK_GREEN);
    }

    public void mouseEntered(MouseEvent e) {
        this.setColors(VERY_LIGHT_GREEN, Color.GREEN);
    }

    public void mouseExited(MouseEvent e) {
        this.setColors(LIGHT_GREEN, DARK_GREEN);
    }
}
