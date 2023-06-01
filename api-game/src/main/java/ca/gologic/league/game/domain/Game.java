package ca.gologic.league.game.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
  private Long id;
  private LocalDate localDate;
  private Team local;
  private Team visitor;
  private Integer pointLocal;
  private Integer pointVisitor;
}
