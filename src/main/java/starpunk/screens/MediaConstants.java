package starpunk.screens;

import starpunk.services.music.MusicResource;
import starpunk.services.sound.SoundResource;

public final class MediaConstants
{
  public static final SoundResource CLICK_SOUND  = new SoundResource( "sounds/click.wav" );
  public static final MusicResource MENU_MUSIC = new MusicResource( "music/menu.ogg" );
  public static final MusicResource LEVEL_MUSIC = new MusicResource( "music/level.ogg" );

  private MediaConstants()
  {
  }
}
