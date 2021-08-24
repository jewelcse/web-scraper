package com.jewelcse045.utils;

public class Params {

    private String selector;
    private String name;

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Params{" +
                "selector='" + selector + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
