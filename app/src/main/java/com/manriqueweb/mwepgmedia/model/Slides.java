
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
    "slides"
})
public class Slides implements Serializable
{

    @JsonProperty("slides")
    private List<Slide> slides = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4672129622453911805L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Slides() {
    }

    /**
     * 
     * @param slides
     */
    public Slides(List<Slide> slides) {
        super();
        this.slides = slides;
    }

    @JsonProperty("slides")
    public List<Slide> getSlides() {
        return slides;
    }

    @JsonProperty("slides")
    public void setSlides(List<Slide> slides) {
        this.slides = slides;
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
