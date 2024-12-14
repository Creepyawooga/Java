package org.example;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    // Конвертация Task -> TaskDTO
    @Mapping(source = "author", target = "author")
    @Mapping(source = "assignee", target = "assignee")
    @Mapping(source = "observers", target = "observers")
    TaskDTO taskToTaskDTO(Task task);

    // Конвертация TaskDTO -> Task
    @Mapping(source = "author", target = "author")
    @Mapping(source = "assignee", target = "assignee")
    @Mapping(source = "observers", target = "observers")
    Task taskDTOToTask(TaskDTO taskDTO);

    // Конвертация User -> UserDTO
    UserDTO userToUserDTO(User user);

    // Конвертация UserDTO -> User
    User userDTOToUser(UserDTO userDTO);
}
