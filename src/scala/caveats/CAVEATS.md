# Scala async caveat: singleton initialization + Future

`object ... extends App` executes the object body during JVM class initialization (`<clinit>`).

If that initialization path starts async work with `Future` and then blocks with `Await.result`, you can hit an initialization cycle:

1. Main thread is initializing the singleton and blocks in `Await.result`.
2. A worker thread running the `Future` touches the same singleton.
3. JVM class initialization lock prevents progress until initialization completes.
4. Main thread is waiting for the worker, worker is waiting for initialization.

Typical outcome:

- `java.util.concurrent.TimeoutException` from `Await.result`
- wrapped as `ExceptionInInitializerError`
- followed by `NoClassDefFoundError: Could not initialize class ...`

Reproducer in this folder:

- `ScalaAsyncInitRepro.scala`

Safe variant in this folder:

- `ScalaAsyncInitSafe.scala`

Safer pattern:

- Keep singleton initialization lightweight.
- Run async workflows from an explicit `main(args: Array[String])` entrypoint after initialization.
