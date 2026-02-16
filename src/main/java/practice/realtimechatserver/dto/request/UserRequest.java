package practice.realtimechatserver.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @Size(min = 3, max = 10, message = "Your name size is not valid")
    String firstName;
    @Min(value = 3, message = "Your surname size under the minimum")
    @Max(value = 20, message = "your surname size over the max")
    String lastName;
    String email;
}
