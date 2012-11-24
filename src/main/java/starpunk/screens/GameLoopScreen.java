package starpunk.screens;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import starpunk.StarPunkGame;
import starpunk.logic.EntityFactory;
import starpunk.logic.systems.MovementSystem;
import starpunk.logic.systems.SpriteRenderSystem;

public final class GameLoopScreen
  extends BaseScreen
{
  private World _world;
  private OrthographicCamera _camera;
  private SpriteRenderSystem _renderSystem;

  public GameLoopScreen( final StarPunkGame game )
  {
    super( game );
    _camera = new OrthographicCamera( StarPunkGame.WIDTH, StarPunkGame.HEIGHT );

    _world = new World();
    _renderSystem = _world.setSystem( new SpriteRenderSystem( game, _camera ), true );
    _world.setSystem( new MovementSystem() );
    _world.initialize();

    for( int i = 0; i < 500; i++ )
    {
      EntityFactory.createStar( _world, StarPunkGame.WIDTH, StarPunkGame.HEIGHT ).addToWorld();
    }
    EntityFactory.createShip( _world, 0, 0 ).addToWorld();
  }

  @Override
  public void show()
  {
    super.show();
    getGame().getMusicManager().play( MediaConstants.LEVEL_MUSIC );
  }

  @Override
  public void hide()
  {
    super.hide();
    getGame().getMusicManager().play( null );
  }

  @Override
  public boolean update( final float delta )
  {

    if( Gdx.input.isKeyPressed( Input.Keys.ESCAPE ) )
    {
      getGame().getSoundManager().play( MediaConstants.CLICK_SOUND );
      getGame().setScreen( new EndGameScreen( getGame() ) );
      return false;
    }
    else
    {
      _world.setDelta( delta );
      _world.process();
      return true;
    }
  }

  @Override
  public void draw( final float delta )
  {
    _camera.update();
    Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT );
    _renderSystem.process();
  }
}
