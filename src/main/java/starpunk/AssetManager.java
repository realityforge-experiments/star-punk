package starpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.HashMap;
import java.util.Map;

public final class AssetManager
{
  private final HashMap<String, TextureAtlas.AtlasRegion> _sprites = new HashMap<String, TextureAtlas.AtlasRegion>();
  private final HashMap<String, TextureAtlas> _textures = new HashMap<String, TextureAtlas>();

  protected void initialize()
  {
    final String textureName = "target/assets/game";
    final TextureAtlas textureAtlas = new TextureAtlas( Gdx.files.internal( textureName ) );
    for( final TextureAtlas.AtlasRegion r : textureAtlas.getRegions() )
    {
      _sprites.put( r.name, r );
    }
    _textures.put( textureName, textureAtlas );
  }

  protected void dispose()
  {
    for( final Map.Entry<String, TextureAtlas> entry : _textures.entrySet() )
    {
      entry.getValue().dispose();
    }
    _textures.clear();
    _sprites.clear();
  }

  public TextureAtlas.AtlasRegion getSprite( final String name )
  {
    return _sprites.get( name );
  }
}
