package ru.medweather.documentflow.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    private String title;
    private String about;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Firm author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "other_side_id")
    private Firm otherSide;
    private boolean sign;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "firm_id")
    private Firm firm;

    @Column(name = "sign_other_side")
    private boolean signOtherSide;

    public Document() {
    }

    public Document(String title, Firm author, String about) {
        this.about = about;
        this.author = author;
        this.title = title;
    }

    public String getAuthorName() {
        return author != null ? author.getName() : "<none>";
    }

    public String getOtherName() {
        return otherSide != null ? otherSide.getName() : "<none>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Firm getAuthor() {
        return author;
    }

    public void setAuthor(Firm author) {
        this.author = author;
    }

    public Firm getOtherSide() {
        return otherSide;
    }

    public void setOtherSide(Firm otherSide) {
        this.otherSide = otherSide;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public boolean isSignOtherSide() {
        return signOtherSide;
    }

    public void setSignOtherSide(boolean signOtherSide) {
        this.signOtherSide = signOtherSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id.equals(document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}
