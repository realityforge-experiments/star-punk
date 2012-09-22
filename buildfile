desc "StarPunk: An experiment to build a little space game"
define('star-punk') do
  project.group = 'org.realityforge'
  compile.options.source = '1.6'
  compile.options.target = '1.6'
  compile.options.lint = 'all'

  compile.with :javax_annotation

  test.using :testng

  package(:jar)
  package(:sources)
end
