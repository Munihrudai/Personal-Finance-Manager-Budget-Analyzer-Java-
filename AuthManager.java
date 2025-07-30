import java.io.*;

public class AuthManager {
    private static final String FILE_PATH = "data/users.txt";

    public static void register(String username, String password) {
        try {
            new File("data").mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            writer.write(username + "," + password + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String username, String password) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return false;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
