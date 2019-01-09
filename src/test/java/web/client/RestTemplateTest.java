package web.client;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 스프링 RestTemplate API 테스트 유닛
 * 
 * @since 2019-01-07
 * @author fixalot
 */
public class RestTemplateTest {
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);

	private static final String alphabet = "https://google.com";
	private static final String myLocalServer = "http://localhost:8080/http-test/you-should-be-here.data";
	
	@Test
	public void simpleGetRequest() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(alphabet, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	/**
	 * 로컬 서버 띄워야 함.
	 * 
	 * @author fixalot
	 */
//	@Test
	public void postRequest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("email", "first.last@example.com");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(myLocalServer, request , String.class );
		logger.debug(response.toString());
	}
}
