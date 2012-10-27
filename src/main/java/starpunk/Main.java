package starpunk;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;

public class Main
{
  public static void main( final String[] args )
  {
    final TexturePacker.Settings settings = new TexturePacker.Settings();
    settings.maxWidth = 1024;
    settings.maxHeight = 1024;
    TexturePacker.process( settings, "src/main/resources", "target/assets", "game" );

    final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
    configuration.fullscreen = false;
    configuration.width = StarPunkGame.WIDTH;
    configuration.height = StarPunkGame.HEIGHT;
		configuration.useGL20 = true;
		configuration.vSyncEnabled = true;
    configuration.title = "Star Punk";
    new LwjglApplication( new StarPunkGame(), configuration );
  }
}