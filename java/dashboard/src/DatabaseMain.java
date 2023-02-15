import java.sql.*;
import java.util.logging.*;

public class DatabaseMain {

  private DatabaseStatus status;
  private DatabaseUser user;
  DatabaseConnectionBuilder connectionBuilder;
  private Connection connection;
  private static final Logger LOGGER = Logger.getLogger(
    DatabaseMain.class.getName()
  );

  DatabaseMain(
    DatabaseStatus status,
    DatabaseUser user,
    DatabaseConnectionBuilder connection
  ) {
    this.status = status;
    this.user = user;
    this.connectionBuilder = connection;
    this.connection = null;
  }

  public void connecter() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException classNotFound) {
      LOGGER.warning(
        String.format(
          "Une erreur est survenue en tentant de trouver les drivers nécessaire à la connection pour raison: %s",
          classNotFound
        )
      );
    }

    try {
      this.connection =
        DriverManager.getConnection(
          this.connectionBuilder.getLink(),
          this.user.getUsername(),
          this.user.getPassword()
        );
      status.setConnected(true);
    } catch (SQLException sqlException) {
      status.setConnected(false);
      LOGGER.warning(
        String.format(
          "Une erreur est survenue en tentant de se connecter à la base de données pour raison: %s",
          sqlException
        )
      );
    }

    if (status.isConnected()) {
      LOGGER.info(String.format("Connecté à la base de donnée"));
      try {
        this.connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE
          );
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  public Connection getConnection() {
    return this.connection;
  }

  public ResultSet executeQuery(String query) {
    Connection connection = getConnection();
    ResultSet rs = null;
    try {
      Statement statement = connection.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE
      );
      rs = statement.executeQuery(query);
    } catch (Exception e) {
      System.out.println(e);
    }

    return rs;
  }
}
