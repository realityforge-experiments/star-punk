package starpunk.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.Locale;
import starpunk.StarPunkGame;
import starpunk.services.MusicResource;
import starpunk.services.SoundResource;

public class OptionsScreen
  extends Base2DScreen
{
  private Label _musicVolume;

  public OptionsScreen( final StarPunkGame game )
  {
    super( game );
  }

  @Override
  public void show()
  {
    super.show();

    final Table table = super.getTable();
    table.defaults().spaceBottom( 30 );
    table.columnDefaults( 0 ).padRight( 20 );
    table.add( "Options" ).colspan( 3 );

    final CheckBox musicCheckbox = new CheckBox( "", getSkin() );
    musicCheckbox.setChecked( getGame().getPreferencesManager().isMusicEnabled() );
    musicCheckbox.addListener( new ChangeListener()
    {
      @Override
      public void changed(
        final ChangeEvent event,
        final Actor actor )
      {
        final boolean enabled = musicCheckbox.isChecked();
        getGame().getPreferencesManager().setMusicEnabled( enabled );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
        if( enabled )
        {
          getGame().getMusicManager().play( new MusicResource( "src/main/assets/music/menu.ogg" ) );
        }
      }
    } );
    table.row();
    table.add( "Music" );
    table.add( musicCheckbox ).colspan( 2 ).left();

    // range is [0.0,1.0]; step is 0.1f
    final Slider volumeSlider = new Slider( 0f, 1f, 0.1f, false, getSkin() );
    volumeSlider.setValue( getGame().getPreferencesManager().getMusicVolume() );
    volumeSlider.addListener( new ChangeListener()
    {
      @Override
      public void changed( final ChangeEvent event, final Actor actor )
      {
        final float value = ( (Slider) actor ).getValue();
        getGame().getPreferencesManager().setMusicVolume( value );
        updateMusicVolumeLabel();
      }
    } );

    _musicVolume = new Label( "", getSkin() );
    updateMusicVolumeLabel();
    table.row();
    table.add( "Volume" );
    table.add( volumeSlider );
    table.add( _musicVolume ).width( 40 );

    // register the back button
    final TextButton backButton = new TextButton( "Back to main menu", getSkin() );
    backButton.addListener( new ClickListener()
    {
      @Override
      public void touchUp( final InputEvent event, final float x, final float y, final int pointer, final int button )
      {
        super.touchUp( event, x, y, pointer, button );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
        getGame().setScreen( new GameLoopScreen( getGame() ) );
      }
    } );
    table.row();
    table.add( backButton ).size( 250, 60 ).colspan( 3 );
  }

  private void updateMusicVolumeLabel()
  {
    final float volume = getGame().getPreferencesManager().getMusicVolume();
    final String text = String.format( Locale.US, "%1.0f%%", ( volume * 100 ) );
    _musicVolume.setText( text );
  }
}
