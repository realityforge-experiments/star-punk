package starpunk.screens;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import starpunk.EntityFactory;
import starpunk.StarPunkGame;
import starpunk.services.MusicResource;
import starpunk.services.SoundResource;
import starpunk.systems.MovementSystem;
import starpunk.systems.SpriteRenderSystem;

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
    _renderSystem = _world.setSystem( new SpriteRenderSystem( _camera ), true );
    _world.setSystem( new MovementSystem() );
    _world.initialize();

    for( int i = 0; 500 > i; i++ )
    {
      EntityFactory.createStar( _world, StarPunkGame.WIDTH, StarPunkGame.HEIGHT ).addToWorld();
    }
  }

  @Override
  public void show()
  {
    super.show();
    getGame().getMusicManager().play( new MusicResource( "src/main/assets/music/level.ogg" ) );
  }

  @Override
  public void hide()
  {
    super.hide();
    getGame().getMusicManager().play( null );
  }

  @Override
  public void update( final float delta )
  {
    _camera.update();

    _world.setDelta( delta );
    _world.process();
  }

  @Override
  public void draw( final float delta )
  {
    Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT );
    _renderSystem.process();
  }

  @Override
  public void render( final float delta )
  {
    super.render( delta );
    if( Gdx.input.isTouched() )
    {
      getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
      getGame().setScreen( new EndGameScreen( getGame() ) );
    }
  }
}
