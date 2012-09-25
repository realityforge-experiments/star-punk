package starpunk;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main
{
  public static void main( final String[] args )
  {
    final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
    configuration.fullscreen = false;
    configuration.width = 1280;
    configuration.height = 900;
    configuration.useCPUSynch = false;
    configuration.vSyncEnabled = false;
    configuration.title = "Star Punk";
    new LwjglApplication( new ApplicationListener() {
      @Override
      public void create()
      {
      }

      @Override
      public void resize( final int width, final int height )
      {
      }

      @Override
      public void render()
      {
      }

      @Override
      public void pause()
      {
      }

      @Override
      public void resume()
      {
      }

      @Override
      public void dispose()
      {
      }
    }, configuration );
  }
}