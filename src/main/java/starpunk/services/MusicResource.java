package starpunk.services;

import javax.annotation.Nonnull;

public final class MusicResource
{
  @Nonnull
  private final String _filename;

  public MusicResource( @Nonnull final String filename )
  {
    _filename = filename;
  }

  @Nonnull
  public String getFilename()
  {
    return _filename;
  }
}
