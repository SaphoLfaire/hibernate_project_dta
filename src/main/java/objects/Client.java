package objects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Client {

	@Column
	@NotBlank
	private String lastName;

	@Column
	@NotBlank
	private String firstName;

	@Column
	private Gender gender;

	/*
	 * Un client a un seul livre favori
	 */
	@ManyToOne
	private Book bookFav;

	@Id
	@GeneratedValue
	private long id;
	
	/*
	 * Un client achete des livres.
	 * Un client peut avoir plusieurs livres.
	 * Relation avec Book.
	 */
	@ManyToMany
	private List<Book> booksList = new ArrayList<>();

	public Client() {

	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public Book getBookFav() {
		return this.bookFav;
	}

	public long getId() {
		return this.id;
	}
	
	public List<Book> getBookList(){
		return this.booksList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setBookFav(Book book) {
		this.bookFav = book;
	}
	
	public void setBookList(Book book) {
		booksList.add(book);
	}

	@Override
	public String toString() {
		return "Client [lastName=" + lastName + ", firstName=" + firstName + ", gender=" + gender + "]";
	}

}
