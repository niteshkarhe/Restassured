
package com.api.rest.api.xmlmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ApiXmlPost {

    @SerializedName("Laptop")
    @Expose
    private Laptop laptop;

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public ApiXmlPost withLaptop(Laptop laptop) {
        this.laptop = laptop;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(laptop).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ApiXmlPost) == false) {
            return false;
        }
        ApiXmlPost rhs = ((ApiXmlPost) other);
        return new EqualsBuilder().append(laptop, rhs.laptop).isEquals();
    }

}
