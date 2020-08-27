import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class PersonQueries {
    private static final String URL = "jdbc:derby:AddressBook";
    private static final String USERNAME = "deitel";
    private static final String PASSWORD = "deitel";
    private Connection connection; // manages connection
    private PreparedStatement selectAllPeople;
    private PreparedStatement selectPeopleByLastName;
    private PreparedStatement selectPeopleByState;
    private PreparedStatement insertNewPerson;

    public PersonQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            selectAllPeople = connection.prepareStatement("SELECT * FROM Addresses");
            selectPeopleByLastName = connection.prepareStatement( "SELECT * FROM Addresses WHERE LastName = ?");
            selectPeopleByState = connection.prepareStatement( "SELECT * FROM Addresses WHERE State = ?");
            insertNewPerson = connection.prepareStatement( "INSERT INTO Addresses " + "(FirstName, LastName, Email, PhoneNumber, State) " + "VALUES (?, ?, ?, ?, ?)");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            //System.exit(1);
        }
    }

    public List< Person > getAllPeople() {
        List<Person> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllPeople.executeQuery();
            results = new ArrayList<Person>();

            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("State")
                ));
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }

    public List< Person > getPeopleByLastName(String name) {
        List< Person > results = null;
        ResultSet resultSet = null;
        try {
            selectPeopleByLastName.setString(1, name);
            resultSet = selectPeopleByLastName.executeQuery();
            results = new ArrayList< Person >();
            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("State")
                ));
            }
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        finally {
            try {
                resultSet.close();
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }

    public List< Person > getPeopleByState(String state) {
        List< Person > results = null;
        ResultSet resultSet = null;
        try {
            selectPeopleByState.setString(1, state);
            resultSet = selectPeopleByState.executeQuery();
            results = new ArrayList< Person >();
            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("State")
                ));
            }
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        finally {
            try {
                resultSet.close();
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }

    public int addPerson( String fname, String lname, String email, String num, String st) {
        int result = 0;
        try {
            insertNewPerson.setString(1, fname);
            insertNewPerson.setString(2, lname);
            insertNewPerson.setString(3, email);
            insertNewPerson.setString(4, num);
            insertNewPerson.setString(5, st);
            result = insertNewPerson.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    public int removePerson( String fname, String lname, String email, String num, String st) {
        int result = 0;
        try {
            String sql = "DELETE FROM Person WHERE fname = ?, lname = ?, email = ?, num = ?, st = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, num);
            ps.setString(5, st);
            result = insertNewPerson.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    public int updatePerson( String fname, String lname, String email, String num, String st) {
        int result = 0;
        try {
            String sql = "UPDATE Person SET fname = ?, lname = ?, email = ?, num = ?, st = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, num);
            ps.setString(5, st);
            ps.executeUpdate();
            result = ps.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    public void close() {
        try {
            connection.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}