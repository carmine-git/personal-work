import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Class_GUI {

  String SaveLogin = "";
  String SavePassword = "";

  // --------------------- VARIABLES GLOBALES
  JTable Table = new JTable();
  int uniqueID = new Random().nextInt(1_000_000_000);
  JFrame Frame = new JFrame("Patch_DataBases");
  JScrollPane TableScroll = new JScrollPane(Table);
  JPanel PanelButtons = new JPanel();
  JPanel PanelOption = new JPanel();
  JPanel PanelTable = new JPanel();
  JButton ApplyButton = new JButton("Apply");
  JButton ConnectionButton = new JButton("Q");
  JTextField TextName = new JTextField();
  JTextField TextProjectName = new JTextField();
  JTextArea TextDescription = new JTextArea();
  JLabel LabelName = new JLabel("Name/First Name:");
  JLabel LabelProjectName = new JLabel("Project Name:");
  JLabel LabelDescription = new JLabel("Project Description:");
  JLabel LabelDescriptionOfPanel = new JLabel("Add project to table.");
  JCheckBox VerifiedCheckbox = new JCheckBox("Vérifier");
  String[] option = { "None", "Supprimer", "Modifier" };
  JComboBox comboBox = new JComboBox(option);

  JButton Afficher = new JButton("Afficher");

  JLabel none1 = new JLabel("NONE");
  JLabel none2 = new JLabel("NONE");
  JLabel none3 = new JLabel("NONE");
  JLabel none4 = new JLabel("NONE");
  JLabel none5 = new JLabel("NONE");

  JLabel info1 = new JLabel("NONE");
  JLabel info2 = new JLabel("NONE");
  JLabel info3 = new JLabel("NONE");
  JLabel info4 = new JLabel("NONE");
  JLabel info5 = new JLabel("NONE");

  // ---------------------- GESTION BDD
  private DatabaseMain database;
  private Connection connection;

  public Class_GUI(int val) {
    var databaseStatus = new DatabaseStatus();
    var databaseUser = new DatabaseUser("amaryllis", "");
    var databaseConnectionBuilder = new DatabaseConnectionBuilder()
      .setPort(3306)
      .setSchema("tableaudebord");

    this.database =
      new DatabaseMain(databaseStatus, databaseUser, databaseConnectionBuilder);

    this.database.connecter();

    this.connection = this.database.getConnection();

    GetFrame();
    GetPanelButtons();
    GetPanelOption();
    GetPanelTable();

    // Ajout d'un listener pour le bouton "Apply"

    ApplyEventPerformed();
    ConnectionButtonEventPerformed();
    AfficherEventPerformed();

    if (val == 1) {
      SetComponantsPanelButtonStudent();
    }
    if (val == 2) {
      SetComponantsPanelButtonProfessor();
    }
  }

  public Connection getConnection() {
    return this.connection;
  }

  protected void GetFrame() {
    this.Frame.setSize(new Dimension(750, 600));
    this.Frame.setVisible(true);
    this.Frame.setResizable(false);
    this.Frame.setLayout(null);
    this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  protected void GetPanelTable() {
    this.PanelTable.setBounds(0, 0, 500, 600);
    this.PanelTable.setBackground(Color.WHITE);
    this.Frame.add(PanelTable);
    SetComponantsPanelTableStudent();
  }

  protected void GetPanelButtons() {
    this.PanelButtons.setBounds(0, 350, 500, 213);
    this.PanelButtons.setLayout(null);
    this.PanelButtons.setBackground(Color.LIGHT_GRAY);
    this.Frame.add(PanelButtons);

    this.ConnectionButton.setBounds(5, 170, 40, 40);
    this.ConnectionButton.setFont(new Font("Arial", Font.ITALIC, 8));
    this.PanelButtons.add(ConnectionButton);
    // SetComponantsPanelButtonStudent();
    // SetComponantsPanelButtonProfessor();
  }

  protected void GetPanelOption() {
    this.PanelOption.setBounds(500, 0, 250, 600);
    this.PanelOption.setBackground(Color.gray);
    this.Frame.add(PanelOption);
  }

  protected void SetSQLValueToVariable(
    String request,
    String username,
    String password,
    String applyRequest
  ) {
    SaveLogin = username;
    SavePassword = password;

    if (request.contains("INSERT")) {
      // LES STATEMENT INSERT NE PRODUISE PAS DE RESULT SET
      return;
    }

    Vector data = new Vector();
    Vector columnName = new Vector();

    try {
      Statement st =
        this.connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE
          );

      ResultSet rs = st.executeQuery(request);
      ResultSetMetaData metadata = rs.getMetaData();
      int numcol = metadata.getColumnCount();

      for (int i = 1; i <= numcol; i++) {
        columnName.addElement(metadata.getColumnName(i));
      }

      while (rs.next()) {
        Vector row = new Vector(numcol);

        for (int i = 1; i <= numcol; i++) {
          row.addElement(rs.getObject(i));
        }

        data.addElement(row);
      }

      rs.close();
      st.close();
    } catch (Exception e) {
      System.out.println(e);
    }

    SetSQLToTable(data, columnName);
  }

  protected void SetSQLToTable(Vector SQLdata, Vector SQLcolumn) {
    DefaultTableModel MyModelTable = new DefaultTableModel(SQLdata, SQLcolumn);
    Table.setModel(MyModelTable);
    Table.repaint();
  }

  protected void SetComponantsPanelButtonStudent() {
    // TextBoxs:
    this.TextName.setBounds(10, 30, 150, 20);
    this.TextProjectName.setBounds(10, 90, 150, 20);
    this.TextDescription.setBounds(340, 105, 150, 100);
    this.TextDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.TextDescription.setAlignmentX(JTextField.NORTH_WEST);
    this.TextDescription.setWrapStyleWord(true);
    this.TextDescription.setLineWrap(true);
    this.PanelButtons.add(TextName);
    this.PanelButtons.add(TextProjectName);
    this.PanelButtons.add(TextDescription);

    // Labels:
    this.LabelName.setBounds(10, 15, 100, 10);
    this.LabelProjectName.setBounds(10, 75, 100, 10);
    this.LabelDescription.setBounds(340, 90, 150, 10);
    this.LabelDescriptionOfPanel.setFont(new Font("Arial", Font.ITALIC, 10));
    this.LabelDescriptionOfPanel.setBounds(400, 1, 500, 20);
    this.PanelButtons.add(LabelName);
    this.PanelButtons.add(LabelProjectName);
    this.PanelButtons.add(LabelDescription);
    this.PanelButtons.add(LabelDescriptionOfPanel);

    // Buttons:

    this.ApplyButton.setBounds(160, 150, 100, 30);
    this.ApplyButton.setBorderPainted(false);
    this.PanelButtons.add(ApplyButton);

    this.Afficher.setBounds(390, 35, 100, 30);
    this.PanelButtons.add(Afficher);

    this.ConnectionButton.setEnabled(false);

    StudentComponentRepaint();
  }

  protected void StudentComponentRepaint() {
    this.TextName.repaint();
    this.TextProjectName.repaint();
    this.TextDescription.repaint();

    this.LabelName.repaint();
    this.LabelProjectName.repaint();
    this.LabelDescription.repaint();
    this.LabelDescriptionOfPanel.repaint();

    this.Afficher.repaint();

    this.ApplyButton.repaint();

    this.ConnectionButton.repaint();
  }

  protected void ProfessorComponentRepaint() {
    this.VerifiedCheckbox.repaint();
    this.comboBox.repaint();
    this.TextDescription.repaint();
    this.LabelDescription.repaint();
    this.ApplyButton.repaint();
    this.Afficher.repaint();
    this.ConnectionButton.repaint();
  }

  protected void SetComponantsPanelButtonProfessor() {
    this.VerifiedCheckbox.setBounds(430, 30, 100, 20);
    this.VerifiedCheckbox.setOpaque(false);
    this.comboBox.setBounds(397, 7, 100, 20);
    this.TextDescription.setBounds(340, 105, 150, 100);
    this.TextDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.TextDescription.setAlignmentX(JTextField.NORTH_WEST);
    this.TextDescription.setWrapStyleWord(true);
    this.TextDescription.setLineWrap(true);
    this.LabelDescription.setBounds(340, 90, 150, 10);
    this.LabelDescription.setText("Notation du proffesseur:");
    this.PanelButtons.add(this.VerifiedCheckbox);
    this.PanelButtons.add(comboBox);
    this.PanelButtons.add(TextDescription);
    this.PanelButtons.add(this.LabelDescription);

    // Buttons:
    this.ApplyButton.setBounds(160, 150, 100, 30);
    this.ApplyButton.setBorderPainted(false);
    this.PanelButtons.add(ApplyButton);

    this.Afficher.setBounds(390, 50, 100, 30);
    this.PanelButtons.add(Afficher);

    this.ConnectionButton.setEnabled(false);

    ProfessorComponentRepaint();
  }

  protected void SetComponantsPanelTableStudent() {
    this.TableScroll.setPreferredSize(new Dimension(490, 340));
    this.PanelTable.add(this.TableScroll);
  }

  protected void ApplyEventPerformed() {
    this.ApplyButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Object content = comboBox.getSelectedItem();

            String projectName = TextProjectName.getText();
            String projectDescription = TextDescription.getText();
            String name = TextName.getText();
            Statement st = null;

            try {
              st = getConnection().createStatement();
            } catch (Exception err) {
              System.out.println(err);
            }

            if (SaveLogin.equalsIgnoreCase("student")) {
              try {
                String queryStudent = String.format(
                  "INSERT INTO `tableaudebord`.`student` (`id`, `ProjectName`, `Description`, `Accepted`, `NoteProfessor`) VALUES ('%s', '%s', '%s', '%s', '%s');",
                  uniqueID,
                  projectName,
                  projectDescription,
                  "false",
                  "Une appreciation du professeur sera écrite prochainement"
                );

                String queryProf = String.format(
                  "INSERT INTO `tableaudebord`.`professeur` (`name`, `ProjectName`, `Description`, `Accepted`, `NoteProfessor`) VALUES ('%s', '%s', '%s', '%s', '%s');",
                  name,
                  projectName,
                  projectDescription,
                  "false",
                  "Une appreciation du professeur sera écrite prochainement"
                );

                st.execute(queryStudent);
                st.execute(queryProf);
                SetSQLValueToVariable(
                  "SELECT * FROM tableaudebord.student",
                  SaveLogin,
                  SavePassword,
                  "null"
                );
                SetSQLValueToVariable(
                  queryStudent,
                  SaveLogin,
                  SavePassword,
                  "null"
                );
              } catch (Exception err) {
                System.out.println(err);
              }
            }

            if (SaveLogin.equalsIgnoreCase("professeur")) {
              if (content.equals("Modifier")) {
                if (VerifiedCheckbox.isSelected()) {
                  int column = 0;
                  int row = Table.getSelectedRow();

                  String studentNameValue = Table
                    .getModel()
                    .getValueAt(row, column)
                    .toString();

                  try {
                    String query = String.format(
                      "UPDATE `tableaudebord`.`professeur` SET `Accepted` = 'true' WHERE (`name` = '%s')",
                      studentNameValue
                    );
                    st.execute(query);
                    SetSQLValueToVariable(
                      "SELECT * FROM tableaudebord.professeur",
                      SaveLogin,
                      SavePassword,
                      "null"
                    );
                  } catch (Exception err) {
                    System.out.println(err);
                  }

                  return;
                }

                int column = 0;
                int row = Table.getSelectedRow();

                String studentNameValue = Table
                  .getModel()
                  .getValueAt(row, column)
                  .toString();

                String textDescription = TextDescription.getText();

                try {
                  String query = String.format(
                    "UPDATE `tableaudebord`.`professeur` SET `NoteProfessor` = '%s' WHERE (`name` = '%s')",
                    textDescription,
                    studentNameValue
                  );
                  st.execute(query);
                  SetSQLValueToVariable(
                    "SELECT * FROM tableaudebord.professeur",
                    SaveLogin,
                    SavePassword,
                    "null"
                  );
                } catch (Exception err) {
                  System.out.println(err);
                }
              } else if (content.equals("Supprimer")) {
                try {
                  int column = 0;
                  int row = Table.getSelectedRow();
                  String value = Table
                    .getModel()
                    .getValueAt(row, column)
                    .toString();
                  String query = String.format(
                    "DELETE FROM tableaudebord.professeur WHERE name='%s'",
                    value
                  );
                  st.execute(query);
                  SetSQLValueToVariable(
                    "SELECT * FROM tableaudebord.professeur",
                    SaveLogin,
                    SavePassword,
                    "null"
                  );
                } catch (Exception err) {
                  System.out.println(err);
                }
              }
            }
          }
        }
      );
  }

  protected void OptionComponantRepaint() {
    none1.repaint();
    none2.repaint();
    none3.repaint();
    none4.repaint();
    none5.repaint();
    info1.repaint();
    info2.repaint();
    info3.repaint();
    info4.repaint();
    info5.repaint();
  }

  protected void setComponantToPanelOption() {
    this.none1.setBounds(10, 1, 200, 20);
    this.none1.setText(Table.getColumnName(Table.getColumnCount() - 5) + ":");

    this.none2.setBounds(10, 30, 200, 20);
    this.none2.setText(Table.getColumnName(Table.getColumnCount() - 4) + ":");

    this.none3.setBounds(10, 59, 200, 20);
    this.none3.setText(Table.getColumnName(Table.getColumnCount() - 3) + ":");

    this.none4.setBounds(10, 88, 200, 20);
    this.none4.setText(Table.getColumnName(Table.getColumnCount() - 2) + ":");

    this.none5.setBounds(10, 117, 200, 20);
    this.none5.setText(Table.getColumnName(Table.getColumnCount() - 1) + ":");

    this.info1.setBounds(30, 1, 200, 20);

    this.info1.setText(
        Table.getModel().getValueAt(Table.getSelectedRow(), 0).toString()
      );

    this.info2.setBounds(95, 30, 200, 20);
    this.info2.setText(
        (String) Table.getModel().getValueAt(Table.getSelectedRow(), 1)
      );

    this.info3.setBounds(85, 59, 200, 20);
    this.info3.setText(
        (String) Table.getModel().getValueAt(Table.getSelectedRow(), 2)
      );

    this.info4.setBounds(70, 88, 200, 20);
    this.info4.setText(
        (String) Table.getModel().getValueAt(Table.getSelectedRow(), 3)
      );

    this.info5.setBounds(10, 130, 200, 20);
    this.info5.setText(
        (String) Table.getModel().getValueAt(Table.getSelectedRow(), 4)
      );
    this.PanelOption.add(none1);
    this.PanelOption.add(none2);
    this.PanelOption.add(none3);
    this.PanelOption.add(none4);
    this.PanelOption.add(none5);
    this.PanelOption.add(info1);
    this.PanelOption.add(info2);
    this.PanelOption.add(info3);
    this.PanelOption.add(info4);
    this.PanelOption.add(info5);
    OptionComponantRepaint();
  }

  protected void AfficherEventPerformed() {
    this.Afficher.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            setComponantToPanelOption();
          }
        }
      );
  }

  protected void ConnectionButtonEventPerformed() {
    this.ConnectionButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JFrame NewConnectionWindow = new JFrame("Connection Page");
            NewConnectionWindow.setVisible(true);
            NewConnectionWindow.setSize(new Dimension(210, 210));
            NewConnectionWindow.setLayout(null);
            NewConnectionWindow.setResizable(false);

            JPanel connectionPanel = new JPanel();
            connectionPanel.setBounds(5, 10, 280, 250);
            connectionPanel.setBackground(Color.GRAY);
            connectionPanel.setLayout(null);

            JLabel loginLabel = new JLabel("Login:");
            JLabel passwordLabel = new JLabel("Password:");

            loginLabel.setBounds(10, 20, 100, 20);
            passwordLabel.setBounds(10, 60, 100, 20);

            JTextField Login = new JTextField();
            JTextField Password = new JTextField();

            JButton ConnectionButtonToSQL = new JButton("Connection");
            ConnectionButtonToSQL.setBounds(30, 120, 100, 20);

            ConnectionButtonToSQL.addActionListener(
              new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  if (Login.getText().equals("professeur")) {
                    SetComponantsPanelButtonProfessor();
                    SetSQLValueToVariable(
                      "SELECT * FROM tableaudebord.professeur",
                      Login.getText(),
                      Password.getText(),
                      "null"
                    );
                  } else if (Login.getText().equals("student")) {
                    SetComponantsPanelButtonStudent();
                    SetSQLValueToVariable(
                      "SELECT * FROM tableaudebord.student",
                      Login.getText(),
                      Password.getText(),
                      "null"
                    );
                  }
                  NewConnectionWindow.dispose();
                }
              }
            );

            Login.setBounds(10, 40, 150, 20);
            Password.setBounds(10, 80, 150, 20);

            NewConnectionWindow.add(connectionPanel);
            connectionPanel.add(Password);
            connectionPanel.add(Login);
            connectionPanel.add(loginLabel);
            connectionPanel.add(passwordLabel);
            connectionPanel.add(ConnectionButtonToSQL);
          }
        }
      );
  }
}
