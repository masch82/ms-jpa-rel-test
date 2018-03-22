package de.marcoschuh.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;

@Transactional
@Entity
@XmlRootElement
public class Parent {
	
	@Id
//	@Column(name="PARENT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
	
	String name;
	
//	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="aparent")
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="PARENT_ID", referencedColumnName="ID")
	List<ChildLvl1> lvl1_children;
	
	public Parent() { }

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name="lvl1_children")
	@XmlElement(name="lvl1_child")
	public List<ChildLvl1> getChildren1() {
		return lvl1_children;
	}

	public void setChildren1(List<ChildLvl1> children1) {
		this.lvl1_children = children1;
//		for (ChildLvl1 childLvl1 : children1) {
//			if (childLvl1.getAparent() != this) {
//				childLvl1.setAparent(this);
//			}
//		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(
        		this.getClass().getSimpleName()
        		+"[id='%d', name='%s', lvl1_children=", id, name));
		for (ChildLvl1 childLvl1 : lvl1_children) {
			sb.append(childLvl1);
			sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	

}
