GDX_VERSION='0.9.6'
GDX = struct(
  :core => "com.badlogic.gdx:gdx:jar:#{GDX_VERSION}",
  :core_sources => "com.badlogic.gdx:gdx:jar:sources:#{GDX_VERSION}",
  :core_natives => "com.badlogic.gdx:gdx-natives:jar:#{GDX_VERSION}",
  :backend_lwjgl => "com.badlogic.gdx:gdx-backend-lwjgl:jar:#{GDX_VERSION}",
  :backend_lwjgl_sources => "com.badlogic.gdx:gdx-backend-lwjgl:jar:sources:#{GDX_VERSION}",
  :backend_lwjgl_natives => "com.badlogic.gdx:backend_lwjgl_natives:jar:#{GDX_VERSION}"
)

task 'unzip_libgdx' do
download_task = download("libs/libgdx-#{GDX_VERSION}.zip" => "http://libgdx.googlecode.com/files/libgdx-#{GDX_VERSION}.zip")
  download_task.invoke
  unzip("target/libgdx-#{GDX_VERSION}" => download_task.name).extract
end

artifact(GDX.core).from(file("target/libgdx-#{GDX_VERSION}/gdx.jar" => ['unzip_libgdx']))
artifact(GDX.core_sources).from(file("target/libgdx-#{GDX_VERSION}/sources/gdx-sources.jar" => ['unzip_libgdx']))
artifact(GDX.core_natives).from(file("target/libgdx-#{GDX_VERSION}/gdx-natives.jar" => ['unzip_libgdx']))
artifact(GDX.backend_lwjgl).from(file("target/libgdx-#{GDX_VERSION}/gdx-backend-lwjgl.jar" => ['unzip_libgdx']))
artifact(GDX.backend_lwjgl_sources).from(file("target/libgdx-#{GDX_VERSION}/sources/gdx-backend-lwjgl-sources.jar" => ['unzip_libgdx']))
artifact(GDX.backend_lwjgl_natives).from(file("target/libgdx-#{GDX_VERSION}/gdx-backend-lwjgl-natives.jar" => ['unzip_libgdx']))
