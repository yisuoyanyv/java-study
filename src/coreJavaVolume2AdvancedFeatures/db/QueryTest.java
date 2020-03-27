package coreJavaVolume2AdvancedFeatures.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-03-24-11:16 上午
 * <p>
 * This program demonstrates several complex database queries
 */

public class QueryTest {

    private static final String allQuery = "select books.price,books.title from books";

    private static final String authorPublishQuery = "select books.price,books.title"
            + " from books,booksauthors,authors,publishers"
            + " where authors.author_id = booksauthors.author_id and booksauthors.isbn=books.isbn"
            + " and books.publisher_id=publishers.publisher_id and authors.name=?"
            + " and publishers.name=?";
    private static final String authorQuery = "select books.price,books.title from books,booksauthors,authors"
            + " where authors.authors_id=booksauthors.author_id and booksauthors.isbn=books.isbn"
            + " and publishers.name=?";
    private static final String publisherQuery = "select books.price,books.title from books,publishers"
            + " where books.publisher_id=publishers.publisher_id and publishers.name=?";

    private static final String priceUpdate = "update books " + " set price=price+? " +
            " where books.publisher_id=(select publisher_id from publishers where name=?)";

    private static Scanner in;
    private static ArrayList<String> authors = new ArrayList<>();
    private static ArrayList<String> publishers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (Connection conn = getConnection()) {
            in = new Scanner(System.in);
            authors.add("Any");
            publishers.add("Any");
            try (Statement stat = conn.createStatement()) {
                //Fill the authors array list;
                String query = "select name from authors";
                try (ResultSet rs = stat.executeQuery(query)) {
                    while (rs.next()) {
                        authors.add(rs.getString(1));
                    }
                }

                //Fill the publishers array list
                query = "select name from publishers";
                try (ResultSet rs = stat.executeQuery(query)) {
                    while (rs.next()) {
                        publishers.add(rs.getString(1));
                    }
                }


            }

            boolean done = false;
            while (!done) {
                System.out.print("Q)uery C)hange prices E)xit:");
                String input = in.next().toUpperCase();
                if (input.equals("Q"))
                    executeQuery(conn);
                else if (input.equals("C"))
                    changePries(conn);
                else
                    done = true;

            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }


    }


    /**
     * Executes the selected query
     *
     * @param conn
     * @throws IOException
     */
    private static void executeQuery(Connection conn) throws SQLException {
        String author = select("Authors:", authors);
        String publisher = select("Publishers:", publishers);
        PreparedStatement stat;
        if (!author.equals("Any") && !publisher.equals("Any")) {
            stat = conn.prepareStatement(authorPublishQuery);
            stat.setString(1, author);
            stat.setString(2, publisher);
        } else if (!author.equals("Any") && publisher.equals("Any")) {
            stat = conn.prepareStatement(authorQuery);
            stat.setString(1, author);
        } else if (author.equals("Any") && !publisher.equals("Any")) {
            stat = conn.prepareStatement(publisherQuery);
            stat.setString(1, publisher);
        } else {
            stat = conn.prepareStatement(allQuery);
        }

        try (ResultSet rs = stat.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + ", " + rs.getString(2));
            }
        }
    }

    /**
     * Executes an update statement to change pricesd
     *
     * @param conn the database connection
     * @throws SQLException
     */
    public static void changePries(Connection conn) throws SQLException {
        String publisher = select("Publishers:", publishers.subList(1, publishers.size()));
        System.out.print("Change prices by:");
        double priceChange = in.nextDouble();
        PreparedStatement stat = conn.prepareStatement(priceUpdate);
        stat.setDouble(1, priceChange);
        stat.setString(2, publisher);
        int r = stat.executeUpdate();
        System.out.println(r + " records updated.");
    }

    /**
     * Asks the user to select a string
     *
     * @param prompt  the prompt to display
     * @param options the options from which the user can choose
     * @return
     */
    public static String select(String prompt, List<String> options) {
        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%2d) %s %n", i + 1, options.get(i));
            }
            int sel = in.nextInt();
            if (sel > 0 && sel <= options.size()) {
                return options.get(sel - 1);
            }
        }
    }

    /**
     * Gets a connection from properties specified in the file database.properties.
     *
     * @return the database connection
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/coreJavaVolume2AdvancedFeatures/db/database.properties"))) {
            props.load(in);
        }
        String driver = props.getProperty("jdbc.driver");
        if (driver != null) System.setProperty("jdbc.drivers", driver);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);


    }
}
