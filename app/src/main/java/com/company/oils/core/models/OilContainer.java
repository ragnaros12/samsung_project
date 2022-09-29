package com.company.oils.core.models;

import java.util.List;

public class OilContainer {
    private List<Oil> oils;
    private List<String> names;

    public OilContainer(List<Oil> oils, List<String> names) {
        this.oils = oils;
        this.names = names;
    }

    public List<Oil> getOils() {
        return oils;
    }

    public void setOils(List<Oil> oils) {
        this.oils = oils;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
