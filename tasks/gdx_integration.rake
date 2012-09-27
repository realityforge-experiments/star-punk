GDX_VERSION='0.9.6'
GDX = struct(
  :core => "com.badlogic.gdx:gdx:jar:#{GDX_VERSION}",
  :core_sources => "com.badlogic.gdx:gdx:jar:sources:#{GDX_VERSION}",
  :core_natives => "com.badlogic.gdx:gdx-natives:jar:#{GDX_VERSION}",
  :backend_lwjgl => "com.badlogic.gdx:gdx-backend-lwjgl:jar:#{GDX_VERSION}",
  :backend_lwjgl_sources => "com.badlogic.gdx:gdx-backend-lwjgl:jar:sources:#{GDX_VERSION}",
  :backend_lwjgl_natives => "com.badlogic.gdx:backend_lwjgl_natives:jar:#{GDX_VERSION}"
)

target_dir = "target/libgdx-#{GDX_VERSION}"

task 'unzip_libgdx' do
  download_task = download("libs/libgdx-#{GDX_VERSION}.zip" => "http://libgdx.googlecode.com/files/libgdx-#{GDX_VERSION}.zip")
  download_task.invoke

  unzip(target_dir => download_task.name).extract unless File.exist?(target_dir)
end

artifact(GDX.core).from(file("#{target_dir}/gdx.jar" => %w(unzip_libgdx)))
artifact(GDX.core_sources).from(file("#{target_dir}/sources/gdx-sources.jar" => %w(unzip_libgdx)))
artifact(GDX.core_natives).from(file("#{target_dir}/gdx-natives.jar" => %w(unzip_libgdx)))
artifact(GDX.backend_lwjgl).from(file("#{target_dir}/gdx-backend-lwjgl.jar" => %w(unzip_libgdx)))
artifact(GDX.backend_lwjgl_sources).from(file("#{target_dir}/sources/gdx-backend-lwjgl-sources.jar" => %w(unzip_libgdx)))
artifact(GDX.backend_lwjgl_natives).from(file("#{target_dir}/gdx-backend-lwjgl-natives.jar" => %w(unzip_libgdx)))
