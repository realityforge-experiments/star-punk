package starpunk.logic.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import starpunk.StarPunkGame;
import starpunk.logic.components.Position;
import starpunk.logic.components.Sprite;

public final class SpriteRenderSystem
  extends EntitySystem
{
  @Mapper
  ComponentMapper<Position> _positionMapper;
  @Mapper
  ComponentMapper<Sprite> _spriteMapper;
  private SpriteBatch _batch;
  private final OrthographicCamera _camera;
  private Bag<AtlasRegion> _regionsByEntity;
  private List<Entity> _sortedEntities;
  private final StarPunkGame _game;

  public SpriteRenderSystem( final StarPunkGame game, final OrthographicCamera camera )
  {
    super( Aspect.getAspectForAll( Position.class, Sprite.class ) );
    _game = game;
    _camera = camera;
  }

  protected final StarPunkGame getGame()
  {
    return _game;
  }

  @Override
  protected void initialize()
  {
    _regionsByEntity = new Bag<AtlasRegion>();

    _batch = new SpriteBatch();

    _sortedEntities = new ArrayList<Entity>();
  }

  @Override
  protected void begin()
  {
    _batch.setProjectionMatrix( _camera.combined );
    _batch.begin();
  }

  @Override
  protected boolean checkProcessing()
  {
    return true;
  }

  @Override
  protected void processEntities( final ImmutableBag<Entity> entities )
  {
    for( final Entity entity : _sortedEntities )
    {
      process( entity );
    }
  }

  protected void process( final Entity e )
  {
    if( _positionMapper.has( e ) )
    {
      final Position position = _positionMapper.getSafe( e );
      final Sprite sprite = _spriteMapper.get( e );

      final AtlasRegion spriteRegion = _regionsByEntity.get( e.getId() );
      _batch.setColor( sprite.getR(), sprite.getG(), sprite.getB(), sprite.getA() );

      if( null != spriteRegion )
      {
        final float posX = position.getX() - ( spriteRegion.getRegionWidth() / 2 * sprite.getScaleX() );
        final float posY = position.getY() - ( spriteRegion.getRegionHeight() / 2 * sprite.getScaleY() );
        _batch.draw( spriteRegion,
                     posX,
                     posY,
                     0,
                     0,
                     spriteRegion.getRegionWidth(),
                     spriteRegion.getRegionHeight(),
                     sprite.getScaleX(),
                     sprite.getScaleY(),
                     sprite.getRotation() );
      }
    }
  }

  protected void end()
  {
    _batch.end();
  }

  @Override
  protected void inserted( final Entity e )
  {
    final Sprite sprite = _spriteMapper.get( e );
    final AtlasRegion sprite1 = getGame().getSpriteManager().getSprite( sprite.getName(), sprite.getFrame() );
    _regionsByEntity.set( e.getId(), sprite1 );

    _sortedEntities.add( e );

    Collections.sort( _sortedEntities, new Comparator<Entity>()
    {
      @Override
      public int compare( final Entity e1, final Entity e2 )
      {
        final Sprite s1 = _spriteMapper.get( e1 );
        final Sprite s2 = _spriteMapper.get( e2 );
        return s1.getLayer().compareTo( s2.getLayer() );
      }
    } );
  }

  @Override
  protected void removed( final Entity e )
  {
    _regionsByEntity.set( e.getId(), null );
    _sortedEntities.remove( e );
  }
}
