package starpunk.services.preference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import starpunk.StarPunkGame;
import starpunk.services.music.MusicManager;
import starpunk.services.sound.SoundManager;

public final class PreferenceManager
{
  private static final String MUSIC_ENABLED_KEY = "music.enabled";
  private static final String MUSIC_VOLUME_KEY = "music.volume";
  private static final String SOUND_ENABLED_KEY = "sound.enabled";
  private static final String SOUND_VOLUME_KEY = "sound.volume";

  private final StarPunkGame _game;

  public PreferenceManager( final StarPunkGame game )
  {
    _game = game;
  }

  public void loadPreferences()
  {
    log( "Loading the system preferences." );
    final MusicManager musicManager = _game.getMusicManager();
    final SoundManager soundManager = _game.getSoundManager();
    musicManager.setDisabled( !isMusicEnabled() );
    soundManager.setDisabled( !isSoundEnabled() );
    soundManager.setVolume( getSoundVolume() );
    musicManager.setVolume( getMusicVolume() );
  }

  public boolean isSoundEnabled()
  {
    return getPreferences().getBoolean( SOUND_ENABLED_KEY, true );
  }

  public void setSoundEnabled( final boolean soundEnabled )
  {
    getPreferences().putBoolean( SOUND_ENABLED_KEY, soundEnabled );
    getPreferences().flush();
    _game.getSoundManager().setDisabled( !soundEnabled );
  }

  public boolean isMusicEnabled()
  {
    return getPreferences().getBoolean( MUSIC_ENABLED_KEY, true );
  }

  public void setMusicEnabled( final boolean musicEnabled )
  {
    getPreferences().putBoolean( MUSIC_ENABLED_KEY, musicEnabled );
    getPreferences().flush();
    _game.getMusicManager().setDisabled( !musicEnabled );
  }

  public float getSoundVolume()
  {
    return getPreferences().getFloat( SOUND_VOLUME_KEY, 0.5f );
  }

  public void setSoundVolume( final float volume )
  {
    _game.getSoundManager().setVolume( volume );
    getPreferences().putFloat( SOUND_VOLUME_KEY, volume );
    getPreferences().flush();
  }

  public float getMusicVolume()
  {
    return getPreferences().getFloat( MUSIC_VOLUME_KEY, 0.5f );
  }

  public void setMusicVolume( final float volume )
  {
    _game.getMusicManager().setVolume( volume );
    getPreferences().putFloat( MUSIC_VOLUME_KEY, volume );
    getPreferences().flush();
  }

  private Preferences getPreferences()
  {
    return Gdx.app.getPreferences( _game.getGameKey() );
  }

  private void log( final String message )
  {
    Gdx.app.log( getClass().getSimpleName(), message );
  }
}
