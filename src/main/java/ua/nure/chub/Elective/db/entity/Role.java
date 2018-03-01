package ua.nure.chub.Elective.db.entity;

/**
 * Author Lera
 * created 01.09.2017.
 */
public enum Role {
    ADMIN, STUDENT, TEACHER;

    public static Role getRole(int id) {
        return Role.values()[id-1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
