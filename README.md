# Mars Rover Simulation

A text-based backend simulation of a Mars Rover navigating a bounded, wrapping 2D grid with obstacle detection — built using strict Test-Driven Development (Red-Green-Refactor) and clean Object-Oriented Design.

## Tech Stack

- **Java 17**
- **Maven** (build tool)
- **JUnit 5** (testing — the only dependency in this project)

No frameworks, no Spring Boot, no external libraries beyond the standard library and JUnit. This is intentional (see Q&A below).

## How to Run

```bash
mvn test
```

## Project Structure

```
src/main/java/com/incubyte/marsrover/
├── Rover.java                 — holds rover state (position, direction), delegates commands
├── Direction.java              — enum representing N/S/E/W with movement deltas
├── DirectionOrder.java          — shared counter-clockwise ordering used by rotation commands
├── Grid.java                    — bounds, wrapping, and obstacle knowledge
├── RoverCommand.java             — command interface (Command Pattern)
├── ForwardCommand.java            — moves rover forward, respects obstacles
├── BackwardCommand.java            — moves rover backward, respects obstacles
├── LeftwardCommand.java              — rotates rover counter-clockwise
├── RightwardCommand.java              — rotates rover clockwise
├── CommandFactory.java                — maps command characters to command instances
├── CommandInterpreter.java             — executes a full command string, stops on obstacle
└── ExecutionResult.java                 — reports outcome: status, position, obstacle, commands run

src/test/java/com/incubyte/marsrover/
├── RoverTest.java
├── GridTest.java
└── CommandInterpreterTest.java
```

## Design Decisions — Q&A

### Q: Why no Spring Boot or any framework?

The assignment brief explicitly requires zero external production dependencies — only the language's standard library and a bare-minimum test framework (JUnit 5 here). Spring Boot annotations (`@Component`, `@Autowired`, `@RestController`, etc.) would pull in an entire dependency-injection container and web framework for a problem that doesn't need either. This project only depends on `junit-jupiter`, declared with `test` scope in `pom.xml` — it never ships in the production build.

### Q: Why is movement math (`Rover` doesn't compute deltas itself) split across `Direction` and the Command classes?

Early versions of this code had `Rover.moveForward()` directly containing coordinate math (`currentY += 1` for north, etc.). This violated Single Responsibility: `Rover` was responsible for *both* holding state and *computing* what movement means for each direction — two reasons to change.

The fix: `Direction` (an enum) knows its own spatial delta (`getDeltaX()`/`getDeltaY()`), since "what does facing north mean spatially" is intrinsic to the direction itself, not the rover. `ForwardCommand`/`BackwardCommand` then combine the rover's current position with its current direction's delta to compute a target — `Rover` itself never contains movement-calculation logic, only state and mutators (`setPosition`, `getCurrentXCoordinate`, etc.).

### Q: Why is there a `RoverCommand` interface and one class per command, instead of an if/else chain in `Rover.executeCommand()`?

This is the Command Pattern, chosen specifically to satisfy the Open/Closed Principle. An early version had:

```java
public void executeCommand(char command) {
    if (command == 'F') { moveForward(); }
    else if (command == 'B') { moveBackward(); }
    // ...
}
```

Every new command meant editing this method again — modifying existing, working code to add new behav0ior. That's a textbook OCP violation. By extracting each command into its own class implementing `RoverCommand`, adding a new command means *adding a new file* and registering it in `CommandFactory` — `Rover.executeCommand()` itself never needs to change again.

### Q: Why is there a `CommandFactory` instead of `Rover` directly instantiating command classes?

Without it, `Rover` would need to know about every concrete command class (`ForwardCommand`, `BackwardCommand`, etc.) just to map characters to behavior. `CommandFactory` centralizes that "character → command" resolution as its own responsibility, so `Rover` only depends on the `RoverCommand` interface and the factory — not on every concrete implementation. This keeps `Rover` decoupled from implementation details of individual commands.

### Q: Why does `Grid` own wrapping *and* obstacle detection — isn't that two responsibilities?

`Grid` represents the rover's environment as a whole — its physical boundaries and what's inside them. Both bounds/wrapping and obstacle positions are properties of "the space the rover moves through," not properties of the rover itself. This mirrors the assignment spec's own suggested structure, which explicitly separates `Grid` (boundaries, wrapping, obstacle map) from `Rover` (position, driving, state).

An alternative would be splitting `Grid` and a separate `ObstacleMap` class — considered, but decided against for this scope, since both concepts describe the same "environment" and splitting them further didn't reduce coupling meaningfully at this size.

### Q: Why does `Rover.setPosition()` automatically wrap coordinates, instead of commands calling `Grid.wrapXCoordinate()` themselves?

Three designs were considered:
1. **`Rover` holds a `Grid` reference just for commands to pull out and use directly** — rejected, since `Rover` would be carrying an object it never uses itself, and every command would need to remember to apply wrapping manually (error-prone, easy to forget in a new command).
2. **`RoverCommand.execute()` takes both `Rover` and `Grid` as parameters** — rejected, since rotation commands (`LeftwardCommand`/`RightwardCommand`) don't need `Grid` at all, so they'd receive and ignore an unused parameter, and every command signature would need updating.
3. **`Rover.setPosition()` applies wrapping internally, transparently** (chosen) — `Rover` guarantees its own invariant ("I will never hold an out-of-bounds position") regardless of who calls `setPosition`. Commands stay exactly as simple as before wrapping was introduced — `ForwardCommand`/`BackwardCommand` didn't need any changes when wrapping was added.

This does mean `Rover` actively *uses* `Grid`'s wrapping behavior, not just stores it passively — a deliberate tradeoff, similar to how a `BankAccount` class might refuse to hold a negative balance internally rather than trusting every caller to check first.

### Q: How does obstacle-triggered stopping work end-to-end?

1. `Rover.tryMoveTo(x, y)` checks `Grid.hasObstacleAt(x, y)` before mutating position. If blocked, it remembers the attempted coordinate (`lastBlockedX`/`lastBlockedY`) and returns `false` without moving.
2. `ForwardCommand`/`BackwardCommand` return whatever `tryMoveTo` returns. `LeftwardCommand`/`RightwardCommand` always return `true` (rotation can't be blocked).
3. `Rover.executeCommand(char)` propagates that boolean up.
4. `CommandInterpreter.interpret(rover, commands)` loops through each character, and the moment a command returns `false`, it stops the loop immediately — remaining commands in the string are never executed.
5. `CommandInterpreter` builds an `ExecutionResult` describing the outcome: status (`COMPLETED`/`BLOCKED`), the rover's last safe position, the obstacle coordinate that blocked it, and the list of commands that actually ran before stopping.

### Q: Why does `Rover` remember the last blocked coordinate (`lastBlockedX`/`lastBlockedY`) instead of `CommandInterpreter` tracking it?

This was a deliberate, acknowledged tradeoff. The cleaner alternative — having `RoverCommand.execute()` return a richer result object (success flag + attempted coordinate) instead of a plain `boolean` — was considered, since storing "history of a failed attempt" arguably isn't `Rover`'s job (it's meant to hold *current* state, not execution metadata). 

The simpler approach was chosen instead: `tryMoveTo` already computes the target coordinate right when it checks for an obstacle, so remembering it there avoids recomputing delta math elsewhere or restructuring every command's return type (including rotation commands, which would need to return the richer type despite never failing). This is a pragmatic tradeoff for project scope, not a claim that it's the only correct design — the richer-return-type alternative is equally defensible and was scoped out due to time constraints.

### Q: Why is rotation ordering (`DirectionOrder`) a separate small utility class instead of methods on the `Direction` enum?

Adding `getLeftDirection()`/`getRightDirection()` directly to `Direction` was considered and initially built, but reconsidered — it would give `Direction` two responsibilities (spatial delta knowledge + rotational ordering knowledge). Since `LeftwardCommand` and `RightwardCommand` both needed the same counter-clockwise ordering array, and duplicating it in both classes would be a DRY violation, a small shared constant-holder (`DirectionOrder`) was extracted instead — keeping `Direction` narrowly scoped to just spatial deltas, while still avoiding duplication between the two rotation commands.

### Q: Why `.gitignore` excludes `target/`?

`target/` is Maven's build output directory — compiled `.class` files, test reports, etc. — entirely regenerated on every `mvn test`/`mvn build`. It should never be committed to version control since it's derived, not source, and tracking it just adds noise/merge-conflict risk to the repository.

## Test Coverage Summary

18 tests across three test classes, covering:
- Rover initialization, movement (forward/backward), rotation (left/right)
- Grid coordinate wrapping (north/south, east/west edges)
- Obstacle detection at the `Grid` level
- Obstacle blocking at the `Rover` level (forward and backward)
- Full command string execution via `CommandInterpreter`, including early stopping on obstacles
- End-to-end integration test replicating the assignment's exact sample scenario

## Development Process

This project was built following strict Red-Green-Refactor TDD discipline — every feature began with a failing test, followed by the minimum code to pass it, followed by refactoring where needed. Commit history reflects this cycle with `test:`, `feat:`, `refactor:`, and `fix:` prefixed commits throughout.
