package com.company.oils.core.models;

import java.util.HashMap;
import java.util.List;

public class Oil {
    private String name;
    private List<Double> variants;

    public Oil(String name, List<Double> variants) {
        this.name = name;
        this.variants = variants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getVariants() {
        return variants;
    }

    public void setVariants(List<Double> variants) {
        this.variants = variants;
    }
}
