package com.example.ca3.Entity;

import androidx.annotation.Nullable;

public class Animal {
    private String name;
    private String age;
    private String breed;
    private String lifestyle;




    public Animal() {
        this.name="";
        this.breed="";
        this.age="";
        this.lifestyle="";
    }

    public Animal(String name, String breed, String age, String lifestyle) {
        this.name=name;
        this.breed=breed;
        this.age=age;
        this.lifestyle=lifestyle;
    }

        public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

        public String getAge() {
        return age;
    }

        public void setAge(String age) {
        this.age = age;
    }

        public String getBreed() {
        return breed;
    }

        public void setBreed(String breed) {
        this.breed = breed;
    }

        public String getlifestyle() {
        return lifestyle;
    }

        public void setlifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }


        @Override
        public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

}
