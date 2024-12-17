package com.spring.sample.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sample.data.DataServiceFactory;
import com.spring.sample.data.DummyDataService;
import com.spring.sample.data.IDataService;
import com.spring.sample.exception.InvalidInputException;
import com.spring.sample.model.Student;

import org.json.JSONObject;

@CrossOrigin
@RestController
public class BasicController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(BasicController.class);

	@Autowired
	private IDataService dataService;
	
	public void setDataService(IDataService dataService) {
		this.dataService = dataService;		
	}
	
	@GetMapping(value = "/sample/dataservice/{keyId}", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getDataFromService(
			@PathVariable(name = "keyId") String keyId ) {
		Map<String, Object> mapClass = dataService.getData(keyId);
		return new ResponseEntity<>(mapClass, HttpStatus.OK);
	}
	
	@GetMapping(value = "/sample/ctof", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getCelciusToFahrenheit(
			@RequestParam(name = "c", required = true) int celcius) {
		Map<String, Object> map = new HashMap<>();
		int fahrenheit = (int) (1.8 * celcius + 32);
		map.put("celcius", celcius);
		map.put("farenheit", fahrenheit);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping(value = "/sample/data", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getStaticData() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "100");
		map.put("name", "David");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/sample/data2")
	public ResponseEntity<Map<String, Object>> getJSONData() {
		JSONObject jo = new JSONObject();
		//jo.append("id", "200");
		
		jo.put("name", "Chang");
		jo.put("list", "item1");
		jo.append("list", "item2");
		
		return new ResponseEntity<>(jo.toMap(), HttpStatus.OK);
	}
	
	//curl http://localhost:8080/sample/students/1
	@GetMapping("/sample/students/{sid}")
	public ResponseEntity<Map<String, Object>> getStudent(@PathVariable(name = "sid", required = true) int sid) {
	
		JSONObject jo = new JSONObject();
		jo.put("sid", sid);
		jo.put("lastName", "Jackson");
		jo.put("firstName", "Michael");
		
		return new ResponseEntity<>(jo.toMap(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/sample/students/v2/{sid}")
	public ResponseEntity<Student> getStudentV2(@PathVariable(name = "sid", required = true) int sid,
			@RequestParam(name = "year", defaultValue = "9999") String year) {
		
		Student s1 = new Student();
		s1.sid = sid;
		s1.firstName = "Michael";
		s1.lastName = "Jackson";
		s1.year = year;
		//s1.setFirstName("Michael");
		//int d = s1.getId();
		//s1.id = 300;
		
		return new ResponseEntity<>(s1, HttpStatus.OK);
	}
	
	// curl -H 'Content-Type: application/json' \
    //   -d '{ "title":"foo", "body":"bar","lastName": "Jackson"}' \
    //   -X POST \
    //   http://localhost:8080/sample/students
	@PostMapping(value = "/sample/students", headers = "content-type=application/json")
	public ResponseEntity<Map<String, Object>> postNewStudent(@RequestBody String requestJsonData) {
		
		Map<String, Object> map = new HashMap<>();
		JSONObject jo = super.getJsonStringObject(requestJsonData);
		String lastName = jo.has("lastName") ? jo.getString("lastName") : "";
		
		map.put("message", "student created with lastName = " + lastName);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// curl -H 'Content-Type: application/json' \
    //   -d '{ "id":"12", "body":"bar","lastName": "Jackson"}' \
    //   -X PUT \
    //   http://localhost:8080/sample/students
	@PutMapping(value = "/sample/students", headers = "content-type=application/json")
	public ResponseEntity<Map<String, Object>> putNewStudent(@RequestBody String requestJsonData) {
		
		Map<String, Object> map = new HashMap<>();
		JSONObject jo = super.getJsonStringObject(requestJsonData);
		if (!jo.has("id")) {
			throw new InvalidInputException("no id in data");
		}
		int id = jo.getInt("id");
		String lastName = jo.has("lastName") ? jo.getString("lastName") : "";
		
		map.put("message", "update student with id = " + id);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// curl -H 'Content-Type: application/json' \
    //   -d '{ "id":"12", "body":"bar","lastName": "Jackson"}' \
    //   -X DELETE \
    //   http://localhost:8080/sample/students/32
	@DeleteMapping(value = "/sample/students/{sid}")
	public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable(name = "sid", required = true) int sid,
			@RequestBody String requestJsonData)
	{
		Map<String, Object> map = new HashMap<>();
		JSONObject jo = super.getJsonStringObject(requestJsonData);
		
		map.put("message", "delete student with id = " + sid);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping(value = "/sample/permissions")
	public ResponseEntity<Map<String, Object>> getRequestAttribute(@RequestAttribute(name = "permissions") String permissions) {
		Map<String, Object> map = new HashMap<>();
		map.put("permissions", permissions);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
}
