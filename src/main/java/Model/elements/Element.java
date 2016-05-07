package model.elements;

import java.util.List;

public class Element {

    //Attributes
    private String name;

    //Methods
    public Element() {
        this.setName("");
    }

    public Element(String name) {
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void init() {}

    protected static <T> void addToCollection(T item, List<T> collection) {
        if (item != null) {
            if (!collection.contains(item)){
                collection.add(item);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Element)) {
            return false;
        }
        Element otherElement = (Element) other;
        return (this.name.equals(otherElement.name));
    }

}
