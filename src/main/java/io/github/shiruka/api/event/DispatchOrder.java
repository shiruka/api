/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.event;

import org.jetbrains.annotations.NotNull;

/**
 * the order in which events that are dispatched by the server's {@link EventController} are transmitted to their
 * respective listeners.
 */
public enum DispatchOrder {
  /**
   * a listener marked with this order is attempted to be invoked as soon as possible.
   */
  FIRST(-2),
  /**
   * a listener marked with this order will be invoked sometime after the first listeners and the middle listeners.
   */
  EARLY(-1),
  /**
   * the default order.
   */
  MIDDLE(0),
  /**
   * a listener marked with this order will be invoked sometime between the middle and the last listener.
   */
  LATE(1),
  /**
   * listeners marked with this method attempt to be invoked after all listeners.
   */
  LAST(2);

  /**
   * the order.
   */
  @NotNull
  private final Number order;

  /**
   * ctor.
   *
   * @param order the order.
   */
  DispatchOrder(@NotNull final Number order) {
    this.order = order;
  }

  /**
   * obtains the order.
   *
   * @return the order.
   */
  @NotNull
  public Number getOrder() {
    return this.order;
  }
}
