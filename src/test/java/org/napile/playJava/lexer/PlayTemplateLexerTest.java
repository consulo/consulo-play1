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

package org.consulo.playJava.lexer;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.consulo.play1.template.base.parser.lexer.PlayBaseTemplateLexer;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 16:28/18.03.13
 */
public class PlayTemplateLexerTest
{
	public static void main(String[] args) throws Exception
	{
		final InputStream resourceAsStream = PlayTemplateLexerTest.class.getResourceAsStream("PlayTemplate.html");

		InputStreamReader reader = new InputStreamReader(resourceAsStream);

		StringBuilder builder = new StringBuilder();
		int c;
		while((c = reader.read()) != -1)
		{
			builder.append((char)c);
		}

		PlayBaseTemplateLexer adapter = new PlayBaseTemplateLexer();
		adapter.start(builder);


		IElementType elementType = null;
		while((elementType = adapter.getTokenType()) != null)
		{
			System.out.println("Element [" + elementType + "], text [" + adapter.getTokenText().replace("\n", "") + "]");

			adapter.advance();
		}
	}
}
