package starpunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import starpunk.StarPunkGame;

public final class EndGameScreen
  extends BaseScreen
{
  private final SpriteBatch spriteBatch;
  private final Matrix4 viewMatrix = new Matrix4();
  private final Matrix4 transformMatrix = new Matrix4();

  public EndGameScreen( final StarPunkGame game )
  {
    super( game );
    spriteBatch = new SpriteBatch();
  }

  @Override
  public void dispose()
  {
    spriteBatch.dispose();
  }

  @Override
  public void draw( final float delta )
  {
    Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );

    viewMatrix.setToOrtho2D( 0, 0, 480, 320 );
    spriteBatch.setProjectionMatrix( viewMatrix );
    spriteBatch.setTransformMatrix( transformMatrix );
    spriteBatch.begin();
    spriteBatch.disableBlending();
    spriteBatch.setColor( Color.WHITE );
    spriteBatch.draw( StarPunkGame.getGame().getAssetManager().getBackground(),
                      0,
                      0,
                      480,
                      320,
                      0,
                      0,
                      512,
                      512,
                      false,
                      false );
    spriteBatch.enableBlending();
    final String text = "It is the end my friend.\nTouch to continue!";
        final BitmapFont font = StarPunkGame.getGame().getAssetManager().getFont();
    final TextBounds bounds = font.getMultiLineBounds( text );
    spriteBatch.setBlendFunction( GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA );
    font.drawMultiLine( spriteBatch, text, 0, 160 + bounds.height / 2, 480, HAlignment.CENTER );
    spriteBatch.end();
  }

  @Override
  public void update( final float delta )
  {
    if( Gdx.input.isTouched() )
    {
      getGame().setScreen( new GameLoopScreen( getGame() ) );
    }
  }
}
