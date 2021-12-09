package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static File file = new File("src/edu/iu/c212/users");
                                        // work on making this relative

    // line format:
    // user_name|balance|item1,item2,item3
    // user name not allowed to contain pipe

    /**
     * Write user data to the file you provided above.
     *
     * @param users The total list of all users
     */
    public static void writeUserDataToFile(List<User> users) throws IOException {
        PrintWriter write = new PrintWriter(file);
        for(int i = 0; i < users.size(); i++){
            String name = users.get(i).getUsername();
            double balance = users.get(i).getBalance();
            String inventory = users.get(i).inventoryToString();
            write.println(name + "|" + balance + "|" + inventory);
        }
        write.close();
    }

    /**
     * Read user data from the file you provided above. Return a list of Users
     */
    public static List<User> getUserDataFromFile() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(file));
        List<User> allUsers = new ArrayList<>();
        String users;
        // if the file is empty it will just return an empty user list
        while((users = read.readLine()) != null){
            String[] split1 = users.split("\\|");
            String name = split1[0];
            double balance = Double.parseDouble(split1[1]);
            // now view items
            List<Item> userItems = new ArrayList<>();
            if(split1.length == 2){
                // dont know if Items should be place to null
                //userItems = null;
                allUsers.add(new User(name,balance,userItems));
            }
            else {
                String[] splitItems = split1[2].split(",");
                for (int i = 0; i < splitItems.length; i++) {
                    userItems.add(Item.valueOf(splitItems[i]));
                }
                allUsers.add(new User(name, balance, userItems));
            }
        }
        read.close();
        return allUsers;
    }
}
