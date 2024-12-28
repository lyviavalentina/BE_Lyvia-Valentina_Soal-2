import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDDatabaseService {
    // CRUD Items
    public void createitems(int item_id, String name, String description, int price, String status, int user_id) {
        String query = "INSERT INTO items(item_id, name, description, price, status, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, item_id);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setInt(4, price);
            stmt.setString(5, status);
            stmt.setInt(6, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<items> readitems() {
        List<items> itemsList = new ArrayList<>();
        String query = "SELECT * FROM items";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                itemsList.add(new items(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("status"),
                        rs.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    public void updateitems(int item_id, String name, String description, int price, String status, int user_id) {
        String query = "UPDATE items SET name = ?, description = ?, price = ?, status = ?, user_id = ? WHERE item_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, price);
            stmt.setString(4, status);
            stmt.setInt(5, user_id);
            stmt.setInt(6, item_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteitems(int item_id) {
        String query = "DELETE FROM items WHERE item_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, item_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD Pembeli
    public void createpembeli(int user_id, String username, String password, String email) {
        String query = "INSERT INTO pembeli (user_id, username, password, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user_id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<pembeli> readpembeli() {
        List<pembeli> pembeliList = new ArrayList<>();
        String query = "SELECT * FROM pembeli";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pembeliList.add(new pembeli(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pembeliList;
    }

    public void updatepembeli(int user_id, String username, String password, String email) {
        String query = "UPDATE pembeli SET username = ?, password = ?, email = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setInt(4, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletepembeli(int user_id) {
        String query = "DELETE FROM pembeli WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        // CRUD Penjual
    public void createpenjual(int user_id, String username, String password, String email) {
        String query = "INSERT INTO penjual (user_id, username, password, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user_id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<penjual> readpenjual() {
        List<penjual> penjualList = new ArrayList<>();
        String query = "SELECT * FROM penjual";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                penjualList.add(new penjual(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return penjualList;
    }

    public void updatepenjual(int user_id, String username, String password, String email) {
        String query = "UPDATE penjual SET username = ?, password = ?, email = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setInt(4, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletepenjual(int user_id) {
        String query = "DELETE FROM penjual WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD KRS
    public void createtransaksi(int transaksi_id, int item_id, int user_id, String tanggal_transaksi, int total_price) {
        String query = "INSERT INTO transaksi (transaksi_id, item_id, user_id, tanggal_transaksi, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transaksi_id);
            stmt.setInt(2, item_id);
            stmt.setInt(3, user_id);
            stmt.setString(4, tanggal_transaksi);
            stmt.setInt(5, total_price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<transaksi> readtransaksi() {
        List<transaksi> transaksiList = new ArrayList<>();
        String query = "SELECT * FROM transaksi";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                transaksiList.add(new transaksi(
                        rs.getInt("transaksi_id"),
                        rs.getInt("item_id"),
                        rs.getInt("user_id"),
                        rs.getString("tanggal_transaksi"),
                        rs.getInt("total_price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaksiList;
    }

    public void updatetransaksi(int transaksi_id, int item_id, int user_id, String tanggal_transaksi, int total_price) {
        String query = "UPDATE transaksi SET item_id = ?, user_id = ?, tanggal_transaksi = ?, total_price = ? WHERE transaksi_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, item_id);
            stmt.setInt(2, user_id);
            stmt.setString(3, tanggal_transaksi);
            stmt.setInt(4, total_price);
            stmt.setInt(5, transaksi_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletetransaksi(int transaksi_id) {
        String query = "DELETE FROM transaksi WHERE transaksi_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transaksi_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}