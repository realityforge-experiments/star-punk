package starpunk.services;

import com.badlogic.gdx.audio.Music;

public final class MusicReference
{
  private final String _filename;
  private Music _resource;

  public MusicReference( final String filename )
  {
    _filename = filename;
  }

  public String getFilename()
  {
    return _filename;
  }

  Music getResource()
  {
    return _resource;
  }

  void setResource( final Music resource )
  {
    _resource = resource;
  }
}
