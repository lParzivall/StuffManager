package model;

import model.HumanInterface;

public abstract class AbstractPerson implements HumanInterface {

    private String name;

    public AbstractPerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract String think();
}
