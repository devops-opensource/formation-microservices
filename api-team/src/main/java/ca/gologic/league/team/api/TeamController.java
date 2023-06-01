package ca.gologic.league.team.api;

import ca.gologic.league.team.domain.Team;
import ca.gologic.league.team.service.TeamService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/team")
@Slf4j
public class TeamController {

  @Autowired
  private TeamService teamService;

  @GetMapping
  public List<Team> getPlayers() {
    log.info("Get all team");
    return teamService.getTeams();
  }

}
