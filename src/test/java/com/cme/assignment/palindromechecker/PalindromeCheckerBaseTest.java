package com.cme.assignment.palindromechecker;

import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class PalindromeCheckerBaseTest {

	@Autowired
	protected MockMvc mockMvc;

	public static final String ENDPOINT = "/palindromechecker/api/v1/isPalindrome";

	public static final String RESPONSE_STATUS_PATH = "$.status";

	public static final String RESPONSE_PALINDROME_FLAG_PATH = "$.isPalindrome";

	public static final String RESPONSE_MESSAGE_PATH = "$.message";

	public static final String VALID_PALINDROME = "kayak";

	public static final String NON_PALINDROME = "kayaking";

	public static final String VALID_PALINDROME_REQUEST = "{" +
			"\"text\" : \""+VALID_PALINDROME+"\"," +
			"\"userName\" : \"Pramodh\"" +
			"}";

	protected List<PalindromeCheckerEntity> getMockEntities() {
		PalindromeCheckerEntity entity1 = PalindromeCheckerEntity.builder().text(VALID_PALINDROME).isPalindrome(true).build();
		PalindromeCheckerEntity entity2 =  PalindromeCheckerEntity.builder().text(NON_PALINDROME).isPalindrome(false).build();
		return List.of(entity1, entity2);
	}

}
