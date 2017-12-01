package objects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {

	@Column
	private String title;
	
	@Column
	private String author;
	
	@Id
	@GeneratedValue
	private long id;
	
	/*
	 * Un livre peut être acheté par plusieurs clients.
	 * Relation avec Client, porte la relation pour 
	 * pouvoir récupérer tous les objets à chaque SELECT 
	 * de client. 
	 */
	@ManyToMany (mappedBy = "booksList")
	private List<Client> clientsList = new ArrayList<>();

	public Book() {

	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public long getId() {
		return id;
	}
	
	public List<Client> getClientsList(){
		return clientsList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setClientsList(Client client) {
		this.clientsList.add(client);
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + "]";
	}

}