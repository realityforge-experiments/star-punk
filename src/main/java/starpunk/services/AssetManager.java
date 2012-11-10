package starpunk.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashMap;
import java.util.Map;

public final class AssetManager
  implements Disposable
{
  private final HashMap<String, TextureAtlas.AtlasRegion> _sprites = new HashMap<String, TextureAtlas.AtlasRegion>();
  private final HashMap<String, TextureAtlas> _textures = new HashMap<String, TextureAtlas>();
  private Texture _background;

  public void initialize()
  {
    final String textureName = "target/assets/game";
    final TextureAtlas textureAtlas = new TextureAtlas( Gdx.files.local( textureName ) );
    for( final TextureAtlas.AtlasRegion r : textureAtlas.getRegions() )
    {
      _sprites.put( r.name, r );
    }
    _textures.put( textureName, textureAtlas );

    _background = new Texture( Gdx.files.local( "assets/images/backgrounds/splash.gif" ) );
    _background.setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear );
  }

  public void dispose()
  {
    for( final Map.Entry<String, TextureAtlas> entry : _textures.entrySet() )
    {
      entry.getValue().dispose();
    }
    _textures.clear();
    _sprites.clear();
    _background.dispose();
  }

  public Texture getBackground()
  {
    return _background;
  }

  public TextureAtlas.AtlasRegion getSprite( final String name )
  {
    return _sprites.get( name );
  }
}
