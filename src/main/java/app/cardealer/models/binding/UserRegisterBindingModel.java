package app.cardealer.models.binding;

import app.cardealer.common.annotations.PasswordValidation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordValidation
public class UserRegisterBindingModel {

    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @NotNull(message = "Email cannot be null.")
    @Pattern(regexp = "^[a-zA-Z\\.-_0-9]+@[a-z]+\\.[a-z]+$", message = "Invalid email.")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Username cannot be null.")
    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 symbols long.")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "PasswordValidation cannot be null.")
    @Length(min = 3, max = 20, message = "Password must be between 3 and 20 symbols long.")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "PasswordValidation cannot be null.")
    @Length(min = 3, max = 20, message = "Password must be between 3 and 20 symbols long.")
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
