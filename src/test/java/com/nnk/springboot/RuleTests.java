package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameService ruleNameService;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameService.save(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameService.save(rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameService.delete(id);
		RuleName ruleList = ruleNameService.findById(id);
		Assert.assertNull(ruleList);
	}

	@Test
	public void ruleConstraintTest_shouldThrownConstraintViolationException() {
		RuleName rule1 = new RuleName("", "Description", "Json", "Template", "SQL", "SQL Part");
		RuleName rule2 = new RuleName("Rule Name", "", "Json", "Template", "SQL", "SQL Part");
		RuleName rule3 = new RuleName("Rule Name", "Description", "", "Template", "SQL", "SQL Part");
		RuleName rule4 = new RuleName("Rule Name", "Description", "Json", "", "SQL", "SQL Part");
		RuleName rule5 = new RuleName("Rule Name", "Description", "Json", "Template", "", "SQL Part");
		RuleName rule6 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "");

		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ruleNameService.save(rule1));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ruleNameService.save(rule2));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ruleNameService.save(rule3));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ruleNameService.save(rule4));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ruleNameService.save(rule5));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ruleNameService.save(rule6));

	}

	@Test
	public void ruleAttrsSaveTest_shouldSaveCorreclyAllAttrs() {
		String name = "Test";
		String description = "Test";
		String json = "Json Test";
		String template = "Template test";
		String sqlStr = "Sql Str test";
		String sqlPart = "Sql part test";

		RuleName rule = new RuleName();
		rule.setName(name);
		rule.setDescription(description);
		rule.setJson(json);
		rule.setTemplate(template);
		rule.setSqlStr(sqlStr);
		rule.setSqlPart(sqlPart);

		RuleName savedRule = ruleNameService.save(rule);

		Assert.assertNotNull(savedRule.getId());
		Assert.assertEquals("name", name, savedRule.getName());
		Assert.assertEquals("description", description, savedRule.getDescription());
		Assert.assertEquals("json", json, savedRule.getJson());
		Assert.assertEquals("template", template, savedRule.getTemplate());
		Assert.assertEquals("sqlStr", sqlStr, savedRule.getSqlStr());
		Assert.assertEquals("sqlPart", sqlPart, savedRule.getSqlPart());

		ruleNameService.delete(savedRule.getId());

	}
}
