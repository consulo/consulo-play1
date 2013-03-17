package org.napile.playJava;

import org.napile.playJava4idea.PlayJavaConstants;

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
