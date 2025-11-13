package tasktracker.task;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.time.LocalDateTime;
import java.util.Map;

public record TaskModel (String id, String description, String status, LocalDateTime createAt, LocalDateTime updateAt) {


}
