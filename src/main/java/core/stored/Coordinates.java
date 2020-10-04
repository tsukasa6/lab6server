package core.stored;

import java.io.Serializable;

public class Coordinates implements Serializable {
    // Значение поля должно быть больше -548, Поле не может быть null
    private Long x;
    private Double y;

    public Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {}

    public Long getX() {
        return this.x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
