package fr.efrei.pandax.security;

import javax.validation.constraints.NotNull;

/**
 * Represents the different {@link Role} endorsed by the users.
 */
public enum Role {
    ADMIN,
    USER;

    /**
     * Parses a {@link Role} from a {@link Role} name.
     * @param s {@link Role} name.
     * @return corresponding {@link Role}, if existing.
     */
    @NotNull
    public static Role fromString(String s) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(s)) {
                return r;
            }
        }
        throw new ClassCastException("Cannot cast Role from " + s);
    }
}
