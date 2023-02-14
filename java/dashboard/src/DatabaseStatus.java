public class DatabaseStatus {

  private boolean connected;

  DatabaseStatus() {
    this.connected = false;
  }

  public boolean isConnected() {
    return this.connected;
  }

  public void setConnected(boolean c) {
    this.connected = c;
  }
}
