package com.didier.carmanagementsystem.model;


import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long CategoryID;

    @Column(name = "category_name")
    private String CategoryName;

    @Column(name = "category_description")
    private String CategoryDescription;

    private String status;

    public Category() {
        super();
    }

    public Category(String CategoryName, String CategoryDescription, String status) {
        this.CategoryName = CategoryName;
        this.CategoryDescription = CategoryDescription;
        this.status = status;
    }


    public Long getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Long categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
