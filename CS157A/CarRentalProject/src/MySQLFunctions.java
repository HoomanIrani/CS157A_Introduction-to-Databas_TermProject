import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLFunctions {
    private String databaseURL = "jdbc:mysql://localhost/Car_Rental?serverTimezone=UTC";
    private String user = "root";
    private String pass = "@cs157a2021";
    private Connection conn = null;
    private static PreparedStatement preparedStatement = null;

    public void login(String username, String password){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //STEP 2: Register JDBC driver (automatically done since JDBC 4.0)
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(databaseURL, user, pass);

            //STEP 4: Execute a query
            String statement = "select * from user where username = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            rs = preparedStatement.executeQuery();

            //STEP 5: Process the results
            while(rs.next()){
                System.out.println("Username: "+rs.getString("username")+", First name: "+rs.getString("firstname"));
                System.out.println("Login Successful");
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    //Return type when account already exists?
    public void createAccount(String fn, String ln, String dl, String username, String password, boolean admin){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into user (firstName, lastName, driversLicense, username, password, admin) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,fn);
            preparedStatement.setString(2, ln);
            preparedStatement.setString(3, dl);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setBoolean(6, admin);
            
            
            int result = preparedStatement.executeUpdate();

            //STEP 5: Process the results
            if(result != 0)
            	System.out.println("Account Created!");
            else
            	System.out.println("Account already exists");


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
  
    public void returnVehicle(String username, int vehicleID){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "update rental set vehicleReturned = 1 where username = ? and vehicleID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2, vehicleID);
            int result = preparedStatement.executeUpdate();

            //STEP 5: Process the results
            if (result != 0){
                System.out.println("Vehicle returned!" );
            }
            else {
                System.out.println("Vehicle already returned.");
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void deleteVehicle(int vehicleID){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "delete from car where vehicleID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1,vehicleID);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Vehicle deleted!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void addVehicle(String vehicleType, String make, String model){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into car (vehicleType, make, model) values (?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,vehicleType);
            preparedStatement.setString(2, make);
            preparedStatement.setString(3, model);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Vehicle inserted!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void deleteUser(String username){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "delete from user where username = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("User deleted!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void updatePrice(String vehicleType, int price){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "update rate set price = ? where vehicleType = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1,price);
            preparedStatement.setString(2, vehicleType);

            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Price updated!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void getRate(String vehicleType){
        Statement stmt = null;
        ResultSet rs = null;
        double vehiclePrice = 0;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "select price from rate where vehicleType = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, vehicleType);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                vehiclePrice = rs.getDouble("price");
            }

            //STEP 5: Process the results
            System.out.println("Here is the price of the vehicle: " + vehiclePrice);


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void rentVehicle(String username, int vehicleID, int days){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into rental (userName, VehicleID, returnDate) values (?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, vehicleID);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, days);
            java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());
            System.out.println(returnDate);
            preparedStatement.setDate(3, returnDate);

            preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Vehicle rented!");


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public void getMostExpensiveVehicles(){
        Statement stmt = null;
        ResultSet rs = null;
        int storeId;
        int maxPrice;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);
            // Execute a query
            String statement = "select store.storeID, MAX(price) \n"
            		+ "from store join car inner join rate on car.vehicleType = rate.vehicleType \n"
            		+ "where car.vehicleID in\n"
            		+ "		(select vehicleID \n"
            		+ "		from store as s\n"
            		+ "where vehicleID in (select s.vehicleID where s.storeID = store.storeID)) group by store.storeID;\n"
            		+ "";
            preparedStatement = conn.prepareStatement(statement);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                storeId = rs.getInt("storeID");
                maxPrice = rs.getInt("MAX(price)");
                System.out.println("Max priced vehicle is " + maxPrice + " at storeID " + storeId);
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    // Where and when to return a vehicle
    public void getReturnInfo(int vehicleID) {
        Statement stmt = null;
        ResultSet rs = null;
        String storeID = "";
        Calendar cal = Calendar.getInstance();
        java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());

        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "Select storeID, returnDate from rental, store where rental.vehicleID = store.vehicleID and rental.vehicleID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1, vehicleID);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                storeID = rs.getString("storeID");
                returnDate = rs.getDate("returnDate");
                System.out.println("Vehicle " + vehicleID + " should be returned to store " + storeID +
                        " by " + returnDate.toString());
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public void getCarsRentedAtStore(String storeId){
        Statement stmt = null;
        ResultSet rs = null;
        String vehicleType;
        int count;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "SELECT vehicleType, count(*) as count\n"
            		+ "  	FROM car AS c, rental AS r, store AS s\n"
            		+ "  	WHERE c.VehicleID = r.VehicleID and s.VehicleID =  c.VehicleID and s.storeID = ? \n"
            		+ "and r.vehicleReturned = false\n"
            		+ " 	GROUP BY c.vehicleType\n"
            		+ "HAVING count(*) > 0;\n"
            		+ "";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, storeId);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                vehicleType = rs.getString("vehicleType");
                count = rs.getInt("count");
                System.out.println("Vehicle Type: " + vehicleType + " and count is: " + count);
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void changeReturnDate(String username, int vehicleID, int days) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "update rental set returnDate = ? where username = ? and vehicleID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(2, username);
            preparedStatement.setInt(3, vehicleID);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, days);
            java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());
            System.out.println(returnDate);
            preparedStatement.setDate(1, returnDate);

            preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Vehicle return date is updated!");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public void getUserDataAsCol(String username){
        Statement stmt = null;
        ResultSet rs = null;
        List<String> userDetails = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "select username as UserDataInCol \n"
            		+ "from user\n"
            		+ "where username = ?\n"
            		+ "union\n"
            		+ "select driversLicense \n"
            		+ "from user\n"
            		+ "where username = ?\n"
            		+ "union\n"
            		+ "select firstName\n"
            		+ "from user\n"
            		+ "where username = ?\n"
            		+ "union\n"
            		+ "select lastName\n"
            		+ "from user\n"
            		+ "where username = ?;\n"
            		+ "";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, username);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                userDetails.add(rs.getString("UserDataInCol"));
            }
            
            System.out.println("Username: " + userDetails.get(0));
            System.out.println("Driver's License: " + userDetails.get(1));
            System.out.println("First Name: " + userDetails.get(2));
            System.out.println("Last Name: " + userDetails.get(3));

            
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    //Get ID and type of all cars together with username of users who have rented them at
    // a certain store. The cars that are not rented out will have null
    public void getCars(String storeID) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);
            System.out.print("Vehicles at store " + storeID + ": ");
            String statement = "SELECT c.VehicleID, c.vehicleType, r.username " +
                    "FROM car AS c " +
                    "LEFT JOIN rental AS r " +
                    "ON c.vehicleID = r.vehicleID and vehicleReturned = false " +
                    "INNER JOIN " +
                    "store as s " +
                    "ON c.vehicleID = s.vehicleID " +
                    "Where s.storeID = ? " +
                    "Order by c.vehicleID";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, storeID);

            rs = preparedStatement.executeQuery("SELECT c.VehicleID, c.vehicleType, r.username "+
                    "FROM car AS c " +
                    "LEFT JOIN rental AS r " +
                    "ON c.vehicleID = r.vehicleID and vehicleReturned = false " +
                    "INNER JOIN " +
                    "store as s " +
                    "ON c.vehicleID = s.vehicleID " +
                    "Where s.storeID =  " + storeID + " " +
                    "Order by c.vehicleID");

            // preparedStatement.execute();

            while (rs.next()) {
                System.out.println("\nVehicle ID = " + rs.getString("VehicleID") + " Vehicle Type = " + rs.getString("vehicleType")
                        + " Username = " + rs.getString("username"));
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public void insertVehicle(String vehicleType, int price){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into rate (vehicleType, price) values (?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, vehicleType);
            preparedStatement.setInt(2, price);          
            preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Vehicle inserted!");


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void addStore(String storeID, int vehicleID) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into store (storeID, vehicleID) values (?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, storeID);
            preparedStatement.setInt(2, vehicleID);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Store added");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public void archive(String date) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "CALL archive(?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, date);
            boolean result = preparedStatement.execute();

            if(!result)
            	System.out.println("Archive Successful!");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}