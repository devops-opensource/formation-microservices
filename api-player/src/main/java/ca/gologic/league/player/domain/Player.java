package ca.gologic.league.player.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
  @Id
  private Long id;
  private String name;
  private Integer age;
  private Integer goal;
}
