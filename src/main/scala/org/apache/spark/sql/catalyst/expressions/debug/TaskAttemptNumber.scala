/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.catalyst.expressions.debug

import org.apache.spark.sql.catalyst.FunctionIdentifier
import org.apache.spark.sql.catalyst.expressions.{Expression, ExpressionDescription}
import org.apache.spark.sql.extra.{ExpressionUtils, FunctionDescription}
import org.apache.spark.sql.types.{DataType, IntegerType}

// scalastyle:off line.size.limit
@ExpressionDescription(
  usage = """_FUNC_() - Get task attemptNumber, how many times this task has been attempted""",
  examples = "",
  since = "0.3.0",
  group = "misc_funcs",
  source = "built-in")
// scalastyle:on line.size.limit
case class TaskAttemptNumber() extends TaskContextHelper {
  override def dataType: DataType = IntegerType
  override def prettyName: String = "task_attempt_num"
  override protected def func: () => Any = () => ctx.attemptNumber()
  override protected def funcName: String = "attemptNumber"
}

object TaskAttemptNumber {
  val fd: FunctionDescription = (
    new FunctionIdentifier("task_attempt_num"),
    ExpressionUtils.getExpressionInfo(classOf[TaskAttemptNumber], "task_attempt_num"),
    (_: Seq[Expression]) => TaskAttemptNumber())
}
