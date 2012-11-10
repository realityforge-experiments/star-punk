package starpunk;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main
{
  public static void main( final String[] args )
  {
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