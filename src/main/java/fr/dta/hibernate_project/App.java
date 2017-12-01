package fr.dta.hibernate_project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.BookDAO;
import dao.ClientDAO;
import dao.DatabaseHelper;
import objects.Book;
import objects.Client;
import objects.Gender;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Book book1 = new Book();
		book1.setTitle("What's New in Java 8");
		book1.setAuthor("Adam L. Davis");
		Book book2 = (new Book());
		book2.setAuthor("Robert Cecil Martin");
		book2.setTitle("UML For Java Programmers");
		Book book3 = (new Book());
		book3.setAuthor("Brad Miller");
		book3.setTitle("Welcome to Java for Python Programmers");
		Book book4 = (new Book());
		book4.setAuthor(null);
		book4.setTitle("The Java EE7 Tutorial");

		BookDAO.createBook(book1);
		BookDAO.createBook(book2);
		BookDAO.createBook(book3);
		BookDAO.createBook(book4);

		Client client1 = new Client();
		Client client2 = new Client();
		Client client3 = new Client();
		client1.setFirstName("AUPETIT");
		client1.setLastName("Sapho");
		client1.setGender(Gender.F);
		client2.setFirstName("LEPROPRE");
		client2.setLastName("Florian");
		client2.setGender(Gender.M);
		client3.setFirstName("LAPROPRE");
		client3.setLastName("Anais");
		client3.setGender(Gender.F);

		ClientDAO.createClient(client1);
		ClientDAO.createClient(client2);
		ClientDAO.createClient(client3);

		ClientDAO.buy(client1, book1);
		ClientDAO.buy(client3, book1);
		ClientDAO.buy(client2, book1);
		ClientDAO.buy(client3, book2);

		ClientDAO.setBookFav(client2, book4);
		System.out.println(BookDAO.bookBoughtByClients(book2));
		ClientDAO.buy(client3, book4);
		ClientDAO.buy(client3, book3);
		System.out.println(ClientDAO.booksBuyByClient(client3));

		/*
		 * BONNES PRATIQUES : Il est préférable que les classes-tables de la bdd aient
		 * un constructeur vide et que les variables soient remplies avec des setters.
		 */

		/*
		 * QUELS LIVRES ONT ETE ACHETE :
		 */

		EntityManager em = DatabaseHelper.createEntityManager();
		List<Book> booksBougth = new ArrayList<>();
		try {
			TypedQuery<Book> tq = em
					.createQuery("select distinct book " + "from Client c " + "inner join c.booksList book ", Book.class);

			booksBougth = tq.getResultList();
			em.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);

		}
		System.out.println(booksBougth);

	}
}
