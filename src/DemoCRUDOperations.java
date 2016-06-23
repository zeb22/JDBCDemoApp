import java.sql.*;


/**
 * Created by condor on 26/02/15.
 * FastTrackIT, 2015
 * <p/>
 * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
 *
 * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
 * repeating the same common code multiple times
 *
 * BTW, exercise 1: how we reorg this/refactor in small pieces
 */
public class DemoCRUDOperations {


    public static void main(String[] args) {
        System.out.println("Hello database users! We are going to call DB from Java");
        try {
            //demo CRUD operations
//            demoCreate();
//            demoRead();
            demoUpdate();
//            demoDelete();

           // demoBlobInsert();
           // demoBlobRead();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void demoCreate() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver"); //se face automat in versiuni mai noi, e pus doar pentru intelegere

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_sebastian"; //adresa, IP:PORT, Data base
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO agenda (\"Nume\", \"Prenume\", \"Telefon\") VALUES (?,?,?)");
        pSt.setString(1, "Vasile");
        pSt.setString(2, "Bumbu");
        pSt.setString(3, "0742945123");

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    private static void demoRead() throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_sebastian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT \"Nume\", \"Prenume\", \"Telefon\" FROM agenda ");

        // 6. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("nume").trim());
            System.out.print(" ");
            System.out.println(rs.getString("prenume").trim());
            System.out.println("Telefon: " + rs.getString("telefon").trim());
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }

    private static void demoUpdate() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_sebastian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE agenda SET \"Nume\"=?, \"Prenume\"=?, \"Telefon\"=? WHERE \"ID\"=?"); //so we have 3 params
        pSt.setString(1, "Vasile");
        pSt.setString(2, "Bumbu");
        pSt.setString(3, "0930403");
        pSt.setLong(4, 3);

        // 5. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }


    private static void demoDelete() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_sebastian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM agenda WHERE \"ID\"=?");
        pSt.setLong(1, 5);

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 6. close the objects
        pSt.close();
        conn.close();
    }
}

