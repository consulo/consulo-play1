package org.napile.playJava4idea;

import java.util.regex.Pattern;

/**
 * @author VISTALL
 * @since 16:35/17.03.13
 */
public interface PlayJavaConstants
{
	Pattern JAR_PATTERN = Pattern.compile("play-1.\\d(.\\d)?.jar");

	String PLAY_PLAY = "play.Play";
}
