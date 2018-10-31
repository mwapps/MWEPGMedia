
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
    "id",
    "title",
    "start",
    "end",
    "lastTimePosition",
    "images",
    "channelId",
    "channelTitle",
    "channelImages",
    "meta",
    "series",
    "description"
})
public class Program implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("start")
    private String start;
    @JsonProperty("end")
    private String end;
    @JsonProperty("lastTimePosition")
    private String lastTimePosition;
    @JsonProperty("images")
    private ImagesProgram images;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("channelTitle")
    private String channelTitle;
    @JsonProperty("channelImages")
    private ChannelImages channelImages;
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("series")
    private List<Series> series = null;
    @JsonProperty("description")
    private String description;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4019328362979698225L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Program() {
    }

    /**
     * 
     * @param id
     * @param series
     * @param title
     * @param channelId
     * @param start
     * @param description
     * @param channelImages
     * @param channelTitle
     * @param images
     * @param lastTimePosition
     * @param meta
     * @param end
     */
    public Program(String id, String title, String start, String end, String lastTimePosition, ImagesProgram images, String channelId, String channelTitle, ChannelImages channelImages, Meta meta, List<Series> series, String description) {
        super();
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.lastTimePosition = lastTimePosition;
        this.images = images;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.channelImages = channelImages;
        this.meta = meta;
        this.series = series;
        this.description = description;
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

    @JsonProperty("lastTimePosition")
    public String getLastTimePosition() {
        return lastTimePosition;
    }

    @JsonProperty("lastTimePosition")
    public void setLastTimePosition(String lastTimePosition) {
        this.lastTimePosition = lastTimePosition;
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

    @JsonProperty("channelImages")
    public ChannelImages getChannelImages() {
        return channelImages;
    }

    @JsonProperty("channelImages")
    public void setChannelImages(ChannelImages channelImages) {
        this.channelImages = channelImages;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("series")
    public List<Series> getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(List<Series> series) {
        this.series = series;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
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
