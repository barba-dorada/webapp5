package ru.javawebinar.webapp.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * GKislin
 * 26.12.2014.
 */
public class OrganizationSection extends Section {
    private List<Organization> values;

    public OrganizationSection(Organization... values) {
        this.values = new LinkedList<>(Arrays.asList(values));
    }

    public OrganizationSection(List<Organization> values) {
        this.values = new LinkedList<>(values);
    }

    public List<Organization> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationSection)) return false;

        OrganizationSection that = (OrganizationSection) o;

        if (!values.equals(that.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "values=" + values +
                '}';
    }
}
