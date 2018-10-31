
package com.manriqueweb.mwepgmedia.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "year",
    "genres",
    "cast",
    "creators"
})
public class Meta implements Serializable
{

    @JsonProperty("year")
    private String year;
    @JsonProperty("genres")
    private List<String> genres = null;
    @JsonProperty("cast")
    private List<Cast> cast = null;
    @JsonProperty("creators")
    private List<Creator> creators = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8618331352658870190L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Meta() {
    }

    /**
     * 
     * @param creators
     * @param cast
     * @param genres
     * @param year
     */
    public Meta(String year, List<String> genres, List<Cast> cast, List<Creator> creators) {
        super();
        this.year = year;
        this.genres = genres;
        this.cast = cast;
        this.creators = creators;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    @JsonProperty("genres")
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("cast")
    public List<Cast> getCast() {
        return cast;
    }

    @JsonProperty("cast")
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    @JsonProperty("creators")
    public List<Creator> getCreators() {
        return creators;
    }

    @JsonProperty("creators")
    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
