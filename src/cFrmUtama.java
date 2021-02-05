import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.Math;

import static java.lang.Math.round;

public class cFrmUtama extends javax.swing.JFrame {
    File f;
    BufferedImage Gambar;
    int xS, yS, xC, yC;
    double angle;
    int[] polyX = {400, 600, 800, 600, 400, 200};
    int[] polyY = {100, 100, 300, 500, 500, 300};

    public void Circle(double CenterX, double CenterY, double Radius, int Warna) {
        angle = 0;
        while (angle <= 360) {
            xC = (int) Math.round(Radius * Math.cos(angle * Math.PI / 180));
            yC = (int) Math.round(Radius * Math.sin(angle * Math.PI / 180));
            xS = (int) (CenterX + xC);
            yS = (int) (CenterY - yC);
            Gambar.setRGB(xS, yS, Warna);
            angle += 30 / Radius;
        }
    }
    public void Arc(double CenterX, double CenterY, double Radius,double StartAngle, double SweepAngle, int Warna){
        angle = StartAngle;
        while (angle <= StartAngle + SweepAngle){
            xC = (int) Math.round(Radius * Math.cos(angle * Math.PI/180));
            yC = (int) Math.round(Radius * Math.sin(angle * Math.PI / 180));
            xS = (int) (CenterX + xC);
            yS = (int) (CenterY - yC);
            Gambar.setRGB(xS, yS, Warna);
            angle += 30 / Radius;
        }
    }
    public void Ellipse(double CenterX, double CenterY, double RadiusX, double RadiusY, int Warna){
        angle = 0;
        double Radius;
        if (RadiusX < RadiusY){
            Radius = RadiusX;
        }else Radius = RadiusY;
        while (angle <= 360){
            xC = (int) Math.round(RadiusX * Math.cos(angle * Math.PI/180));
            yC = (int) Math.round(RadiusY * Math.sin(angle * Math.PI / 180));
            xS = (int) (CenterX + xC);
            yS = (int) (CenterY - yC);
            Gambar.setRGB(xS, yS, Warna);
            angle += 30 / Radius;
        }
    }
    public void CustomDiagonal(int x1, int x2, int y1, int y2, int Warna){
        int dx, dy, xLength, yLength, Count, i;
        float x, y, rx, ry;
        x = x1; y = y1; dx = x2 - x1; dy = y2 - y1; xLength = Math.abs(dx); yLength = Math.abs(dy);
        if (xLength>yLength){
            rx = 1;
            ry = Math.abs(yLength/xLength);
            Count = xLength;
        }else{
            rx = Math.abs(xLength/yLength);
            ry = 1;
            Count = yLength;
        }
        if(dx<0) rx=-rx;
        if (dy<0) ry=-ry;
        for(i = 0; i<Count; i++){
            Gambar.setRGB(round(x), round(y), Warna);
            x += rx; y += ry;
        }
    }
    public void Hexagon(int[] x, int[] y, int numOfPoints, int color){
        int i = 0;
        while (i < numOfPoints-1){
            CustomDiagonal(x[i], x[i+1], y[i], y[i+1], color);
            i++;
        }
        CustomDiagonal(x[numOfPoints-1], x[0], y[numOfPoints-1],y[0], color);
    }
    public void Fill(int x, int y, int Warna){
        int BgColor = Gambar.getRGB(x, y);
        boolean loopY, loopX;
        loopY = true;
        int y1, x1, x2 = 0, y2;
        y1 = y;
        while (y1>0 && loopY){
             loopX = true;
             x1 = x;
             while (x1>0 && loopX){
                 if(Gambar.getRGB(x1, y1) != BgColor) loopX=false;
                 else Gambar.setRGB(x1, y1, Warna);
                 x1--;
             }
             while (x2<WIDTH && loopX){
                 if(Gambar.getRGB(x2, y1) != BgColor) loopX=false;
                 else Gambar.setRGB(x2, y1, Warna);
                 x2++;
             }
             y1--;
            if(Gambar.getRGB(x, y1) != BgColor) loopY=false;
        }
        loopY = true;
        y2 = y+1;
        while (y2<HEIGHT && loopY){
            loopX = true;
            x1 = x;
            while (x1>0 && loopX){
                if(Gambar.getRGB(x1, y2) != BgColor) loopX=false;
                else Gambar.setRGB(x1, y2, Warna);
                x1--;
            }
            x2 = x+1;
            while (x2<WIDTH && loopX){
                if(Gambar.getRGB(x2, y2) != BgColor) loopX=false;
                else Gambar.setRGB(x2, y2, Warna);
                x2++;
            }
            y2++;
            if(Gambar.getRGB(x, y2) != BgColor) loopY=false;
        }
    }
    public void EmptyRectangle (int x1, int x2, int y1, int y2, int Warna){
        for(int x=x1; x<x2; x++){
            Gambar.setRGB(x, y1, Warna);
            Gambar.setRGB(x,y2, Warna);
        }
        for(int y=y1; y<y2; y++){
            Gambar.setRGB(x1, y, Warna);
            Gambar.setRGB(x2, y, Warna);
        }
    }
    public cFrmUtama() {
        int x, y, Warna = 0x00FF00;

        initComponents();
        Gambar = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 1920; i++) {
            for (int j = 0; j < 1080; j++) {
                Gambar.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        Circle(100, 100, 50, 0x000000);
        Arc(200, 100, 100, 90, 180, 0x4def45);
        Ellipse(200, 100, 100, 50, 0x343434);
        Hexagon(polyX, polyY, 6, 0x000000);
        EmptyRectangle(100, 300, 250, 350, 0xac4cae);
        Fill(299, 349, 0x003355);
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 986, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 614, Short.MAX_VALUE)
        );

        pack();
    }

    public void paint(Graphics g) {
        g.drawImage(Gambar, 0, 30, this);
        this.setTitle("Primitive Geometry 2");
    }
}
