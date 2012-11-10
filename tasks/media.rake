target_dir = "target/tyrian-assets"

task 'unzip_tyrian_assets' do
  download_task = download("downloads/tyrian-graphics.zip" => "http://lostgarden.com/Remastered%20Tyrian%20Graphics.zip")

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

desc "Setup assets"
task "assets"

images.each do |image|
  filename = File.expand_path("assets/images/#{image.key}#{File.extname(image.filename)}")
  source_image = File.expand_path("#{target_dir}/#{image.filename}")
  file(source_image => %w(unzip_tyrian_assets))
  file(filename => [source_image]) do
    mkdir_p File.dirname(filename)
    rm_rf filename
    command = "convert -transparent '#bfdcbf' -extract #{image.x2 - image.x1}x#{image.y2 - image.y1}+#{image.x1}+#{image.y1} #{source_image} #{filename}"
    sh command
  end
  task("assets").enhance [filename]
end

splash_filename = File.expand_path("assets/images/backgrounds/splash.gif")
file(splash_filename => %w(unzip_tyrian_assets)) do
  mkdir_p File.dirname(splash_filename)
  cp "#{target_dir}/TSHPSP~1.GIF", splash_filename
end

task("assets").enhance [splash_filename]