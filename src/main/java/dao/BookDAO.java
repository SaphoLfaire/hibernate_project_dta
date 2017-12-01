package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import objects.Book;
import objects.Client;

public class BookDAO {

	public static Boolean createBook(Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		try {

			DatabaseHelper.beginTx(em);
			em.persist(book);
			DatabaseHelper.commitTxAndClose(em);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);
			return false;

		}

	}

	public static List<Client> bookBoughtByClients(Book book) { // quels clients ont acheté un certain livre
		List<Client> clientsList = new ArrayList<>();
		EntityManager em = DatabaseHelper.createEntityManager();
		try {
			TypedQuery<Client> tq = em.createQuery(
					"select distinct client " + "from Book u " + "inner join u.clientsList client " + " where u.id =:id ", Client.class);
			tq.setParameter("id", book.getId());

			clientsList = tq.getResultList();
			em.close();
			return clientsList;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			DatabaseHelper.rollbackTxAndClose(em);
			return clientsList;

		}

	}
}
