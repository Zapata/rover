class Plateau
  def initialize(x_max, y_max)
    @x_max = x_max.to_i
    @y_max = y_max.to_i
  end

  def validate_position(x, y)
    if x > @x_max or y > @y_max or x < 0 or y < 0
      raise "Invalid move, rover is out of the plateau (#{x}, #{y})."
    end
  end
end

class Rover
  CARDINALS = ["N", "E", "S", "W"]

  attr_reader :x, :y, :heading

  def initialize(x, y, heading)
    raise "Unknown heading '#{heading}'." unless CARDINALS.include?(heading)
    @x = x.to_i
    @y = y.to_i
    @heading = heading
  end

  def execute(instruction)
    case instruction
    when "L", "R"
      rotate(instruction)
    when "M"
      move
    else
      raise "Unknown instruction '#{instruction}'."
    end
  end

  def rotate(left_or_right)
    way = left_or_right == "L" ? -1 : 1
    @heading = CARDINALS[(CARDINALS.index(@heading) + way) % CARDINALS.size]
  end

  def move
    case @heading
    when "N"
      @y += 1
    when "E"
      @x += 1
    when "S"
      @y -= 1
    when "W"
      @x -= 1
    end
  end

  def to_s
    "#{@x} #{@y}"
  end
end

class ScenarioRunner
  def initialize(io)
    @io = io
  end

  def run
    rovers = []
    plateau = build_plateau
    while (rover = next_rover) != nil
      rovers << rover
      next_instructions.each do |instruction|
        rover.execute(instruction)
        plateau.validate_position(rover.x, rover.y)
      end
    end
    return rovers
  end

  private

  def build_plateau
    begin
      line = @io.readline
      plateau_size = line.split(" ")
      raise "Cannot parse plateau size from line: '#{line}'" if plateau_size.size != 2
      return Plateau.new(*plateau_size)
    rescue EOFError
      raise "Expecting plateau size."
    end
  end

  def next_rover
    begin
      line = @io.readline
      rover_coord = line.split(" ")
      raise "Cannot parse rover position from line: '#{line}'." if rover_coord.size != 3
      return Rover.new(*rover_coord)
    rescue EOFError
      return nil
    end
  end

  def next_instructions
    begin
      return @io.readline.chomp.split("")
    rescue EOFError
      raise "Expecting instructions for rover."
    end
  end
end

ARGV.each do |filename|
  rovers = ScenarioRunner.new(File.open(filename)).run
  puts rovers.join("\n")
end