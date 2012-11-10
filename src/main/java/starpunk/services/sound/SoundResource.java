package starpunk.services.sound;

import javax.annotation.Nonnull;

public final class SoundResource
{
  @Nonnull
  private final String _filename;

  public SoundResource( @Nonnull final String filename )
  {
    _filename = filename;
  }

  @Nonnull
  public String getFilename()
  {
    return _filename;
  }
}
