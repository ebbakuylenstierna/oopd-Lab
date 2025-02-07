package lab.transport;

public interface Transportable {
    void startTransport(Transporter transporter);
    void endTransport();
    Transporter getTransporter();
}
