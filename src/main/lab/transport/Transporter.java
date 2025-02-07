package lab.transport;

public interface Transporter<T extends Transportable> {
    void transport(T transportable);
}
