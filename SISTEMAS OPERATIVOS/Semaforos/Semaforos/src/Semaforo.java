import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Semaforo {
    private int x, y;
    private Color color;
    private Timer timer;
    private JPanel panel;
    private boolean vertical;
    private int estadoInicial;
    private int direccion;

    // Caja del semáforo
    private final int anchoCaja = 40;
    private final int altoCaja = 94;
    private final int radioLuz = 23;

    public Semaforo(int x, int y, JPanel panel, boolean vertical, int estadoInicial, int direccion) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.vertical = vertical;
        this.estadoInicial = estadoInicial;
        this.direccion = direccion;

        setColorInicial(estadoInicial);

        // Configurar el temporizador para cambiar el color
        timer = new Timer(5000, new ActionListener() {
            private int estado = estadoInicial;

            public void actionPerformed(ActionEvent e) {
                if (estado == 0) {
                    color = Color.GREEN;
                } else if (estado == 1) {
                    color = Color.YELLOW;
                } else if (estado == 2) {
                    color = Color.RED;
                }
                estado = (estado + 1) % 3;
                panel.repaint();
            }
        });
        timer.start();
    }
    public int getDireccion() {
        return direccion;
    }
    private void setColorInicial(int estadoInicial) {
        if (estadoInicial == 0) {
            color = Color.GREEN;
        } else if (estadoInicial == 1) {
            color = Color.YELLOW;
        } else if (estadoInicial == 2) {
            color = Color.RED;
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void dibujar(Graphics g) {
        // Dibujar la caja del semáforo
        g.setColor(Color.BLACK);
        if (vertical) {
            g.fillRect(x - anchoCaja / 2, y - altoCaja / 2, anchoCaja, altoCaja);
        } else {
            g.fillRect(x - altoCaja / 2, y - anchoCaja / 2, altoCaja, anchoCaja);
        }

        // LUZ ROJA
        if (color == Color.RED) {
            g.setColor(Color.RED);
        } else {
            g.setColor(new Color(152, 9, 9));
        }
        if (vertical) {
            g.fillOval(x - radioLuz / 2, y - altoCaja / 2 + 10, radioLuz, radioLuz);
        } else {
            g.fillOval(x - altoCaja / 2 + 10, y - radioLuz / 2, radioLuz, radioLuz);
        }

        // LUZ AMARILLA
        if (color == Color.YELLOW) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(new Color(150, 159, 36));
        }
        if (vertical) {
            g.fillOval(x - radioLuz / 2, y - altoCaja / 2 + 35, radioLuz, radioLuz);
        } else {
            g.fillOval(x - altoCaja / 2 + 35, y - radioLuz / 2, radioLuz, radioLuz);
        }

        // LUZ VERDE
        if (color == Color.GREEN) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(new Color(39, 152, 3));
        }
        if (vertical) {
            g.fillOval(x - radioLuz / 2, y - altoCaja / 2 + 60, radioLuz, radioLuz);
        } else {
            g.fillOval(x - altoCaja / 2 + 60, y - radioLuz / 2, radioLuz, radioLuz);
        }
    }
}
