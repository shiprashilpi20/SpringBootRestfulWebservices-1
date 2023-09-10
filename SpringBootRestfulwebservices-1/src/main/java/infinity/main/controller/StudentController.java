package infinity.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import infinity.main.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {

	// http://localhost:8080/student
	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1,"Shipra","Shilpi");
		//return new ResponseEntity<>(student, HttpStatus.OK);
	//	return ResponseEntity.ok(student);
		// pass header in response entity
		return ResponseEntity.ok().header("custom-header", "Infinity").body(student);
	}
	
	// http://localhost:8080/students
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Shilpi", "Singh"));
		students.add(new Student(2, "Anant", "Kumar"));
		students.add(new Student(3, "Rani", "Devi"));
		students.add(new Student(4, "Minku", "Rai"));
		return ResponseEntity.ok(students);
	}
	
	
	/* Spring-Boot REST API with Path Variable 
	 * {id} is called uri template variable
	*/
	// http://localhost:8080/students/1
	@GetMapping("{id}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable int id) {
		 Student student = new Student(id,"Shipra","Shilpi");
		 return ResponseEntity.ok(student);
		//return new Student(id,"Sanu","Shubham");
	}
	
	/*Handling multiple path variable in a request 
	 http://localhost:8080/students/Shipra/Shilpi */
	@GetMapping("{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable int id, 
		@PathVariable("first-name")	String firstName,
		@PathVariable("last-name") String lastName) {
		Student student= new Student(id,firstName,lastName);
		return ResponseEntity.ok(student);
		//return new Student(id,"Sanu","Shubham");
	}
	
	/* REST API with RequestParam i.e @RequestParam
	 * http://localhost:8080/students/query?id=1
	  */
	@GetMapping("query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam int id) {
	Student student= new Student(id,"Sanu","Shubham");
	return ResponseEntity.ok(student);
	}
	
	/* REST API with with multiple query parameters RequestParam i.e @RequestParam
	 * http://localhost:8080/students/query?id=1&firstName=Shubham&lastName=Sanu
	  */
	@GetMapping("querys")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
			@RequestParam String firstName,
			@RequestParam String lastName) {
	Student student = new Student(id,firstName,lastName);
	return ResponseEntity.ok(student);
	}
	
	// Spring boot REST API that handles HTTP post request
    // @PostMapping and @RequestBody -- create new resource
	
	@PostMapping("create")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		// to check whether our REST API will get student object from http request or
		// not
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	
	// REST API that handles HTTP Put Request -- updating existing resource
	@PutMapping("{id}/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,
			@PathVariable("id") int studentId) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		student.setId(studentId);
		return ResponseEntity.ok(student);	
	}
	
	
	// REST API that handles HTTP delete request. -- delete existing resouce
	@DeleteMapping("{id}/delete")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		System.out.println(id);
		return ResponseEntity.ok("Student deleted successfully.");

	}
	
	
	
}
