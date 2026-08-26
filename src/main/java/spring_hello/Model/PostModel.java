package spring_hello.Model;


import com.fasterxml.jackson.annotation.JsonProperty;


public record PostModel(@JsonProperty("userId") int userId, @JsonProperty("id") int id, @JsonProperty("title") String title,
                        @JsonProperty("body") String body) {
}
