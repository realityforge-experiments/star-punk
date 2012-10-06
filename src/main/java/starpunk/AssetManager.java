package starpunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import java.util.HashMap;
import java.util.Map;

public final class AssetManager
{
  private static final String FONT_CHARACTERS =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

  private final HashMap<String, TextureAtlas.AtlasRegion> _sprites = new HashMap<String, TextureAtlas.AtlasRegion>();
  private final HashMap<String, TextureAtlas> _textures = new HashMap<String, TextureAtlas>();
  private BitmapFont _font;

  protected void initialize()
  {
    final String textureName = "target/assets/game";
    final TextureAtlas textureAtlas = new TextureAtlas( Gdx.files.internal( textureName ) );
    for( final TextureAtlas.AtlasRegion r : textureAtlas.getRegions() )
    {
      _sprites.put( r.name, r );
    }
    _textures.put( textureName, textureAtlas );

    _font = TrueTypeFontFactory.createBitmapFont( Gdx.files.internal( "src/main/resources/psihoepatesaltern8.ttf" ),
                                                  FONT_CHARACTERS,
                                                  100,
                                                  100,
                                                  1.0f,
                                                  StarPunkGame.WIDTH,
                                                  StarPunkGame.HEIGHT );
  }

  protected void dispose()
  {
    for( final Map.Entry<String, TextureAtlas> entry : _textures.entrySet() )
    {
      entry.getValue().dispose();
    }
    _textures.clear();
    _sprites.clear();
    _font.dispose();
  }

  public BitmapFont getFont()
  {
    return _font;
  }

  public TextureAtlas.AtlasRegion getSprite( final String name )
  {
    return _sprites.get( name );
  }
}
