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
import starpunk.services.music.MusicResource;
import starpunk.services.sound.SoundResource;

public class OptionsScreen
  extends Base2DScreen
{
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

    final CheckBox soundCheckbox = new CheckBox( "", getSkin() );
    soundCheckbox.setChecked( getGame().getPreferenceManager().isSoundEnabled() );
    soundCheckbox.addListener( new ChangeListener()
    {
      @Override
      public void changed( final ChangeEvent event, final Actor actor )
      {
        getGame().getPreferenceManager().setSoundEnabled( soundCheckbox.isChecked() );
        getGame().getSoundManager().play( new SoundResource( "src/main/assets/sounds/click.wav" ) );
      }
    } );
    table.row();
    table.add( "Sound Effects" );
    table.add( soundCheckbox ).colspan( 2 ).left();

    final Label soundVolumeLabel =
      new Label( formatVolume( getGame().getPreferenceManager().getSoundVolume() ), getSkin() );

    // range is [0.0,1.0]; step is 0.1f
    final Slider soundVolumeSlider = new Slider( 0f, 1f, 0.1f, false, getSkin() );
    soundVolumeSlider.setValue( getGame().getPreferenceManager().getSoundVolume() );
    soundVolumeSlider.addListener( new ChangeListener()
    {
      @Override
      public void changed( final ChangeEvent event, final Actor actor )
      {
        final float value = ( (Slider) actor ).getValue();
        getGame().getPreferenceManager().setSoundVolume( value );
        soundVolumeLabel.setText( formatVolume( getGame().getPreferenceManager().getSoundVolume() ) );
      }
    } );

    table.row();
    table.add( "Music Volume" );
    table.add( soundVolumeSlider );
    table.add( soundVolumeLabel ).width( 40 );

    final CheckBox musicCheckbox = new CheckBox( "", getSkin() );
    musicCheckbox.setChecked( getGame().getPreferenceManager().isMusicEnabled() );
    musicCheckbox.addListener( new ChangeListener()
    {
      @Override
      public void changed( final ChangeEvent event, final Actor actor )
      {
        final boolean enabled = musicCheckbox.isChecked();
        getGame().getPreferenceManager().setMusicEnabled( enabled );
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

    final Label musicVolume =
      new Label( formatVolume( getGame().getPreferenceManager().getMusicVolume() ), getSkin() );

    // range is [0.0,1.0]; step is 0.1f
    final Slider musicVolumeSlider = new Slider( 0f, 1f, 0.1f, false, getSkin() );
    musicVolumeSlider.setValue( getGame().getPreferenceManager().getMusicVolume() );
    musicVolumeSlider.addListener( new ChangeListener()
    {
      @Override
      public void changed( final ChangeEvent event, final Actor actor )
      {
        final float value = ( (Slider) actor ).getValue();
        getGame().getPreferenceManager().setMusicVolume( value );
        musicVolume.setText( formatVolume( getGame().getPreferenceManager().getMusicVolume() ) );
      }
    } );

    table.row();
    table.add( "Music Volume" );
    table.add( musicVolumeSlider );
    table.add( musicVolume ).width( 40 );

    // register the back button
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
    table.add( backButton ).size( 250, 60 ).colspan( 3 );
  }

  private String formatVolume( final float volume )
  {
    return String.format( Locale.US, "%1.0f%%", ( volume * 100 ) );
  }
}
