package com.artemis.managers;

import com.artemis.Manager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import java.util.HashMap;
import java.util.Map;

/**
 * Use this class together with PlayerManager.
 * <p/>
 * You may sometimes want to create teams in your game, so that
 * some players are team mates.
 * <p/>
 * A player can only belong to a single team.
 *
 * @author Arni Arent
 */
public class TeamManager extends Manager
{
  private final Map<String, Bag<String>> playersByTeam;
  private final Map<String, String> teamByPlayer;

  public TeamManager()
  {
    playersByTeam = new HashMap<String, Bag<String>>();
    teamByPlayer = new HashMap<String, String>();
  }

  public String getTeam( final String player )
  {
    return teamByPlayer.get( player );
  }

  public void setTeam( final String player, final String team )
  {
    removeFromTeam( player );

    teamByPlayer.put( player, team );

    Bag<String> players = playersByTeam.get( team );
    if( players == null )
    {
      players = new Bag<String>();
      playersByTeam.put( team, players );
    }
    players.add( player );
  }

  public ImmutableBag<String> getPlayers( final String team )
  {
    return playersByTeam.get( team );
  }

  public void removeFromTeam( final String player )
  {
    final String team = teamByPlayer.remove( player );
    if( team != null )
    {
      final Bag<String> players = playersByTeam.get( team );
      if( players != null )
      {
        players.remove( player );
      }
    }
  }
}
