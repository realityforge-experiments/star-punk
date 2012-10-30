package starpunk.screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import starpunk.StarPunkGame;
import starpunk.services.MusicResource;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/** Shows a splash image and moves on to the next screen. */
public class SplashScreen
  extends Base2DScreen
{
  public SplashScreen( final StarPunkGame game )
  {
    super( game );
  }

  @Override
  public void show()
  {
    super.show();

    getGame().getMusicManager().play( new MusicResource( "src/main/assets/music/menu.ogg" ) );

    final Drawable drawable =
      new TextureRegionDrawable( new TextureRegion( StarPunkGame.getGame().getAssetManager().getBackground() ) );

    final Image image = new Image( drawable, Scaling.stretch );
    image.setFillParent( true );
    //Make sure image starts out with 0 alpha so it can fade in
    image.getColor().a = 0f;

    image.addAction( sequence( fadeIn( 0.75f ), delay( 1.75f ), fadeOut( 0.75f ),
                               new Action()
                               {
                                 @Override
                                 public boolean act( final float delta )
                                 {
                                   gotoNextScreen();
                                   return true;
                                 }
                               } ) );

    getStage().addActor( image );
  }

  private void gotoNextScreen()
  {
    getGame().setScreen( new GameLoopScreen( getGame() ) );
  }
}
