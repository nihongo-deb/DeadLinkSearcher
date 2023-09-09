package org.nihongo_deb.DeadLinkSearcher.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
