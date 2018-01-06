package es.sisifo.pruebas.boot.hibernate.persistencia.entity;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class StoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Lob
	private Blob fileContent;

	private int fileSize;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Blob getFileContent() {
		return fileContent;
	}

	public void setFileContent(final Blob fileContent) {
		this.fileContent = fileContent;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(final int fileSize) {
		this.fileSize = fileSize;
	}

}
