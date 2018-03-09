package org.launchcode.g7.bookcollections.dummy;

import org.launchcode.g7.bookcollections.models.Book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DummyReadWrite {
    public static List<Book> books = new ArrayList<Book>();

    public static void addItem(Book book){
        books.add(book);
    }

    public static void createList(){
        for(int i=0; i<12; i++){
            Book book = new Book("1234567890" + i, "new book" + i);
            addItem(book);
        }
    }

    public static void save(){
        //Book myBook = new Book("8360238793", "The repeated");

        // Serialization code
        try {
            FileOutputStream fileOut = new FileOutputStream("userDetails.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(books);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void read(){
        List<Book> deserializedBooks;
        try {
            FileInputStream fileIn = new FileInputStream("userDetails.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedBooks = (List<Book>) in.readObject();
            in.close();
            fileIn.close();

            // verify the object state
            verify(deserializedBooks);


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    private static void verify(List<Book> bookz) {
        for(int i=0; i<12; i++){
            System.out.println(bookz.get(i).getTitle());
            System.out.println(bookz.get(i).getIsbn());
        }
    }


    public static void main(String[] args) {
        createList();
        save();
        read();



















//        // Create new UserDetails object
//        Book myBook = new Book("8360238793", "The repeated");
//
//        // Serialization code
//        try {
//            FileOutputStream fileOut = new FileOutputStream("userDetails.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(myBook);
//            out.close();
//            fileOut.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//
//        // De-serialization code
//        //@SuppressWarnings("unused")
//        Book deserializedBook;
//        try {
//            FileInputStream fileIn = new FileInputStream("userDetails.ser");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            deserializedBook = (Book) in.readObject();
//            in.close();
//            fileIn.close();
//
//            // verify the object state
//            System.out.println(deserializedBook.getIsbn());
//            System.out.println(deserializedBook.getTitle());
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (ClassNotFoundException cnfe) {
//            cnfe.printStackTrace();
//        }
    }



}
