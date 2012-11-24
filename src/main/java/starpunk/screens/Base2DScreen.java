package starpunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import javax.annotation.Nonnull;
import starpunk.StarPunkGame;

public abstract class Base2DScreen
  extends BaseScreen
{
  private Stage _stage;
  private Table _table;
  private Skin _skin;

  protected Base2DScreen( final StarPunkGame game )
  {
    super( game );
  }

  @Override
  public void dispose()
  {
    super.dispose();

    if( null != _skin )
    {
      _skin.dispose();
      _skin = null;
    }

    if( null != _stage )
    {
      _stage.dispose();
      _stage = null;
    }
  }

  @Nonnull
  protected final Stage getStage()
  {
    if( null == _stage )
    {
      throw new NullPointerException( "_stage" );
    }
    return _stage;
  }

  @Override
  public void show()
  {
    super.show();
    _stage = new Stage( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true );
    Gdx.input.setInputProcessor( _stage );
  }

  @Override
  public boolean update( final float delta )
  {
    getStage().act( delta );
    return true;
  }

  @Override
  public void draw( final float delta )
  {
    Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
    Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

    getStage().draw();

    if( getGame().isDebugMode() )
    {
      Table.drawDebug( getStage() );
    }
  }

  protected final Skin getSkin()
  {
    if( null == _skin )
    {
      final FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
      _skin = new Skin( skinFile );
    }
    return _skin;
  }

  protected final Table getTable()
  {
    if( null == _table )
    {
      _table = new Table( getSkin() );
      _table.setFillParent( true );
      if( getGame().isDebugMode() )
      {
        _table.debug();
      }
      getStage().addActor( _table );
    }
    return _table;
  }
}
