#GDX_VERSION='0.9.6'
GDX_VERSION='nightly-20121021'
GdxLibrary = Struct.new("GdxLibrary", :key, :spec, :file)

libraries = [
  GdxLibrary.new(:core, "com.badlogic.gdx:gdx:jar:#{GDX_VERSION}", "gdx.jar"),
  GdxLibrary.new(:core_sources, "com.badlogic.gdx:gdx:jar:sources:#{GDX_VERSION}", "sources/gdx-sources.jar"),
  GdxLibrary.new(:core_natives, "com.badlogic.gdx:gdx-natives:jar:#{GDX_VERSION}", "gdx-natives.jar"),
  GdxLibrary.new(:tools, "com.badlogic.gdx:gdx-tools:jar:#{GDX_VERSION}", "extensions/gdx-tools.jar"),
  GdxLibrary.new(:tools_sources,
                 "com.badlogic.gdx:gdx-tools:jar:sources:#{GDX_VERSION}",
                 "extensions/sources/gdx-tools-sources.jar"),
  GdxLibrary.new(:backend_lwjgl, "com.badlogic.gdx:gdx-backend-lwjgl:jar:#{GDX_VERSION}", "gdx-backend-lwjgl.jar"),
  GdxLibrary.new(:backend_lwjgl_sources,
                 "com.badlogic.gdx:gdx-backend-lwjgl:jar:sources:#{GDX_VERSION}", "sources/gdx-backend-lwjgl-sources.jar"),
  GdxLibrary.new(:backend_lwjgl_natives,
                 "com.badlogic.gdx:backend_lwjgl_natives:jar:#{GDX_VERSION}",
                 "gdx-backend-lwjgl-natives.jar")
]

if GDX_VERSION == '0.9.6'
  libraries << GdxLibrary.new(:gdx_stb_truetype,
                              "com.badlogic.gdx:gdx-stb-truetype:jar:#{GDX_VERSION}",
                              "extensions/gdx-stb-truetype.jar")
  libraries << GdxLibrary.new(:gdx_stb_truetype_sources,
                              "com.badlogic.gdx:gdx-stb-truetype:jar:sources:#{GDX_VERSION}",
                              "extensions/sources/gdx-stb-truetype-sources.jar")
  libraries << GdxLibrary.new(:gdx_stb_truetype_natives,
                              "com.badlogic.gdx:gdx-stb-truetype-natives:jar:#{GDX_VERSION}",
                              "extensions/gdx-stb-truetype-natives.jar")
end

GDX = struct(libraries.inject({}) {|memo, lib| memo[lib.key] = lib.spec; memo })

target_dir = "target/libgdx-#{GDX_VERSION}"

task 'unzip_libgdx' do
  download_task = download("downloads/libgdx-#{GDX_VERSION}.zip" => "http://libgdx.googlecode.com/files/libgdx-#{GDX_VERSION}.zip")
  download_task.invoke

  unzip(target_dir => download_task.name).extract unless File.exist?(target_dir)
end

libraries.each do |lib|
  artifact(lib.spec).from(file("#{target_dir}/#{lib.file}.jar" => %w(unzip_libgdx)))
end
