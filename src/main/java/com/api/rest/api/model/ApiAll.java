
package com.api.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonIgnoreProperties
public class ApiAll {

    @SerializedName("BrandName")
    @Expose
    private String brandName;
    @SerializedName("Features")
    @Expose
    private Features features;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("LaptopName")
    @Expose
    private String laptopName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ApiAll withBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public ApiAll withFeatures(Features features) {
        this.features = features;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ApiAll withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public ApiAll withLaptopName(String laptopName) {
        this.laptopName = laptopName;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(brandName).append(features).append(id).append(laptopName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ApiAll) == false) {
            return false;
        }
        ApiAll rhs = ((ApiAll) other);
        return new EqualsBuilder().append(brandName, rhs.brandName).append(features, rhs.features).append(id, rhs.id).append(laptopName, rhs.laptopName).isEquals();
    }

}
