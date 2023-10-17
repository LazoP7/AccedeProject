package project.accede.dto.user.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserDTO {

//    @NotBlank(message = "Name cannot be blank")
//    @Min(value = 3, message = "Name cannot be smaller than 3 characters")
//    @Max(value = 45, message = "Name cannot be bigger than 45 characters")
    private String name;

//    @NotNull(message = "Surname cannot be null")
//    @Max(value = 45, message = "Name cannot be bigger than 45 characters")
    private String surname;

//    @NotBlank(message = "Username cannot be blank")
//    @Min(value = 4, message = "Username cannot be smaller than 4 characters")
//    @Max(value = 45, message = "Username cannot be bigger than 45 characters")
    private String username;

//    @NotBlank(message = "Email cannot be blank")
    private String mail;

//    @NotBlank(message = "Password cannot be blank")
    private String password;

    private Integer reputation;

    private Integer age;

    private String profDescr;

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", reputation=" + reputation +
                ", age=" + age +
                ", profDescr='" + profDescr + '\'' +
                '}';
    }
}
