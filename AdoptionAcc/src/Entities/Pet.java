/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author mac
 */

public class Pet {
    private int id_pet;
    private String breed;
    private int age;
    private String sex;
    private String color;
    private String governate;
    private String city;
    private int zip;
    private String description;
    private String pet_image;

    public Pet() {
    }
    
    
    public Pet(String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image) {
        //this.id_pet = id_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPet_image() {
        return pet_image;
    }

    public void setPet_image(String pet_image) {
        this.pet_image = pet_image;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_pet;
        hash = 97 * hash + Objects.hashCode(this.breed);
        hash = 97 * hash + this.age;
        hash = 97 * hash + Objects.hashCode(this.sex);
        hash = 97 * hash + Objects.hashCode(this.color);
        hash = 97 * hash + Objects.hashCode(this.governate);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + this.zip;
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.pet_image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pet other = (Pet) obj;
        if (this.id_pet != other.id_pet) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pet{" + "id_pet=" + id_pet + ", breed=" + breed + ", age=" + age + ", sex=" + sex + ", color=" + color + ", governate=" + governate + ", city=" + city + ", zip=" + zip + ", description=" + description + ", pet_image=" + pet_image + '}';
    }
   
}
