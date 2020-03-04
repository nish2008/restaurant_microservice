package com.solutions.restaurantservice.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {
    public static void main(String args[]) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = new Student(1, "Mark");
        Book book1 = new Book(1,"Learn HTML", Arrays.asList(student));
        Book book2 = new Book(2,"Learn JAVA", Arrays.asList(student));

        student.addBook(book1);
        student.addBook(book2);

        Restaurant restaurant1 = new Restaurant("Eat.Fit");
        Menu menu = new Menu();
        Item item1 = new Item("Chicken Biriyani", "Basmati Rice, Egg, Big Big Lollipops", Arrays.asList(restaurant1));
        Item item2 = new Item("Hakka Noddle", "Chicken Chunks, Smoody Noddles",Arrays.asList(restaurant1));
        Item item3 = new Item("Chicken Wrap", "Chicken , Mayonnaise, Fried Onion, Fried Tomatoes",Arrays.asList(restaurant1));
        menu.getItems().add(item1);
        menu.getItems().add(item2);
        menu.getItems().add(item3);
        restaurant1.setMenu(menu);
        Price price1 = new Price(100, item1);
        Price price2 = new Price(200, item2);
        Price price3 = new Price(300, item3);

        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(restaurant1);
        System.out.println(jsonString);
    }
}

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "name")
class Student {
    public int rollNo;
    public String name;

    @JsonManagedReference
    public List<Book> books;

    Student(int rollNo, String name){
        this.rollNo = rollNo;
        this.name = name;
        this.books = new ArrayList<Book>();
    }
    public void addBook(Book book){
        books.add(book);
    }
}

class Book {
    public int id;
    public String name;

    Book(int id, String name, List<Student> owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    @JsonBackReference
    public List<Student> owner;
}

class Restaurant {


    private String name;

    private Long id;

    private Menu menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}

class Menu {
    private ArrayList<Item> items = new ArrayList<>();

    public Menu() {

    }

    public Menu(ArrayList<Item> items) {
        this.items = items;
    }

    public void setItems(ArrayList<Item> items) {

        this.items = items;
    }

    public ArrayList<Item> getItems() {

        return items;
    }
}

class Item {
    private Long id;

    private String name;

    private String description;

    private int price;

    //@JsonManagedReference()
    @JsonIgnore
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    public Item() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String name, String description, List<Restaurant> restaurants) {
        this.name = name;
        this.description = description;
        this.restaurants = restaurants;
    }
    /*
    public Item(String name, String description, Price price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}

 class Price {
    private Long id;

    private int amount;

    @JsonBackReference
    private Item item;

    public Price() {

    }

    public Price(int amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
