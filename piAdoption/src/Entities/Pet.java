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
    private String name_pet;
    private String breed;
    private int age;
    private String sex;
    private String color;
    private String governate;
    private String city;
    private int zip;
    private String description;
    private String pet_image;
    private int user_id;
    private double lat;
    private double lng;
    private String typePet;
    private String type;


    public Pet() {
    }

    public Pet(int id_pet, String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, int user_id, double lat, double lng, String typePet, String type) {
        this.id_pet = id_pet;
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
        this.user_id = user_id;
        this.lat = lat;
        this.lng = lng;
        this.typePet = typePet;
        this.type = type;
    }

    public Pet(String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, int user_id, double lat, double lng, String typePet, String type) {
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
        this.user_id = user_id;
        this.lat = lat;
        this.lng = lng;
        this.typePet = typePet;
        this.type = type;
    }

    public Pet(String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, double lat, double lng, String typePet, String type) {
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
        this.lat = lat;
        this.lng = lng;
        this.typePet = typePet;
        this.type = type;
    }
    
    

    public Pet(int id_pet, String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, int user_id, double lat, double lng) {
        this.id_pet = id_pet;
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
        this.user_id = user_id;
        this.lat = lat;
        this.lng = lng;
    }
    
    public Pet(int id_pet, String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, double lat, double lng) {
        this.id_pet = id_pet;
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
//        this.user_id = user_id;
        this.lat = lat;
        this.lng = lng;
    }

    public Pet(String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, int user_id, double lat, double lng) {
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
        this.user_id = user_id;
        this.lat = lat;
        this.lng = lng;
    }

    

    public Pet(int id_pet, String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image, int user_id) {
        this.id_pet = id_pet;
        this.name_pet = name_pet;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.governate = governate;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.pet_image = pet_image;
        this.user_id = user_id;
    }
    
    

    public Pet(int id_pet, String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image) {
        this.id_pet = id_pet;
        this.name_pet = name_pet;
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
    
    
    
    public Pet(String name_pet, String breed, int age, String sex, String color, String governate, String city, int zip, String description, String pet_image) {
        //this.id_pet = id_pet;
        this.name_pet = name_pet;
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

    public String getName_pet() {
        return name_pet;
    }

    public void setName_pet(String name_pet) {
        this.name_pet = name_pet;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTypePet() {
        return typePet;
    }

    public void setTypePet(String typePet) {
        this.typePet = typePet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id_pet;
        hash = 73 * hash + Objects.hashCode(this.name_pet);
        hash = 73 * hash + Objects.hashCode(this.breed);
        hash = 73 * hash + this.age;
        hash = 73 * hash + Objects.hashCode(this.sex);
        hash = 73 * hash + Objects.hashCode(this.color);
        hash = 73 * hash + Objects.hashCode(this.governate);
        hash = 73 * hash + Objects.hashCode(this.city);
        hash = 73 * hash + this.zip;
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.pet_image);
        hash = 73 * hash + this.user_id;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.lng) ^ (Double.doubleToLongBits(this.lng) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.typePet);
        hash = 73 * hash + Objects.hashCode(this.type);
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
        if (this.age != other.age) {
            return false;
        }
        if (this.zip != other.zip) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lng) != Double.doubleToLongBits(other.lng)) {
            return false;
        }
        if (!Objects.equals(this.name_pet, other.name_pet)) {
            return false;
        }
        if (!Objects.equals(this.breed, other.breed)) {
            return false;
        }
        if (!Objects.equals(this.sex, other.sex)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.governate, other.governate)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.pet_image, other.pet_image)) {
            return false;
        }
        if (!Objects.equals(this.typePet, other.typePet)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pet{" + "id_pet=" + id_pet + ", name_pet=" + name_pet + ", breed=" + breed + ", age=" + age + ", sex=" + sex + ", color=" + color + ", governate=" + governate + ", city=" + city + ", zip=" + zip + ", description=" + description + ", pet_image=" + pet_image + ", user_id=" + user_id + ", lat=" + lat + ", lng=" + lng + ", typePet=" + typePet + ", type=" + type + '}';
    }
  
}
