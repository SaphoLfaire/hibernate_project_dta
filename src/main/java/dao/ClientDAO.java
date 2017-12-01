package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import objects.Book;
import objects.Client;

public class ClientDAO {

	public static Boolean createClient(Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		try {

			DatabaseHelper.beginTx(em);
			em.persist(client);
			DatabaseHelper.commitTxAndClose(em);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);
			return false;

		}
	}

	public static Boolean buy(Client client, Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		try {
			DatabaseHelper.beginTx(em);
			client.setBookList(book);
			book.setClientsList(client);
			em.merge(book);
			em.merge(client);
			DatabaseHelper.commitTxAndClose(em);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);
			return false;

		}
	}

	public static Boolean setBookFav(Client client, Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		try {
			DatabaseHelper.beginTx(em);
			client.setBookFav(book);
			em.merge(client);
			DatabaseHelper.commitTxAndClose(em);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);
			return false;

		}
	}

	public static List<Book> booksBuyByClient(Client client) { // livres qui ont été acheté par un certains client
		List<Book> booksList = new ArrayList<>();
		EntityManager em = DatabaseHelper.createEntityManager();
		try {
			TypedQuery<Book> tq = em.createQuery(
					"select distinct book " + "from Client u " + "inner join u.booksList book " + " where u.id =:id ",
					Book.class);
			tq.setParameter("id", client.getId());
			booksList = tq.getResultList();
			em.close();
			return booksList;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);
			return booksList;

		}

	}

}
