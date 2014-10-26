require "test/unit"
require "stringio"

require_relative "rover"

class TestRover < Test::Unit::TestCase
  def test_execute_instruction
    r = Rover.new(1,1,"N")
    execute_and_assert(r, "M", [1, 2, "N"])
    execute_and_assert(r, "R", [1, 2, "E"])
    execute_and_assert(r, "M", [2, 2, "E"])
    execute_and_assert(r, "R", [2, 2, "S"])
    execute_and_assert(r, "M", [2, 1, "S"])
    execute_and_assert(r, "R", [2, 1, "W"])
    execute_and_assert(r, "M", [1, 1, "W"])
    execute_and_assert(r, "R", [1, 1, "N"])
    execute_and_assert(r, "L", [1, 1, "W"])
    execute_and_assert(r, "L", [1, 1, "S"])
    execute_and_assert(r, "L", [1, 1, "E"])
    execute_and_assert(r, "L", [1, 1, "N"])
  end

  def test_plateau
    p = Plateau.new(3, 5)
    p.validate_position(0, 0)
    p.validate_position(2, 4)
    p.validate_position(3, 5)
    assert_raise( RuntimeError ) { p.validate_position(4, 10) }
    assert_raise( RuntimeError ) { p.validate_position(1, 6) }
    assert_raise( RuntimeError ) { p.validate_position(-1, 1) }
    assert_raise( RuntimeError ) { p.validate_position(1, -1) }
  end

  def test_scenario_out_of_plateau
    r = ScenarioRunner.new(StringIO.new("5 5\n1 1 N\nMMMMMMMMM\n"))
    e = assert_raise( RuntimeError ) { r.run }
    assert_equal(e.to_s, "Invalid move, rover is out of the plateau (1, 6).")
  end

  def test_bad_input
    r = ScenarioRunner.new(StringIO.new("5 5\n1 1\nMMMMMMMMM\n"))
    e = assert_raise( RuntimeError ) { r.run }
    assert_equal(e.to_s, "Cannot parse rover position from line: '1 1\n'.")

    r = ScenarioRunner.new(StringIO.new("5 5\n1 1 N\n"))
    e = assert_raise( RuntimeError ) { r.run }
    assert_equal(e.to_s, "Expecting instructions for rover.")

    r = ScenarioRunner.new(StringIO.new(""))
    e = assert_raise( RuntimeError ) { r.run }
    assert_equal(e.to_s, "Expecting plateau size.")
  end

  def test_complete
    rovers = ScenarioRunner.new(File.open("../scenarios/complete.txt")).run
    assert_equal(2, rovers.size)
    assert_equal("1 3", rovers[0].to_s)
    assert_equal("5 1", rovers[1].to_s)
  end

  private

  def execute_and_assert(r, instruction, result)
    r.execute(instruction)
    assert_equal(result, [ r.x, r.y, r.heading])
  end

end

# compat mixin for Ruby 1.9.1 with test-unit gem in Eclipse
module Test
  module Unit
    module UI
      SILENT = false
    end

    class AutoRunner
      def output_level=(level)
        self.runner_options[:output_level] = level
      end
    end
  end
end