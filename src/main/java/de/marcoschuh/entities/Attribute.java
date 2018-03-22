package de.marcoschuh.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Transactional
@Entity
@XmlType(propOrder= {
	"id",
	"key",
	"value",
//	"parent"
})
public class Attribute {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	String key;
	String value;

//	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//	@JoinColumn(name="PARENT_CHILDLVL2_ID")
//	@XmlTransient
//	ChildLvl2 parentChildLvl2;
	
	protected Attribute() {  /* JPA */  }

	public Attribute(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	@XmlElement
	public Long getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(
        		this.getClass().getSimpleName()
        		+"[key='%s', value='%s']", key, value));

		return sb.toString();
	}
}
