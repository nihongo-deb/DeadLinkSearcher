package org.nihongo_deb.DeadLinkSearcher.DTO;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmedPassword;
    private String email;
    private Long telegramId;
}
