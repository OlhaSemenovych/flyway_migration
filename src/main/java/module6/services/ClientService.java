package module6.services;

import lombok.extern.slf4j.Slf4j;
import module6.Database;
import module6.dto.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClientService {

    public long create(String name) {
        long id = 0;
        try (PreparedStatement createUser = getConnection()
                .prepareStatement("INSERT INTO client (name) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            createUser.setString(1, name);
            createUser.execute();
            ResultSet resultSet = createUser.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            log.error("Creating failed", e);
        }
        return id;
    }

    public String getById(long id) {
        String name = "";
        try (PreparedStatement getUser = getConnection()
                .prepareStatement("SELECT name FROM client WHERE id = ?")
        ) {
            getUser.setLong(1, id);
            getUser.execute();
            ResultSet resultSet = getUser.executeQuery();
            resultSet.next();
            name = resultSet.getString("name");
        } catch (SQLException e) {
            log.error("Get user by id - {} process failed", id, e);
        }
        return name;
    }

    public void setName(long id, String name) {
        try (PreparedStatement setUserName = getConnection()
                .prepareStatement("UPDATE client SET name = ? WHERE id = ?");
        ) {
            setUserName.setString(1, name);
            setUserName.setLong(2, id);
            setUserName.execute();
        } catch (SQLException e) {
            log.error("Set name for user with id - {} process failed", id, e);
        }
    }

    public void deleteById(long id) {
        try (PreparedStatement deleteUser = getConnection()
                .prepareStatement("DELETE FROM client WHERE id = ?");
        ) {
            deleteUser.setLong(1, id);
            deleteUser.execute();
        } catch (SQLException e) {
            log.error("Delete user with id - {} process failed", id, e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement getAllUsers = getConnection()
                .prepareStatement("SELECT * FROM client ORDER BY id")
        ) {
            ResultSet resultSet = getAllUsers.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            log.error("Get all users process failed", e);
        }
        return clients;
    }

    private static Connection getConnection() {
        return Database.getInstance().getConnection();
    }

}
