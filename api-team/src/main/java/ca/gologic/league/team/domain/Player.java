package ca.gologic.league.team.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
  private Long id;
  private String name;
  private Integer age;
  private Integer goal;
}
