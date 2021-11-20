package br.com.matheus.bertholucci.games.price.tracking.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "game")
public class Game {

    @Id
    @JsonAlias("id")
    private String id;

    @JsonAlias("name")
    @JsonProperty(required = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    @JsonAlias("min_price")
    @JsonProperty(required = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String min_price;

    @JsonAlias("discount_percentage")
    @JsonProperty(required = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String percentage;

    @JsonAlias("date")
    @JsonProperty(required = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LocalDateTime date;

    @JsonAlias("platform")
    @JsonProperty(required = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String platform;

}
