/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbj.graphicsobjects;

import java.awt.*;

/**
 * Класс линии жизни простого процесса
 * @author alexander
 */
public class UIRectLifeLine extends UILifeLine {

    /** Номер экзампляра данного класса на сцене */
    public static int m_localNumber = 0;
    
    /**
     * Конструктор с параметрами
     * @param x Координата по Х
     * @param y Координата по У
     * @param text Текст ЛЖ
     */
    public UIRectLifeLine(int x, int y) {
         
        fillData(x,y);
        setDefaultName();
                
        SceneItemListener listener = new SceneItemListener(this);

        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        
        this.setToolTipText("Линия жизни: " + m_text);
    }

    @Override
    public void specificPaint(Graphics g) {
        
        m_textCoordX = x+10;
        m_textCoordY = y+30;
        
        Polygon dark = new Polygon();   // Полигон тени
        
        // Смещаем координаты
        x+=10;
        y+=10;

        // Рисуем полигон
        dark.addPoint(x, y);
        dark.addPoint(x + 120, y);
        dark.addPoint(x + 120, y + 50);
        dark.addPoint(x, y + 50);

        // Возвращаем координаты обратно
        x-=10;
        y-=10;
        
        Graphics2D g2 = (Graphics2D)g;
        Color c = new Color(0, 0, 0,125);   // Цвет черный и прозрачный
        Stroke stroke = new BasicStroke(1);
        g2.setStroke(stroke);
        g.setColor(c);                      // Задаем цвет
        g.fillPolygon(dark);                // Заполняем полигон 
        
        Polygon p = new Polygon();          // Полигон комментария

        // Рисуем прямоугольник
        p.addPoint(x, y);
        p.addPoint(x + 120, y);
        p.addPoint(x + 120, y + 50);
        p.addPoint(x, y + 50);

        if (m_isSelected)   // Если выбрали - заливаем желтым
            g.setColor(Color.getHSBColor(105, 215, 245));
        else                // Иначе, заливаем полигон голубоватым цветом
            g.setColor(Color.getHSBColor(205, 235, 245));

        g.fillPolygon(p);

        if (m_isSelected)   // Рисуем границы полигона красным цветом
            g.setColor(Color.red);
        else                // Рисуем границы полигона черным цветом
            g.setColor(Color.black);

        g.drawPolygon(p);
    }
    
    @Override
    protected void setDefaultName() {
        m_text = "Линия жизни " + Integer.toString(m_localNumber++);
    }
    
}
