
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "images",
    "channelId",
    "channelTitle",
    "start",
    "end",
    "catchupExpiration"
})
public class Slide implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("images")
    private ImagesProgram images;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("channelTitle")
    private String channelTitle;
    @JsonProperty("start")
    private String start;
    @JsonProperty("end")
    private String end;
    @JsonProperty("catchupExpiration")
    private String catchupExpiration;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7056052208696403085L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Slide() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param start
     * @param channelId
     * @param channelTitle
     * @param images
     * @param catchupExpiration
     * @param end
     */
    public Slide(String id, String title, ImagesProgram images, String channelId, String channelTitle, String start, String end, String catchupExpiration) {
        super();
        this.id = id;
        this.title = title;
        this.images = images;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.start = start;
        this.end = end;
        this.catchupExpiration = catchupExpiration;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("images")
    public ImagesProgram getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(ImagesProgram images) {
        this.images = images;
    }

    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("channelTitle")
    public String getChannelTitle() {
        return channelTitle;
    }

    @JsonProperty("channelTitle")
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("end")
    public String getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(String end) {
        this.end = end;
    }

    @JsonProperty("catchupExpiration")
    public String getCatchupExpiration() {
        return catchupExpiration;
    }

    @JsonProperty("catchupExpiration")
    public void setCatchupExpiration(String catchupExpiration) {
        this.catchupExpiration = catchupExpiration;
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
