package starpunk.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import starpunk.StarPunkGame;
import starpunk.services.sound.SoundResource;

public class MenuScreen
  extends Base2DScreen
{
  public MenuScreen( final StarPunkGame game )
  {
    super( game );
  }

  @Override
  public void show()
  {
    super.show();

    // retrieve the default table actor
    final Table table = getTable();
    table.add( "Welcome to StarPunk" ).spaceBottom( 50 );
    table.row();

    final TextButton startGameButton = new TextButton( "Start game", getSkin() );
    startGameButton.addListener( new ClickListener()
    {
      @Override
      public void touchUp( final InputEvent event, final float x, final float y, final int pointer, final int button )
      {
        super.touchUp( event, x, y, pointer, button );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
        getGame().setScreen( new GameLoopScreen( getGame() ) );
      }
    } );
    table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
    table.row();

    final TextButton optionsButton = new TextButton( "Options", getSkin() );
    optionsButton.addListener( new ClickListener()
    {
      @Override
      public void touchUp( final InputEvent event, final float x, final float y, final int pointer, final int button )
      {
        super.touchUp( event, x, y, pointer, button );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
        getGame().setScreen( new OptionsScreen( getGame() ) );
      }
    } );
    table.add( optionsButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
    table.row();

    final TextButton highScoresButton = new TextButton( "High Scores", getSkin() );
    highScoresButton.addListener( new ClickListener()
    {
      @Override
      public void touchUp( final InputEvent event, final float x, final float y, final int pointer, final int button )
      {
        super.touchUp( event, x, y, pointer, button );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
        getGame().setScreen( new EndGameScreen( getGame() ) );
      }
    } );
    table.add( highScoresButton ).uniform().fill();
  }
}
