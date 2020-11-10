/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.shiruka.api.concurrent.concurrent;

import io.github.shiruka.log.Loggers;
import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ThreadFactory;
import org.jetbrains.annotations.NotNull;

/**
 * specifier for creating a block of threads used for
 * managing the server thread pool.
 */
public final class PoolSpec implements ThreadFactory, ForkJoinPool.ForkJoinWorkerThreadFactory,
  Thread.UncaughtExceptionHandler {

  /**
   * a thread factory that does handling for exceptions,
   * piping exception output to the loggers.
   */
  public static final ThreadFactory UNCAUGHT_FACTORY = new PoolSpec("Shiru ka - Net", 0,
    false);

  /**
   * the backing factory.
   */
  private static final ThreadFactory BACKING_FACTORY = Executors.defaultThreadFactory();

  /**
   * the name of the pool used to identify its threads.
   */
  private final String name;

  /**
   * maximum number of parallelism that should be limited
   * in the given thread pool.
   */
  private final int maxThreads;

  /**
   * whether or not the task order is relevant.
   */
  private final boolean doStealing;

  /**
   * Creates a new thread pool spec.
   *
   * @param name the name of the pool
   * @param maxThreads the max thread limit
   * @param doStealing whether or not the pool performs
   *   work steals
   */
  public PoolSpec(final String name, final int maxThreads, final boolean doStealing) {
    this.name = name;
    this.maxThreads = maxThreads;
    this.doStealing = doStealing;
  }

  /**
   * the name of the pool used to identify its threads.
   *
   * @return the name of the pool used to identify its threads.
   */
  @NotNull
  public String getName() {
    return this.name;
  }

  /**
   * maximum number of parallelism that should be limited in the given thread pool.
   *
   * @return maximum number of parallelism that should be limited in the given thread pool.
   */
  public int getMaxThreads() {
    return this.maxThreads;
  }

  /**
   * whether or not the task order is relevant.
   *
   * @return whether or not the task order is relevant.
   */
  public boolean isDoStealing() {
    return this.doStealing;
  }

  @Override
  public Thread newThread(@NotNull final Runnable r) {
    final var thread = new Thread(r, this.name);
    thread.setUncaughtExceptionHandler(this);
    return thread;
  }

  @Override
  public ForkJoinWorkerThread newThread(final ForkJoinPool pool) {
    final var worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
    worker.setName(this.name + " - " + worker.getPoolIndex());
    worker.setUncaughtExceptionHandler(this);
    return worker;
  }

  @Override
  public void uncaughtException(final Thread t, final Throwable e) {
    e.printStackTrace(new PrintStream(System.out) {
      @Override
      public void println(final Object x) {
        Loggers.error(String.valueOf(x));
      }
    });
  }
}
