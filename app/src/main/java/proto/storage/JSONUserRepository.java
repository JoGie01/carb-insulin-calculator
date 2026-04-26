package proto.storage;

import proto.model.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONUserRepository {
    
    private User user;
    private ObjectMapper mapper;
    private File file;

    public JSONUserRepository (String fileName) {
        mapper = new ObjectMapper();
        file = new File(fileName);
        user = load();
    }

    public User load() {
        if (!file.exists()) {
            return null;
        }
        try {
            return mapper.readValue(file, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(User user) {
        try {
            mapper.writeValue(file, user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser() {
        return user;
    }
}
