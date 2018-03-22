package de.marcoschuh.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@Transactional
@Entity
@XmlType
public class ChildLvl2 {
	
	@Id
	@Column(name="C_LVL2_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
	
	String name;
	
//	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="parentChildLvl2")
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="C_LVL2_ID")
	List<Attribute> attributes;
    
    public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name="attributes")
	@XmlElement(name="attribute")
	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
//		for (Attribute attribute : attributes) {
//			attribute.setParent(this);
//		}
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(
        		this.getClass().getSimpleName()
        		+"[id='%d', name='%s', attributes=", id, name));
		for (Attribute attribute : attributes) {
			sb.append(attribute);
			sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
