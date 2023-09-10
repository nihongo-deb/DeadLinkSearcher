package org.nihongo_deb.DeadLinkSearcher.Entity;

public enum Role {
    ROLE_USER, ROLE_ADMIN;

    public static Role stringToRole(String strRole){
        switch (strRole){
            case "ROLE_USER" -> {
                return ROLE_USER;
            }
            case "ROLE_ADMIN" -> {
                return ROLE_ADMIN;
            }
        }
        return null;
    }
}
