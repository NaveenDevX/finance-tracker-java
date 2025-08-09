import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserManage {
    private static final String FILE = "users.csv";
    private Map<String, String> map = new HashMap<>();

    public UserManage() {
        loadUsers();
    }

    // Create a new user
    public boolean createUser(String username, String password) {
        username = username.toLowerCase(); // normalize usernames
        if (map.containsKey(username)) {
            return false; // username already exists
        }
        String hash = hashPassword(password);
        map.put(username, hash);
        saveUsers();
        return true;
    }

    // Validate user login
    public boolean validateUser(String username, String password) {
        username = username.toLowerCase();
        if (!map.containsKey(username)) {
            return false;
        }
        String storedHash = map.get(username);
        return storedHash.equals(hashPassword(password));
    }

    // Load users from file
    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.strip().split(",");
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // File may not exist on first run - that's fine
        }
    }

    // Save users to file
    private void saveUsers() {
        try (PrintWriter p = new PrintWriter(new FileWriter(FILE))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                p.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hash password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
