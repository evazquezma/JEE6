package es.sisifo.cxf.wss4j.provider.mtom;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "name",
	    "fileData"
	})
@XmlRootElement(name = "helloMTOMData")
public class HelloMTOMData {
	@XmlElement(required=true)
	private String name;

	@XmlMimeType("application/octet-stream")
	@XmlElement(required=true)
	private DataHandler fileData;


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public DataHandler getFileData() {
		return fileData;
	}

	public void setFileData(final DataHandler fileData) {
		this.fileData = fileData;
	}
}
