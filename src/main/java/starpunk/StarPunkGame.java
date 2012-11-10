package starpunk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import starpunk.screens.SplashScreen;
import starpunk.services.music.MusicManager;
import starpunk.services.preference.PreferenceManager;
import starpunk.services.sound.SoundManager;
import starpunk.services.sprite.SpriteManager;

public final class StarPunkGame
  extends Game
{
  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;

  private final SpriteManager _spriteManager = new SpriteManager();
  private final MusicManager _musicManager = new MusicManager();
  private final SoundManager _soundManager = new SoundManager();
  private final PreferenceManager _preferenceManager = new PreferenceManager( this );

  @Override
  public void create()
  {
    log( "Creating game on " + Gdx.app.getType() );
    _spriteManager.initialize();
    _preferenceManager.loadPreferences();
    setScreen( new SplashScreen( this ) );
  }

  @Override
  public void dispose()
  {
    log( "Disposing game." );
    super.dispose();
    _musicManager.dispose();
    _spriteManager.dispose();
  }

  public boolean isDebugMode()
  {
    return false;
  }

  public String getGameKey()
  {
    return "StarPunk";
  }

  public PreferenceManager getPreferenceManager()
  {
    return _preferenceManager;
  }

  public MusicManager getMusicManager()
  {
    return _musicManager;
  }

  public SpriteManager getSpriteManager()
  {
    return _spriteManager;
  }

  public SoundManager getSoundManager()
  {
    return _soundManager;
  }

  @Override
  public void resize( final int width, final int height )
  {
    log( "Resizing game to: " + width + " x " + height );
    super.resize( width, height );
  }

  @Override
  public void pause()
  {
    log( "Pausing game" );
    super.pause();
  }

  @Override
  public void resume()
  {
    log( "Resuming game" );
    super.resume();
  }

  @Override
  public void setScreen( final Screen screen )
  {
    log( "Setting screen: " + screen.getClass().getSimpleName() );
    super.setScreen( screen );
  }

  private void log( final String message )
  {
    Gdx.app.log( getClass().getSimpleName(), message );
  }
}
