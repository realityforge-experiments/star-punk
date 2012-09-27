package starpunk;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;

public class Main
{
  public static void main( final String[] args )
  {
    final TexturePacker.Settings settings = new TexturePacker.Settings();
    settings.maxWidth = 512;
    settings.maxHeight = 512;
    TexturePacker.process( settings, "src/main/resources", "target/assets", "game" );

    final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
    configuration.fullscreen = false;
    configuration.width = StarPunkGame.WIDTH;
    configuration.height = StarPunkGame.HEIGHT;
    configuration.useCPUSynch = false;
    configuration.vSyncEnabled = false;
    configuration.title = "Star Punk";
    new LwjglApplication( new StarPunkGame(), configuration );
  }
}