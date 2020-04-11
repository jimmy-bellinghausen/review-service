package com.example.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieModel {

    String title;
    String link;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieModel that = (MovieModel) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
