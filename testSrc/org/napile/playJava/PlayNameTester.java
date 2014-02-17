/*
 * Copyright 2010-2013 napile.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.napile.playJava;

import org.consulo.play1.PlayJavaConstants;

/**
 * @author VISTALL
 * @since 16:30/17.03.13
 */
public class PlayNameTester
{
	public static void main(String[] args)
	{
		test(PlayJavaConstants.JAR_PATTERN.matcher("play-1.2.5.jar").find());
		test(PlayJavaConstants.JAR_PATTERN.matcher("play-1.2.1.jar").find());
		test(PlayJavaConstants.JAR_PATTERN.matcher("play-1.2.jar").find());
		test(PlayJavaConstants.JAR_PATTERN.matcher("play-1.2.3.jar").find());
		test(PlayJavaConstants.JAR_PATTERN.matcher("play-1.2.5.jar").find());

//		test(PlayConstants.JAR_LIB_PATTERN.matcher("play-2.2.5.jar").find());
	}

	private static void test(boolean test)
	{
		if(!test)
			throw new IllegalArgumentException();
	}
}
