package starpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.HashMap;

public final class AssetManager
{
  private final HashMap<String, TextureAtlas.AtlasRegion> _sprites = new HashMap<String, TextureAtlas.AtlasRegion>();

  protected void initialize()
  {
    final TextureAtlas textureAtlas = new TextureAtlas( Gdx.files.internal( "target/assets/game" ) );
    for( final TextureAtlas.AtlasRegion r : textureAtlas.getRegions() )
    {
      _sprites.put( r.name, r );
    }
  }

  public TextureAtlas.AtlasRegion getSprite( final String name )
  {
    return _sprites.get( name );
  }
}
