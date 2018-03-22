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
import javax.xml.bind.annotation.XmlType;

@Transactional
@Entity
@XmlType
public class ChildLvl1 {
	
	@Id
//	@Column(name="C_LVL1_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
	
	String name;
	
//	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="parentChildLvl1")
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="C_LVL1_ID", referencedColumnName="ID")
	List<ChildLvl2> lvl2children;
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name="lvl2_children")
	@XmlElement(name="lvl2_child")
	public List<ChildLvl2> getLvl2children() {
		return lvl2children;
	}

	public void setLvl2children(List<ChildLvl2> lvl2children) {
		this.lvl2children = lvl2children;
//		for (ChildLvl2 childLvl2 : lvl2children) {
//			childLvl2.setParentChildLvl1(this);
//		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(
        		this.getClass().getSimpleName()
        		+"[id='%d', name='%s', lvl2children=", id, name));
		for (ChildLvl2 childLvl2 : lvl2children) {
			sb.append(childLvl2);
			sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
