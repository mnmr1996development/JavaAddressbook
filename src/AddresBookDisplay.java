import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

class AddressBookDisplay extends JFrame {
    private Person currentEntry;
    private PersonQueries personQueries;
    private List<Person> results;
    private int numberOfEntries = 0;
    private int currentEntryIndex;

    private JButton browseButton;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel idLabel;
    private JTextField idTextField;
    private JTextField indexTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JTextField maxTextField;
    private JButton nextButton;
    private JLabel ofLabel;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JButton previousButton;
    private JButton queryButton;
    private JLabel queryLabel;
    private JPanel queryPanel;
    private JPanel navigatePanel;
    private JPanel displayPanel;
    private JTextField queryTextField;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel stateNameLabel;
    private JTextField stateNameTextField;
    private JButton stateQueryButton;
    private JLabel stateQueryLabel;
    private JPanel stateQueryPanel;
    private JTextField stateQueryTextField;


    public AddressBookDisplay() {
        super("Address Book");
        personQueries = new PersonQueries();

        navigatePanel = new JPanel();
        previousButton = new JButton();
        indexTextField = new JTextField(2);
        ofLabel = new JLabel();
        maxTextField = new JTextField(2);
        nextButton = new JButton();
        displayPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField(10);
        firstNameLabel = new JLabel();
        firstNameTextField = new JTextField(10);
        lastNameLabel = new JLabel();
        lastNameTextField = new JTextField(10);
        emailLabel = new JLabel();
        emailTextField = new JTextField(10);
        phoneLabel = new JLabel();
        phoneTextField = new JTextField(10);
        queryPanel = new JPanel();
        queryLabel = new JLabel();
        queryTextField = new JTextField(10);
        queryButton = new JButton();
        browseButton = new JButton();
        insertButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        stateNameLabel = new JLabel();
        stateNameTextField = new JTextField(10);
        stateQueryPanel = new JPanel();
        stateQueryLabel = new JLabel();
        stateQueryTextField = new JTextField(10);
        stateQueryButton = new JButton();

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setSize(900, 600);
        setResizable(false);

        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.X_AXIS));

        previousButton.setText("Previous");
        previousButton.setEnabled(false);
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        navigatePanel.add(previousButton);
        navigatePanel.add(Box.createHorizontalStrut(10));

        indexTextField.setHorizontalAlignment(JTextField.CENTER);
        indexTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                indexTextFieldActionPerformed(evt);
            }
        });
        navigatePanel.add(indexTextField);
        navigatePanel.add(Box.createHorizontalStrut(10));

        ofLabel.setText("of");
        navigatePanel.add(ofLabel);
        navigatePanel.add(Box.createHorizontalStrut(10));

        maxTextField.setHorizontalAlignment(JTextField.CENTER);
        maxTextField.setEditable(false);
        navigatePanel.add(maxTextField);
        navigatePanel.add(Box.createHorizontalStrut(10));

        nextButton.setText("Next");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        navigatePanel.add(nextButton);
        add(navigatePanel);

        displayPanel.setLayout(new GridLayout(6, 2, 4, 4));

        idLabel.setText("Address ID:");
        displayPanel.add(idLabel);
        idTextField.setEditable(false);
        displayPanel.add(idTextField);

        firstNameLabel.setText("First Name:");
        displayPanel.add(firstNameLabel);
        displayPanel.add(firstNameTextField);

        lastNameLabel.setText("Last Name:");
        displayPanel.add(lastNameLabel);
        displayPanel.add(lastNameTextField);

        emailLabel.setText("Email:");
        displayPanel.add(emailLabel);
        displayPanel.add(emailTextField);

        phoneLabel.setText("Phone Number:");
        displayPanel.add(phoneLabel);
        displayPanel.add(phoneTextField);

        stateNameLabel.setText("State:");
        displayPanel.add(stateNameLabel);
        displayPanel.add(stateNameTextField);

        add(displayPanel);

        JPanel searchPanel = new JPanel();

        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        queryPanel.setBorder(BorderFactory.createTitledBorder("Find an entry by last name"));
        queryLabel.setText("Last Name:");
        queryPanel.add(Box.createHorizontalStrut(5));
        queryPanel.add(queryLabel);
        queryPanel.add(Box.createHorizontalStrut(10));
        queryPanel.add(queryTextField);
        queryPanel.add(Box.createHorizontalStrut(10));

        queryButton.setText("Find");
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                queryButtonActionPerformed(evt);
            }
        });

        queryPanel.add(queryButton);
        queryPanel.add(Box.createHorizontalStrut(5));
        searchPanel.add(queryPanel);

        stateQueryPanel.setBorder(BorderFactory.createTitledBorder("Find an entry by state"));
        stateQueryLabel.setText("State:");
        stateQueryPanel.add(Box.createHorizontalStrut(5));
        stateQueryPanel.add(stateQueryLabel);
        stateQueryPanel.add(Box.createHorizontalStrut(10));
        stateQueryPanel.add(stateQueryTextField);
        stateQueryPanel.add(Box.createHorizontalStrut(10));

        stateQueryButton.setText("Find");
        stateQueryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stateQueryButtonActionPerformed(evt);
            }
        });

        stateQueryPanel.add(stateQueryButton);
        stateQueryPanel.add(Box.createHorizontalStrut(5));
        searchPanel.add(stateQueryPanel);

        add(searchPanel);

        browseButton.setText("Browse All Entries");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });
        add(browseButton);

        insertButton.setText("Insert New Entry");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });
        add(insertButton);

        updateButton.setText("Update Entry");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        add(updateButton);

        deleteButton.setText("Delete Entry");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton);

        addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                personQueries.close(); // close database connection
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private void previousButtonActionPerformed(ActionEvent evt) {
        currentEntryIndex--;
        if (currentEntryIndex < 0)
            currentEntryIndex = numberOfEntries - 1;
        indexTextField.setText("" + (currentEntryIndex + 1));
        indexTextFieldActionPerformed(evt);
    }

    private void nextButtonActionPerformed(ActionEvent evt) {
        currentEntryIndex++;
        if (currentEntryIndex >= numberOfEntries)
            currentEntryIndex = 0;
        indexTextField.setText("" + (currentEntryIndex + 1));
        indexTextFieldActionPerformed(evt);
    }

    private void queryButtonActionPerformed(ActionEvent evt) {
        results = personQueries.getPeopleByLastName(queryTextField.getText());
        numberOfEntries = results.size();
        if (numberOfEntries != 0) {
            currentEntryIndex = 0;
            currentEntry = results.get(currentEntryIndex);
            idTextField.setText("" + currentEntry.getAddressID());
            firstNameTextField.setText(currentEntry.getFirstName());
            lastNameTextField.setText(currentEntry.getLastName());
            emailTextField.setText(currentEntry.getEmail());
            phoneTextField.setText(currentEntry.getPhoneNumber());
            maxTextField.setText("" + numberOfEntries);
            indexTextField.setText("" + (currentEntryIndex + 1));
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
        }
        else
            browseButtonActionPerformed(evt);
    }

    private void stateQueryButtonActionPerformed(ActionEvent evt) {
        results = personQueries.getPeopleByState(stateQueryTextField.getText());
        numberOfEntries = results.size();
        if (numberOfEntries != 0) {
            currentEntryIndex = 0;
            currentEntry = results.get(currentEntryIndex);
            idTextField.setText("" + currentEntry.getAddressID());
            firstNameTextField.setText(currentEntry.getFirstName());
            lastNameTextField.setText(currentEntry.getLastName());
            emailTextField.setText(currentEntry.getEmail());
            phoneTextField.setText(currentEntry.getPhoneNumber());
            maxTextField.setText("" + numberOfEntries);
            indexTextField.setText("" + (currentEntryIndex + 1));
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
        }
        else
            browseButtonActionPerformed(evt);
    }

    private void indexTextFieldActionPerformed(ActionEvent evt) {
        currentEntryIndex = (Integer.parseInt(indexTextField.getText()) - 1);
        if (numberOfEntries != 0 && currentEntryIndex < numberOfEntries) {
            currentEntry = results.get(currentEntryIndex);
            idTextField.setText("" + currentEntry.getAddressID());
            firstNameTextField.setText(currentEntry.getFirstName());
            lastNameTextField.setText(currentEntry.getLastName());
            emailTextField.setText(currentEntry.getEmail());
            phoneTextField.setText(currentEntry.getPhoneNumber());
            maxTextField.setText("" + numberOfEntries);
            indexTextField.setText("" + (currentEntryIndex + 1));
        }
    }

    private void browseButtonActionPerformed(ActionEvent evt) {
        try {
            results = personQueries.getAllPeople();
            numberOfEntries = results.size();
            if (numberOfEntries != 0) {
                currentEntryIndex = 0;
                currentEntry = results.get(currentEntryIndex);
                idTextField.setText("" + currentEntry.getAddressID());
                firstNameTextField.setText(currentEntry.getFirstName());
                lastNameTextField.setText(currentEntry.getLastName());
                emailTextField.setText(currentEntry.getEmail());
                phoneTextField.setText(currentEntry.getPhoneNumber());
                maxTextField.setText("" + numberOfEntries);
                indexTextField.setText("" + (currentEntryIndex + 1));
                nextButton.setEnabled(true);
                previousButton.setEnabled(true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertButtonActionPerformed(ActionEvent evt) {
        int result = personQueries.addPerson(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneTextField.getText(),stateNameTextField.getText());
        if (result == 1)
            JOptionPane.showMessageDialog(this, "Person added!", "Person added", JOptionPane.PLAIN_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Person not added!", "Error", JOptionPane.PLAIN_MESSAGE);
        browseButtonActionPerformed(evt);
    }

    private void updateButtonActionPerformed(ActionEvent evt) {
        int result = personQueries.updatePerson(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneTextField.getText(), stateNameTextField.getText());
        if (result == 1)
            JOptionPane.showMessageDialog(this, "Person updated!", "Person updated", JOptionPane.PLAIN_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Person not updated!", "Error", JOptionPane.PLAIN_MESSAGE);
        browseButtonActionPerformed(evt);
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        int result = personQueries.removePerson(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneTextField.getText(), stateNameTextField.getText());
        if (result == 1)
            JOptionPane.showMessageDialog(this, "Person removed!", "Person removed", JOptionPane.PLAIN_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Person not removed!", "Error", JOptionPane.PLAIN_MESSAGE);
        browseButtonActionPerformed(evt);
    }


    public static void main(String args[]) {
        new AddressBookDisplay();
    }
}