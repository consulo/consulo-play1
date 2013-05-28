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

package org.consulo.playJava.routes;

import org.consulo.play1.route.psi.PlayRouteElementTypes;
import org.consulo.play1.route.psi.lexer.PlayRouteLexer;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 22:01/23.03.13
 */
public class PlayJavaRouteLexerTest
{
	public static void main(String[] args)
	{
		String str =
				"# Routes\n" +
				"# This file defines all application routes (Higher priority routes first)\n" +
				"# ~~~~\n" +
				"\n" +
				"# Home page\n" +
				"GET     /                                       Main.index\n" +
				"\n" +
				"# Admin\n" +
				"GET     /admin                                  Admin.index\n" +
				"\n" +
				"# Library\n" +
				"GET     /language.blog                          language.Blog.index\n" +
				"GET     /language.overview                      language.Overview.index\n" +
				"GET     /language.api                           language.Api.api\n" +
				"\n" +
				"# Users\n" +
				"GET     /user/{<[0-9]+>id}                   User.show\n" +
				"GET     /userProfile                            User.userProfile\n" +
				"GET     /forgetPassword                         User.forgetPassword\n" +
				"GET     /restorePassword                        User.restorePassword\n" +
				"GET     /librariesProfile                       User.librariesProfile\n" +
				"GET     /libraryAdd                             User.libraryAdd\n" +
				"\n" +
				"GET     /library/{<[\\w(\\.\\w)+]+>id}             library.Main.libraryProfile\n" +
				"GET     /libraryList                            library.Main.libraryList\n" +
				"\n" +
				"# Ignore favicon requests\n" +
				"GET     /favicon.ico                            404\n" +
				"\n" +
				"# Map static resources from the /app/public folder to the /public path\n" +
				"GET     /public/                                staticDir:public\n" +
				"\n" +
				"# Catch all\n" +
				"*       /{controller}/{action}                  {controller}.{action}\n" +
				"*\t\t/                                       module:gwt2\n";

		PlayRouteLexer lexer = new PlayRouteLexer();
		lexer.start(str);

		IElementType tokenType = null;
		while((tokenType = lexer.getTokenType()) != null)
		{
			if(tokenType == PlayRouteElementTypes.WHITE_SPACE || tokenType == PlayRouteElementTypes.COMMENT)
			{
				lexer.advance();
				continue;
			}

			System.out.println(tokenType + " " + lexer.getTokenText());
			lexer.advance();
		}
	}
}
