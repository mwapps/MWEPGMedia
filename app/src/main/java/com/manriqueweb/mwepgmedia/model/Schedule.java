
package com.manriqueweb.mwepgmedia.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.manriqueweb.mwepgmedia.utils.DateTimeDesSerializer;
import com.manriqueweb.mwepgmedia.utils.DateTimeSerializer;

import org.joda.time.DateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "id",
    "start",
    "end"
})
public class Schedule implements Serializable
{

    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private String id;

    @JsonProperty("start")
    @JsonDeserialize(using = DateTimeDesSerializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime start;

    @JsonProperty("end")
    @JsonDeserialize(using = DateTimeDesSerializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime end;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4874371272101990227L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Schedule() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param start
     * @param end
     */
    public Schedule(String title, String id, DateTime start, DateTime end) {
        super();
        this.title = title;
        this.id = id;
        this.start = start;
        this.end = end;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("start")
    public DateTime getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(DateTime start) {
        this.start = start;
    }

    @JsonProperty("end")
    public DateTime getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(DateTime end) {
        this.end = end;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public boolean isCurrent() {
        long now = System.currentTimeMillis();
        return now >= start.getMillis() && now <= end.getMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schedule{");
        sb.append("title='").append(title).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }
}
