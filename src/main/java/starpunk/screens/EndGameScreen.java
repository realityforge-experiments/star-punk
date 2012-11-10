package starpunk.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import starpunk.StarPunkGame;
import starpunk.services.music.MusicResource;
import starpunk.services.sound.SoundResource;

public final class EndGameScreen
  extends Base2DScreen
{
  public EndGameScreen( final StarPunkGame game )
  {
    super( game );
  }

  @Override
  public void show()
  {
    super.show();
    getGame().getMusicManager().play( new MusicResource( "src/main/assets/music/menu.ogg" ) );

    final Table table = getTable();
    table.defaults().spaceBottom( 30 );
    table.add( "High scores" ).colspan( 2 );

    final String score = String.valueOf( 0 );
    table.row();
    table.add( "You" );
    table.add( new Label( score, getSkin() ) );

    final TextButton backButton = new TextButton( "Back to main menu", getSkin() );
    backButton.addListener( new ClickListener()
    {
      @Override
      public void touchUp( final InputEvent event, final float x, final float y, final int pointer, final int button )
      {
        super.touchUp( event, x, y, pointer, button );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
        getGame().setScreen( new MenuScreen( getGame() ) );
      }
    } );
    table.row();
    table.add( backButton ).size( 250, 60 ).colspan( 2 );

    final Texture background = StarPunkGame.getGame().getAssetManager().getBackground();
    final Drawable drawable = new TextureRegionDrawable( new TextureRegion( background ) );
    table.setBackground( drawable );
  }

  @Override
  public void hide()
  {
    super.hide();
    getGame().getMusicManager().play( null );
  }
}
