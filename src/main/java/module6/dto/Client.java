package module6.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    private Long id;
    private String name;

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client='" + name + '\'' +
                '}';
    }

}
