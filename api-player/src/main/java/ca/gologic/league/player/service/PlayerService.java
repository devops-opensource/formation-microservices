package ca.gologic.league.player.service;

import ca.gologic.league.player.domain.Player;
import ca.gologic.league.player.repository.PlayerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  @Cacheable("players")
  public List<Player> getPlayers() {
    if (playerRepository.count() == 0) {
      playerRepository.saveAll(List.of(new Player(1L, "Sidney Crosby", 32, 0),
          new Player(2L, "Wayne Gretzky", 21, 0),
          new Player(3L, "Alexander Ovechkin", 33, 0),
          new Player(4L, "Connor McDavid", 33, 0)));
    }
    return playerRepository.findAll();
  }

  @CachePut("players")
  public Player updatePlayer(Player player) {
    Player dbPlayer = playerRepository.findById(player.getId()).orElseThrow();
    dbPlayer.setGoal(dbPlayer.getGoal() + player.getGoal());
    playerRepository.save(dbPlayer);
    return dbPlayer;
  }
}
