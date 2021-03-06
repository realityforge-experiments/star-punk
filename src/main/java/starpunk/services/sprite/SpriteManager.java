package starpunk.services.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashMap;
import java.util.Map;

public final class SpriteManager
  implements Disposable
{
  private final HashMap<String, TextureAtlas.AtlasRegion> _sprites = new HashMap<String, TextureAtlas.AtlasRegion>();
  private final HashMap<String, TextureAtlas> _textures = new HashMap<String, TextureAtlas>();

  public void initialize()
  {
    final String textureName = "game.atlas";
    final TextureAtlas textureAtlas = new TextureAtlas( Gdx.files.internal( textureName ) );
    for( final TextureAtlas.AtlasRegion r : textureAtlas.getRegions() )
    {
      _sprites.put( toKey( r.name, r.index ), r );
    }
    _textures.put( textureName, textureAtlas );
  }

  private String toKey( final String name, final int index )
  {
    return index == -1 ? name : name + '-' + index;
  }

  public void dispose()
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

  public TextureAtlas.AtlasRegion getSprite( final String name, final int index )
  {
    return _sprites.get( toKey( name, index ) );
  }
}
