package ru.job4j.generic;

public class Role extends Base {
    private final String username;

    public Role(String id, String name) {
        super(id);
        this.username = name;
    }

    public String getRoleName() {
        return username;
    }
}

