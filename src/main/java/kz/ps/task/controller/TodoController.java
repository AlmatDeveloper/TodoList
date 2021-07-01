package kz.ps.task.controller;

import kz.ps.task.entity.Todo;
import kz.ps.task.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/list")
    public List<Todo> getAllTodoes() {
        return todoRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public Todo getTodoById(@PathVariable(value = "id") Long todoId) {
        Todo todo = todoRepository.getById(todoId);
        return todo;
    }

    @PostMapping("/list/create")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/list/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "id") Long todoId, @RequestBody Todo todoDetails) {
        Todo todo = todoRepository.getById(todoId);

        todo.setDate(todoDetails.getDate());
        todo.setName(todoDetails.getName());
        todo.setPriority(todoDetails.getPriority());
        todo.setDescription(todoDetails.getDescription());
        todo.setDone(todoDetails.getDone());

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/list/{id}")
    public void deleteTodo(@PathVariable(value = "id") Long todoId) {
        Todo todo = todoRepository.getById(todoId);
        todoRepository.delete(todo);
    }

}
