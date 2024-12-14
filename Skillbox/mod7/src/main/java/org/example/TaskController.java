package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.HashSet;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Flux<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .flatMap(task -> Mono.zip(
                        userRepository.findById(task.getAuthorId()),
                        userRepository.findById(task.getAssigneeId()),
                        Flux.fromIterable(task.getObserverIds())
                                .flatMap(userRepository::findById)
                                .collectList()
                ).map(tuple -> {
                    task.setAuthor(tuple.getT1());
                    task.setAssignee(tuple.getT2());
                    // Преобразование List<User> в Set<User>
                    task.setObservers(new HashSet<>(tuple.getT3()));
                    return TaskMapper.INSTANCE.taskToTaskDTO(task);
                }));
    }

    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable String id) {
        return taskRepository.findById(id)
                .flatMap(task -> Mono.zip(
                        userRepository.findById(task.getAuthorId()),
                        userRepository.findById(task.getAssigneeId()),
                        Flux.fromIterable(task.getObserverIds())
                                .flatMap(userRepository::findById)
                                .collectList()
                ).map(tuple -> {
                    task.setAuthor(tuple.getT1());
                    task.setAssignee(tuple.getT2());
                    // Преобразование List<User> в Set<User>
                    task.setObservers(new HashSet<>(tuple.getT3()));
                    return TaskMapper.INSTANCE.taskToTaskDTO(task);
                }));
    }

    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.INSTANCE.taskDTOToTask(taskDTO);
        return taskRepository.save(task)
                .map(savedTask -> TaskMapper.INSTANCE.taskToTaskDTO(savedTask));
    }

    @PutMapping("/{id}")
    public Mono<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    existingTask.setName(taskDTO.getName());
                    existingTask.setDescription(taskDTO.getDescription());
                    existingTask.setStatus(taskDTO.getStatus());
                    return taskRepository.save(existingTask);
                })
                .map(updatedTask -> TaskMapper.INSTANCE.taskToTaskDTO(updatedTask));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable String id) {
        return taskRepository.deleteById(id);
    }
}
