package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

	@Autowired
	private BidListService bidListService;

	@Test
	public void bidCrudTest_shouldProcessAllCrudFeatures() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListService.save(bid);
		Assert.assertNotNull(bid.getId());
		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListService.save(bid);
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidList> listResult = bidListService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getId();
		bidListService.delete(id);
		bid = bidListService.findById(id);
		Assert.assertNull(bid);
	}

	@Test
	public void bidConstrainTest_shouldThrowConstrainViolationException() {
		BidList bid1 = new BidList("test", "", 10d);
		BidList bid2 = new BidList("", "test", 10d);
		BidList bid3 = new BidList("test", "test", 10.001d);
		BidList bid4 = new BidList("test", "test", null);

		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> bidListService.save(bid1));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> bidListService.save(bid2));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> bidListService.save(bid3));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> bidListService.save(bid4));

	}


	@Test
	public void bidAllAttrTest_shouldSaveAllAttrCorrectly() {
		// Prepare
		String account = "Account test";
		String type = "Type test";
		Double bidQuantity = 10d;
		Double askQuantity = 11d;
		Double abid = 12d;
		Double ask = 13d;
		String benchmark = "Bench test";
		Date bidDate = new Date(2000,5,30);
		String commentary = "Comm test";
		String security = "Secu test";
		String status = "test_st";
		String trader = "Trader test";
		String book = "Book test";
		String creationName = "Test";
		Date creationDate = new Date(2000,5,30);
		String revisionName = "Test";
		Date revisionDate = new Date(2000,5,30);
		String dealName = "Deal test";
		String dealType = "Deal type";
		String sourceListId = "Source test";
		String side = "Side test";

		BidList bid = new BidList(account, type, bidQuantity);
		bid.setAskQuantity(askQuantity);
		bid.setAsk(ask);
		bid.setBid(abid);
		bid.setBenchmark(benchmark);
		bid.setBidListDate(bidDate);
		bid.setCommentary(commentary);
		bid.setSecurity(security);
		bid.setStatus(status);
		bid.setTrader(trader);
		bid.setBook(book);
		bid.setCreationName(creationName);
		bid.setCreationDate(creationDate);
		bid.setRevisionName(revisionName);
		bid.setRevisionDate(revisionDate);
		bid.setDealName(dealName);
		bid.setDealType(dealType);
		bid.setSourceListId(sourceListId);
		bid.setSide(side);

		// Save
		BidList bidSaved = bidListService.save(bid);

		// Assert
		Assert.assertNotNull(bidSaved.getId());
		Assert.assertEquals("Account", account, bidSaved.getAccount());
		Assert.assertEquals("Type", type, bidSaved.getType());
		Assert.assertEquals("bidQuantity", bidQuantity, bidSaved.getBidQuantity());
		Assert.assertEquals("askQuantity", askQuantity, bidSaved.getAskQuantity());
		Assert.assertEquals("abid", abid, bidSaved.getBid());
		Assert.assertEquals("ask", ask, bidSaved.getAsk());
		Assert.assertEquals("benchmark", benchmark, bidSaved.getBenchmark());
		Assert.assertEquals("bidDate", bidDate, bidSaved.getBidListDate());
		Assert.assertEquals("commentary", commentary, bidSaved.getCommentary());
		Assert.assertEquals("security", security, bidSaved.getSecurity());
		Assert.assertEquals("status", status, bidSaved.getStatus());
		Assert.assertEquals("trader", trader, bidSaved.getTrader());
		Assert.assertEquals("book", book, bidSaved.getBook());
		Assert.assertEquals("creationName", creationName, bidSaved.getCreationName());
		Assert.assertEquals("creationDate", creationDate, bidSaved.getCreationDate());
		Assert.assertEquals("revisionName", revisionName, bidSaved.getRevisionName());
		Assert.assertEquals("revisionDate", revisionDate, bidSaved.getRevisionDate());
		Assert.assertEquals("dealName", dealName, bidSaved.getDealName());
		Assert.assertEquals("dealType", dealType, bidSaved.getDealType());
		Assert.assertEquals("sourceListId", sourceListId, bidSaved.getSourceListId());
		Assert.assertEquals("side", side, bidSaved.getSide());

		// Clean
		bidListService.delete(bidSaved.getId());
	}

}
