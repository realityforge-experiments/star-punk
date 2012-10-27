package starpunk.services;

import com.badlogic.gdx.audio.Music;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class MusicReference
{
  @Nonnull
  private final String _filename;
  @Nullable
  private Music _resource;

  public MusicReference( @Nonnull final String filename )
  {
    _filename = filename;
  }

  @Nonnull
  public String getFilename()
  {
    return _filename;
  }

  @Nullable
  Music getResource()
  {
    return _resource;
  }

  void setResource( @Nullable final Music resource )
  {
    _resource = resource;
  }
}
