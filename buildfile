desc "StarPunk: An experiment to build a little space game"
define('star-punk') do
  project.group = 'org.realityforge'
  compile.options.source = '1.6'
  compile.options.target = '1.6'
  compile.options.lint = 'all'

  compile.with :javax_annotation, GDX.core, GDX.core_natives, GDX.backend_lwjgl, GDX.backend_lwjgl_natives, GDX.tools, ARTEMIS.framework

  test.using :testng

  package(:jar)
  package(:sources)
end
