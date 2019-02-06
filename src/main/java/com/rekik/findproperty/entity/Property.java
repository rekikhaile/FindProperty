package com.rekik.findproperty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//public class Property implements Comparable<Property>{
public class Property{


    private Address address;
    private String home_id;
    private String owner;
    private long value;


    public Property() {
        super();
    }

    public Property(Address address, String home_id, String owner, long value) {
        super();
        this.address = address;
        this.home_id = home_id;
        this.owner = owner;
        this.value = value;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getHome_id() {
        return home_id;
    }

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property [address=" + address + ", home_id=" + home_id + ", owner=" + owner + ", value=" + value + "]";
    }


   /* @Override
    public int compareTo(Property o) {
        if(this.getValue() > o.getValue())
            return 0;
        else
            return 1;
    }*/
}
