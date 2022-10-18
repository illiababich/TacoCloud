package tacocloud.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacocloud.User;

@Data
public class RegistrationForm {
    //@NotBlank(message="Username is required!")
    private String username;
    //@NotBlank(message="Password name is required")
    private String password;
    //@NotBlank(message="Full name is required")
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    //@NotBlank(message="Phone number is required")
    private String phone;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullName, street, city, state, zip, phone);
    }

    public User toUser(PasswordEncoder passwordEncoder, String username, String password, String fullName, String street, String city, String state, String zip, String phone) {
        return new User(username, passwordEncoder.encode(password), fullName, street, city, state, zip, phone);
    }
}
