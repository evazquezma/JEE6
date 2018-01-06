package es.sisifo.pruebas.boot.hibernate.api.model;

import java.io.InputStream;

public class Store {
	private Long id;
	private String name;
	private InputStream contenStream;
	private int contentSize;

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

	public InputStream getContenStream() {
		return contenStream;
	}

	public void setContenStream(final InputStream contenStream) {
		this.contenStream = contenStream;
	}

	public int getContentSize() {
		return contentSize;
	}

	public void setContentSize(final int contentSize) {
		this.contentSize = contentSize;
	}
}
