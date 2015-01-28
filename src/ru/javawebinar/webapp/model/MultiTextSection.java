package ru.javawebinar.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * GKislin
 * 26.12.2014.
 */
public class MultiTextSection extends Section {
    private List<String> values;

    public MultiTextSection(String multiValue){
        values= Arrays.asList(multiValue.split("\\n"));
    }

    public MultiTextSection(String  ... args){
        values= Arrays.asList(args);
    }

    public List<String> getValues(){
        return values;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(String s:values){
            sb.append("  ").append(s).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiTextSection)) return false;

        MultiTextSection that = (MultiTextSection) o;

        if (!values.equals(that.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }
}
