import lab.Position;
import lab.Rotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTests {
    @Test
    public void emptyPosition() {
        Position p = new Position();
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        assertEquals(Rotation.EAST, p.getRotation());
    }

    @Test
    public void createPosition() {
        Position p = new Position(1, 2, Rotation.NORTH);
        assertEquals(1, p.getX());
        assertEquals(2, p.getY());
        assertEquals(Rotation.NORTH, p.getRotation());
    }

    @Test
    public void setsX() {
        Position p = new Position();
        p.setX(10);
        assertEquals(10, p.getX());
    }

    @Test
    public void setsY() {
        Position p = new Position();
        p.setY(10);
        assertEquals(10, p.getY());
    }

    @Test
    public void setsRotation() {
        Position p = new Position();
        p.setRotation(Rotation.SOUTH);
        assertEquals(Rotation.SOUTH, p.getRotation());
    }

    @Test
    public void equalsCopy() {
        Position p = new Position(1, 2, Rotation.NORTH);
        Position copy = p.copy();
        assertEquals(p, copy);
    }

    @Test
    public void updatesPosition() {
        Position p1 = new Position();
        Position p2 = new Position(1, 2, Rotation.NORTH);
        p1.update(p2);
        assertEquals(p2, p1);
    }

    @Test
    public void offsetsX() {
        Position p1 = new Position(1, 2, Rotation.NORTH);
        Position p2 = p1.offsetX(3);
        assertEquals(4, p2.getX());
        assertEquals(2, p2.getY());
        assertEquals(Rotation.NORTH, p2.getRotation());

        assertEquals(1, p1.getX());
        assertEquals(2, p1.getY());
        assertEquals(Rotation.NORTH, p1.getRotation());
    }

    @Test
    public void offsetsY() {
        Position p1 = new Position(1, 2, Rotation.NORTH);
        Position p2 = p1.offsetY(4);
        assertEquals(1, p2.getX());
        assertEquals(6, p2.getY());
        assertEquals(Rotation.NORTH, p2.getRotation());

        assertEquals(1, p1.getX());
        assertEquals(2, p1.getY());
        assertEquals(Rotation.NORTH, p1.getRotation());
    }

    @Test
    public void offsetsForward() {
        Position p1 = new Position(1, 2, Rotation.NORTH);
        Position p2 = p1.offsetForward(3);
        assertEquals(p1.offsetY(3), p2);
    }

    @Test
    public void offsetsRight() {
        Position p1 = new Position(1, 2, Rotation.NORTH);
        Position p2 = p1.offsetRight(3);
        assertEquals(p1.offsetX(3), p2);
    }

    @Test
    public void turnsRight() {
        Position p1 = new Position(1, 2, Rotation.SOUTH);
        Position p2 = p1.turnedRight();

        assertEquals(1, p2.getX());
        assertEquals(2, p2.getY());
        assertEquals(Rotation.WEST, p2.getRotation());

        assertEquals(1, p1.getX());
        assertEquals(2, p1.getY());
        assertEquals(Rotation.SOUTH, p1.getRotation());
    }

    @Test
    public void turnsLeft() {
        Position p1 = new Position(1, 2, Rotation.SOUTH);
        Position p2 = p1.turnedLeft();

        assertEquals(1, p2.getX());
        assertEquals(2, p2.getY());
        assertEquals(Rotation.EAST, p2.getRotation());

        assertEquals(1, p1.getX());
        assertEquals(2, p1.getY());
        assertEquals(Rotation.SOUTH, p1.getRotation());
    }
}
