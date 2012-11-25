package starpunk.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import starpunk.StarPunkGame;
import starpunk.logic.EntityFactory;
import starpunk.logic.PlayerInput;
import starpunk.logic.systems.MovementSystem;
import starpunk.logic.systems.PlayerMovementSystem;
import starpunk.logic.systems.SpriteRenderSystem;

public final class GameLoopScreen
  extends BaseScreen
{
  private final World _world;
  private final OrthographicCamera _camera;
  private final SpriteRenderSystem _renderSystem;

  public GameLoopScreen( final StarPunkGame game )
  {
    super( game );
    _camera = new OrthographicCamera( StarPunkGame.WIDTH, StarPunkGame.HEIGHT );

    _world = new World();
    _renderSystem = _world.setSystem( new SpriteRenderSystem( game, _camera ), true );
    _world.setManager( new TagManager() );
    _world.setSystem( new MovementSystem() );
    _world.setSystem( new PlayerMovementSystem() );
    _world.initialize();

    for( int i = 0; i < 500; i++ )
    {
      EntityFactory.createStar( _world, StarPunkGame.WIDTH, StarPunkGame.HEIGHT ).addToWorld();
    }
    final Entity ship = EntityFactory.createShip( _world, 0, 0 );
    ship.addToWorld();
    _world.getManager( TagManager.class ).register( "PLAYER", ship );
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
    final PlayerInput input = PlayerInput.getPlayerInput();
    input.setLeft( Gdx.input.isKeyPressed( Input.Keys.DPAD_LEFT ) );
    input.setRight( Gdx.input.isKeyPressed( Input.Keys.DPAD_RIGHT ) );
    input.setUp( Gdx.input.isKeyPressed( Input.Keys.DPAD_UP ) );
    input.setDown( Gdx.input.isKeyPressed( Input.Keys.DPAD_DOWN ) );

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
