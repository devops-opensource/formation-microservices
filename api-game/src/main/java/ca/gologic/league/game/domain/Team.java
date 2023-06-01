package ca.gologic.league.game.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
  private Long id;
  private String name;
  private List<Player> players;

  public List<Player> getPlayers() {
    if (players == null) {
      players = new ArrayList<>();

    }
    return players;
  }
}
