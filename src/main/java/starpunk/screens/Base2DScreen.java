package starpunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

  @Override
  public void show()
  {
    super.show();
    _stage = new Stage( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true );
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

    if( getGame().isDebugMode() )
    {
      Table.drawDebug( _stage );
    }
  }

  protected final Skin getSkin()
  {
    if( null == _skin )
    {
      final FileHandle skinFile = Gdx.files.internal( "src/main/assets/skin/uiskin.json" );
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
      _stage.addActor( _table );
    }
    return _table;
  }
}
