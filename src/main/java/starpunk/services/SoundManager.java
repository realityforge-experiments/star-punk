package starpunk.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import javax.annotation.Nonnull;
import starpunk.util.CacheEntryRemovedListener;
import starpunk.util.LRUCache;

public final class SoundManager
  implements CacheEntryRemovedListener<SoundResource, Sound>, Disposable
{
  private float _volume = 1f;
  private boolean _disabled;
  private final LRUCache<SoundResource, Sound> _cache;

  public SoundManager()
  {
    _cache = new LRUCache<SoundResource, Sound>( 23 );
    _cache.setEntryRemovedListener( this );
  }

  public void play( @Nonnull final SoundResource resource )
  {
    if( !_disabled )
    {
      log( "Playing sound: " + resource.getFilename() );
      getSound( resource ).play( _volume );
    }
  }

  private Sound getSound( final SoundResource resource )
  {
    Sound sound = _cache.get( resource );
    if( null == sound )
    {
      final FileHandle soundFile = Gdx.files.internal( resource.getFilename() );
      sound = Gdx.audio.newSound( soundFile );
      _cache.add( resource, sound );
    }
    return sound;
  }

  public void setVolume( final float volume )
  {
    if( volume < 0 || volume > 1f )
    {
      throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
    }
    log( "Adjusting sound volume to: " + volume );
    _volume = volume;
  }

  public void setDisabled( final boolean disabled )
  {
    _disabled = disabled;
  }

  @Override
  public void notifyEntryRemoved( final SoundResource key, final Sound value )
  {
    log( "Disposing sound: " + key.getFilename() );
    disposeSound( value );
  }

  public void dispose()
  {
    log( "Disposing sound manager" );
    for( final Sound sound : _cache.retrieveAll() )
    {
      disposeSound( sound );
    }
  }

  private void disposeSound( final Sound value )
  {
    value.stop();
    value.dispose();
  }

  private void log( final String message )
  {
    Gdx.app.log( getClass().getSimpleName(), message );
  }
}
