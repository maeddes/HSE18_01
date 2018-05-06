package de.hsesslingen.HalloData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HalloDataApplication {

	@Autowired
	ToDoItemRepository repository;

	@GetMapping("/todos")
	String todos() {

		return repository.findAll().toString();

	}

	@PostMapping("/todos/{todo}")
	String add(@PathVariable String todo) {

		ToDoItem item = new ToDoItem(todo);
		repository.save(item);
		return todo +" created";
	}

	public static void main(String[] args) {
		SpringApplication.run(HalloDataApplication.class, args);
	}
}

interface ToDoItemRepository extends CrudRepository<ToDoItem, Long>{

}

@Entity
class ToDoItem{

	@Id
	@GeneratedValue
	Long id;

	String toDo;
	boolean isDone;

	public ToDoItem(){}

	public ToDoItem(String toDo){

		this.toDo = toDo;
		this.isDone = false;

	}

	public String getToDo(){

		return toDo;
	}

	public boolean isDone(){

		return isDone;
	}

	public void setDone(boolean done){

		this.isDone = done;
	}

	public String toString(){

		return id+" ToDo: "+toDo+" isDone: "+isDone;
	}

}