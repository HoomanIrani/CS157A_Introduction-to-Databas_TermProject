import java.sql.Date;
import java.util.Arrays;
import java.util.Scanner;

public class CarRental {
    public static void main(String[] args){
        MySQLFunctions mysql = new MySQLFunctions();
        Scanner in = new Scanner(System.in);
        User user = null;
        String response = "";
        while (!response.equals("quit")){
            if (user != null){

            } else {
               System.out.print("Enter command: ");
               response = in.nextLine();
               /* String vehicleType, String make, String model */
               String[] parseResponse = response.split(" ");
               String command = parseResponse[0];
               
               if (command.equals("login")){
                   if(parseResponse.length != 3){
                       System.out.println("Not enough information.");
                       continue;
                   }
                   String username = parseResponse[1];
                   String password = parseResponse[2];
                   mysql.login(username, password);
                }
               
               if (command.equals("addVehicle")){
                   if (parseResponse.length != 4) {
                       System.out.println("Not enough information.");
                       continue;
                   }
                   String vehicleType = parseResponse[1];
                   String make = parseResponse[2];
                   String model = parseResponse[3];
                   mysql.addVehicle(vehicleType,make, model);
                   System.out.println(Arrays.toString(parseResponse));
               }

               if (command.equals("deleteVehicle")){
                   if(parseResponse.length != 2){
                       System.out.println("Not enough information.");
                       continue;
                   }
                   String vehicleIDString = parseResponse[1];
                   int vehicleID = Integer.parseInt(vehicleIDString);
                   mysql.deleteVehicle(vehicleID);
                   System.out.println(Arrays.toString(parseResponse));
               }

               if (command.equals("updatePrice")){
                   if(parseResponse.length != 3){
                       System.out.println("Not enough information.");
                       continue;
                   }
                   String vehicleType = parseResponse[1];
                   String priceString = parseResponse[2];
                   int price = Integer.parseInt(priceString);
                   mysql.updatePrice(vehicleType, price);
                   System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("deleteUser")){
                    if(parseResponse.length != 2){
                        System.out.println("Not enough information");
                        continue;
                    }
                    String username = parseResponse[1];
                    mysql.deleteUser(username);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("returnVehicle")){
                    if(parseResponse.length != 3){
                        System.out.println("Not enough information");
                        continue;
                    }
                    String username = parseResponse[1];
                    String vehicleID = parseResponse[2];
                    int id = Integer.parseInt(vehicleID);
                    mysql.returnVehicle(username, id);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("getRate")){
                    if(parseResponse.length != 2){
                        System.out.println("Not enough information");
                        continue;
                    }
                    String vehicleType = parseResponse[1];
                    mysql.getRate(vehicleType);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("rentVehicle")){
                    if(parseResponse.length != 3){
                        System.out.println("Not enough information");
                        continue;
                    }
                    String username = parseResponse[1];
                    String vehicleId = parseResponse[2];
                    int id = Integer.parseInt(vehicleId);
                    System.out.print("How many days would you like to rent the vehicle for? ");
                    int day = in.nextInt();
                    in.nextLine();
                    mysql.rentVehicle(username, id, day);
                    System.out.println(Arrays.toString(parseResponse));
                }
              
                if (command.equals("createAccount")) {
					if (parseResponse.length != 7) {
						System.out.println("Not enough information.");
						continue;
					}
					String fn = parseResponse[1];
					String ln = parseResponse[2];
					String dl = parseResponse[3];
					String username = parseResponse[4];
					String password = parseResponse[5];
					String admin = parseResponse[6];
					mysql.createAccount(fn, ln, dl, username, password, Boolean.valueOf(admin));
				}
                if (command.equals("getMostExpensiveVehicles")) {
					if (parseResponse.length != 1) {
						System.out.println("Not enough information.");
						continue;
					}
					mysql.getMostExpensiveVehicles();
				}
                if (command.equals("getCarsRentedAtStore")) {
					if (parseResponse.length != 2) {
						System.out.println("Not enough information.");
						continue;
					}
					String storeId = parseResponse[1];
					mysql.getCarsRentedAtStore(storeId);
				}
                if (command.equals("getUserDataAsCol")) {
					if (parseResponse.length != 2) {
						System.out.println("Not enough information.");
						continue;
					}
					String username = parseResponse[1];
					mysql.getUserDataAsCol(username);
				}
                if (command.equals("insertVehicle")) {
					if (parseResponse.length != 3) {
						System.out.println("Not enough information.");
						continue;
					}
					String vehicleType = parseResponse[1];
					String price = parseResponse[2];
					mysql.insertVehicle(vehicleType, Integer.valueOf(price));
				}             

                if (command.equals("getReturnInfo")){
                    if(parseResponse.length != 2){
                        System.out.println("Not enough information.");
                        continue;
                    }
                    String vehicleID = parseResponse[1];
                    int vID = Integer.parseInt(vehicleID);
                    mysql.getReturnInfo(vID);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("changeReturnDate")){
                    if(parseResponse.length != 3){
                        System.out.println("Not enough information");
                        continue;
                    }
                    String username = parseResponse[1];
                    String vehicleId = parseResponse[2];
                    int id = Integer.parseInt(vehicleId);
                    System.out.print("How many days from today would you like to extend your rent? ");
                    int day = in.nextInt();
                    in.nextLine();
                    mysql.changeReturnDate(username, id, day);
                    System.out.println(Arrays.toString(parseResponse));
                }

                //Get ID and type of all cars together with username of users who have rented them at
                // a certain store. The cars that are not rented out will have null
                if (command.equals("getCars")){
                    if (parseResponse.length != 2) {
                        System.out.println("Not enough information.");
                        continue;
                    }
                    String storeID = parseResponse[1];
                    mysql.getCars(storeID);
                    System.out.println(Arrays.toString(parseResponse));
                }

                // Add store
                if (command.equals("addStore")){
                    if (parseResponse.length != 3) {
                        System.out.println("Not enough information.");
                        continue;
                    }
                    String storeID = parseResponse[1];
                    String vehicleID = parseResponse[2];
                    int id = Integer.parseInt(vehicleID);
                    mysql.addStore(storeID, id);
                    System.out.println(Arrays.toString(parseResponse));
                }
                if (command.equals("archive")){
                    if (parseResponse.length != 1) {
                        System.out.println("Not enough information.");
                        continue;
                    }
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter a date in format (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    mysql.archive(date);
                }
            }
        }
    }
}
