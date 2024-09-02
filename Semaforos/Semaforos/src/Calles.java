import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Calles extends JPanel {

    private Semaforo semaforo1;
    private Semaforo semaforo2;
    private List<Vehiculo> vehiculos;
    int ancho = 300;

    public Calles() {
        semaforo1 = new Semaforo(0, 0, this, true, 2,1);
        semaforo2 = new Semaforo(0, 0, this, false, 0,0);

        vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo(0, 300, 5, Color.RED, Vehiculo.DIRECCION_HORIZONTAL, "src/images/carroRojo.png"));
        vehiculos.add(new Vehiculo(800, -10, 5, Color.WHITE, Vehiculo.DIRECCION_VERTICAL, "src/images/carroBlanco.png"));

        Timer timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizar();
                repaint();
            }
        });
        timer.start();
    }

    private void actualizar() {
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mover(new Semaforo[]{semaforo1, semaforo2});
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int verticalSemaforoX = width / 2 - 170;
        int verticalSemaforoY = height / 2 - 102;

        int horizontalSemaforoX = width / 2 + 102;
        int horizontalSemaforoY = height / 2 + 170;

        semaforo1.setPosition(verticalSemaforoX, verticalSemaforoY);
        semaforo2.setPosition(horizontalSemaforoX, horizontalSemaforoY);

        // Color de fondo
        g.setColor(new Color(37, 166, 5));
        g.fillRect(0, 0, width, height);

        // Dibujar la calle vertical
        g.setColor(Color.DARK_GRAY);
        g.fillRect(width / 2 - ancho / 2, 0, ancho, height);

        // Dibujar la calle horizontal
        g.fillRect(0, height / 2 - ancho / 2, width, ancho);

        // Delimitando el Centro
        int centroInicioX = width / 2 - ancho / 2;
        int centroFinX = width / 2 + ancho / 2;
        int centroInicioY = height / 2 - ancho / 2;
        int centroFinY = height / 2 + ancho / 2;

        // Dibujando líneas discontinuas en la calle vertical
        g.setColor(Color.WHITE);
        int longitudLinea = 30;
        int espacio = 20;
        for (int y = 0; y < height; y += longitudLinea + espacio) {
            if (y < centroInicioY || y > centroFinY) {
                g.fillRect(width / 2 - 5, y, 10, longitudLinea);
            }
        }

        // Dibujando líneas discontinuas en la calle horizontal
        for (int x = 0; x < width; x += longitudLinea + espacio) {
            if (x < centroInicioX || x > centroFinX) {
                g.fillRect(x, height / 2 - 5, longitudLinea, 10);
            }
        }

        // Dibujar los semáforos
        semaforo1.dibujar(g);
        semaforo2.dibujar(g);

        // Dibujar los vehículos
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.dibujar(g);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Semáforos");
        Calles calles = new Calles();

        // Configurar para que se abra en pantalla completa
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(calles);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




