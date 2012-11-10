def base_dir
  File.expand_path("#{File.dirname(__FILE__)}/..")
end

def raw_assets_dir
  "#{base_dir}/assets"
end

def compiled_assets_dir
  "#{base_dir}/_assets"
end

namespace 'assets' do

  desc 'Prepare the raw asset directory'
  task 'prepare'

  desc 'Compile the assets'
  task 'compile' => %w(assets:prepare)


  desc 'Cleanup assets'
  task 'clean' do
    rm_rf raw_assets_dir
    rm_rf compiled_assets_dir
  end

  desc 'Rebuild assets'
  task 'rebuild' => %w(assets:clean assets:compile)

end

target_dir = "target/tyrian-assets"

task 'unzip_tyrian_assets' do
  download_task =
    download("#{base_dir}/downloads/tyrian-graphics.zip" => "http://lostgarden.com/Remastered%20Tyrian%20Graphics.zip")

  unzip(target_dir => download_task.name).from_path('Remastered Tyrian Graphics')
  file(File.expand_path(target_dir)).invoke
end

Image = Struct.new("Image", :key, :filename, :x1, :y1, :x2, :y2)

images = []

def extract_horizontal_sequence(images, file, key, count, stride, inset, initial_x, initial_y, width, height)
  (0...count).each do |offset|
    x1 = initial_x + (stride * offset) + inset
    x2 = x1 + width
    y1 = initial_y
    y2 = y1 + height
    images << Image.new("#{key}_#{offset}", file, x1, y1, x2, y2)
  end
end

extract_horizontal_sequence(images, "newsh2.shp.000000.png", "u_fighter", 8, 24, 4, 0, 141, 16, 20)

images.each do |image|
  filename = File.expand_path("#{raw_assets_dir}/images/#{image.key}#{File.extname(image.filename)}")
  source_image = File.expand_path("#{target_dir}/#{image.filename}")
  file(source_image => %w(unzip_tyrian_assets))
  file(filename => [source_image]) do
    mkdir_p File.dirname(filename)
    rm_rf filename
    command = "convert -transparent '#bfdcbf' -extract #{image.x2 - image.x1}x#{image.y2 - image.y1}+#{image.x1}+#{image.y1} #{source_image} #{filename}"
    sh command
  end
  task("assets:prepare").enhance [filename]
end

splash_filename = File.expand_path("#{raw_assets_dir}/images/backgrounds/splash.png")
file(splash_filename => %w(unzip_tyrian_assets)) do
  mkdir_p File.dirname(splash_filename)
  cp "#{target_dir}/TSHPSP~1.GIF", splash_filename
end

task("assets:prepare").enhance [splash_filename]
