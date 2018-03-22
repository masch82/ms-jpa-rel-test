package de.marcoschuh;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.marcoschuh.entities.Attribute;
import de.marcoschuh.entities.ChildLvl1;
import de.marcoschuh.entities.ChildLvl2;
import de.marcoschuh.entities.Parent;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityCountTest {
	
	private static boolean initialized = false;
	
	@Autowired
	ParentRepository repository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void postConstruct() {
		if (!initialized) {
			logger.debug("Setting up repository");
			
			Parent parent = new Parent();
			parent.setName("TEST_PARENT");
			
			List<ChildLvl1> lvl1_children = new ArrayList<>();
			ChildLvl1 lvl1_child = new ChildLvl1();
			lvl1_child.setName("TEST_CHILD1");
			
			List<ChildLvl2> lvl2_children = new ArrayList<>();
			ChildLvl2 lvl2_child = new ChildLvl2();
			lvl2_child.setName("TEST_CHILD2");
			
			List<Attribute> attributes = new ArrayList<>();
			attributes.add(new Attribute("KEY1", "VALUE1"));
			attributes.add(new Attribute("KEY2", "VALUE2"));
//			attributes.add(new Attribute("KEY3", "VALUE3"));
			
			lvl1_children.add(lvl1_child);
			parent.setChildren1(lvl1_children);
			lvl2_children.add(lvl2_child);
			lvl1_child.setLvl2children(lvl2_children);
			lvl2_child.setAttributes(attributes);
			
			repository.save(parent);
			
			initialized = true;
		}
	}

	@Test
	public void testFindAll() {
		Parent parent = repository.findAll().get(0);
		logger.debug("Parent retrieved : {}", parent);
		assertEquals(1, parent.getChildren1().size());
		assertEquals(2, parent.getChildren1().get(0).getLvl2children().get(0).getAttributes().size());
	}
	
	@Test
	@Transactional
	public void testGetById() {
		Parent parent = repository.getOne(1L);
		logger.debug("Parent retrieved : {}", parent);
		assertEquals(1, parent.getChildren1().size());
		assertEquals(2, parent.getChildren1().get(0).getLvl2children().get(0).getAttributes().size());
	}
	
}
