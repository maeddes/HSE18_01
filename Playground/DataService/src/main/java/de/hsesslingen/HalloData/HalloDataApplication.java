package de.hsesslingen.HalloData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;
import java.util.ArrayList;

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

	// not the best practice here, should be returned in JSON and parsed on client side
	@GetMapping("/todos")
	List<String> todos() {

		System.out.println("In GET /todos: ");
		
		List<ToDoItem> items = repository.findAll();
		List<String> toDos = new ArrayList<String>();
		for(int i = 0; i < items.size(); i++){

			boolean add = toDos.add(items.get(i).getToDo());

		}
        return toDos;

	}

	@PostMapping("/todos/{todo}")
	String add(@PathVariable String todo) {

		ToDoItem item = new ToDoItem(todo);
		repository.save(item);
		return todo +" created";
	}

	@PostMapping("/todos/done/{toDo}")
	String delete(@PathVariable String toDo){

		List<ToDoItem> toDos = repository.findByToDo(toDo);
		System.out.println("Found list: "+toDos);

		//assumption that always the first hit is the one to remove
		if (toDos.size() > 0) repository.delete(toDos.get(0));
		return toDo +" deleted";

	}

	public static void main(String[] args) {

		SpringApplication.run(HalloDataApplication.class, args);

	}
}

interface ToDoItemRepository extends CrudRepository<ToDoItem, Long>{

	List<ToDoItem> findAll();
	List<ToDoItem> findByToDo(String toDo);

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