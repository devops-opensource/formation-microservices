package ca.gologic.league.team.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
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
