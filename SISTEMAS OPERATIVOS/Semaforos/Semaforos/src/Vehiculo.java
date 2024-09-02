import javax.swing.*;
import java.awt.*;

class Vehiculo {
    private int x, y;
    private int velocidad;
    private Color color;
    private int direccion;
    private Image imagenVehiculo;

    // Direcciones
    public static final int DIRECCION_HORIZONTAL = 0;
    public static final int DIRECCION_VERTICAL = 1;

    public Vehiculo(int x, int y, int velocidad, Color color, int direccion, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.color = color;
        this.direccion = direccion;
        this.imagenVehiculo = new ImageIcon(rutaImagen).getImage();
    }

    public void mover(Semaforo[] semaforos) {
        // Verificar la proximidad al semáforo
        boolean cercaDeSemaforo = false;
        for (Semaforo semaforo : semaforos) {
            if (direccion == DIRECCION_HORIZONTAL && semaforo.getDireccion() == DIRECCION_VERTICAL) {
                if (esCercano(semaforo) && x < semaforo.getX()) {
                    cercaDeSemaforo = true;
                    if (semaforo.getColor() == Color.RED || semaforo.getColor() == Color.YELLOW) {
                        velocidad = 0;
                    } else {
                        velocidad = 5;
                    }
                    break;
                }
            } else if (direccion == DIRECCION_VERTICAL && semaforo.getDireccion() == DIRECCION_HORIZONTAL) {
                if (esCercano(semaforo) && y > semaforo.getY()) {
                    cercaDeSemaforo = true;
                    if (semaforo.getColor() == Color.RED || semaforo.getColor() == Color.YELLOW) {
                        velocidad = 0;
                    } else {
                        velocidad = 5;
                    }
                    break;
                }
            }
        }

        if (!cercaDeSemaforo) {
            velocidad = 5;
        }

        // Mover el vehículo
        if (direccion == DIRECCION_HORIZONTAL) {
            x += velocidad;
            if (x > 1080) {
                x = -20;
            }
        } else if (direccion == DIRECCION_VERTICAL) {
            y -= velocidad;
            if (y < -10) {
                y = 800;
            }
        }
    }

    private boolean esCercano(Semaforo semaforo) {
        int distanciaX = Math.abs(x - semaforo.getX());
        int distanciaY = Math.abs(y - semaforo.getY());

        int radioDeteccionX = 300;
        int radioDeteccionY = 10;

        // Considerar el radio de detección dependiendo de la dirección
        if (direccion == DIRECCION_HORIZONTAL) {
            return x > semaforo.getX() - radioDeteccionX && x < semaforo.getX() + radioDeteccionX;
        } else if (direccion == DIRECCION_VERTICAL) {
            return y > semaforo.getY() - radioDeteccionY && y < semaforo.getY() + radioDeteccionY;
        }

        return false;
    }

    public void dibujar(Graphics g) {
        if (color == Color.RED) {
            g.drawImage(imagenVehiculo, x, y, 300, 100, null);
        }
        if (color == Color.WHITE) {
            g.drawImage(imagenVehiculo, x, y, 100, 300, null);
        }

    }
}


