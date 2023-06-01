package ca.gologic.league.player.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
  private Long id;
  private String name;
  private Integer age;
  private Integer goal;
}
