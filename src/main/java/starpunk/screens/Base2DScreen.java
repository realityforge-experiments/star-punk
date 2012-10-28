package starpunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import starpunk.StarPunkGame;

public abstract class Base2DScreen
  extends BaseScreen
{
  private final Stage _stage;

  protected Base2DScreen( final StarPunkGame game )
  {
    super( game );
    _stage = new Stage( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true );
  }

  @Override
  public void show()
  {
    super.show();
    Gdx.input.setInputProcessor( _stage );
  }

  @Override
  public void update( final float delta )
  {
    _stage.act( delta );
  }

  @Override
  public void draw( final float delta )
  {
    Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
    Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

    _stage.draw();

    Table.drawDebug( _stage );
  }
}
